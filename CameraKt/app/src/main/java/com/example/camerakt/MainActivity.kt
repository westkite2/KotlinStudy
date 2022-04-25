package com.example.camerakt

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.FileProvider
import com.example.camerakt.databinding.ActivityMainBinding
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!

    val REQUEST_IMAGE_CAPTURE = 1 //카메라 사진 촬영 요청 코드 (임의)
    lateinit var curPhotoPath: String //문자열 형태의 사진 경로 값 (lateinit은 값을 늦게 초기화한다는 뜻. null 선언)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setPermission() //권한을 체크하는 메소드 수행

        btn_camera.setOnClickListener{
            takeCapture() //기본 카메라 앱을 실행해 사진 촬영
        }
    }

    //카메라 촬영 메소드
    private fun takeCapture() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also{ takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photofile: File? = try{
                    createImageFile()
                } catch (ex: IOException){
                    null
                }
                photofile?.also{
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.example.camerakt.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
            }
        }
    }
    //이미지 파일 생성
    private fun createImageFile(): File? {
        val timestamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timestamp}_", ".jpg", storageDir)
            .apply { curPhotoPath = absolutePath }
    }

    // 테드 퍼미션 설정
    private fun setPermission() {
        val permission = object : PermissionListener{
            override fun onPermissionGranted() { //설정해놓은 위험 권한들이 허용 되었을 경우
                Toast.makeText(this@MainActivity, "권한이 허용되었습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) { //위험 권한 거부 시
                Toast.makeText(this@MainActivity, "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        TedPermission.with(this)
            .setPermissionListener(permission)
            .setRationaleMessage("카메라 앱을 사용하시려면 권한을 허용해주세요")
            .setDeniedMessage("권한을 거부하셨습니다. [앱 설정] -> [권한]에서 허용해주세요")
            .setPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA)
            .check()
    }
    //startActivityForResult를 통해 기본 카메라로부터 받아온 사진 결과값
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK){
            //이미지를 성공적으로 가져왔다면.
            val bitmap: Bitmap
            val file = File(curPhotoPath) //사진에 저장된 값을 쥐고 있다
            if (Build.VERSION.SDK_INT < 28){ //안드로이드 9.0 (pie) 버전보다 낮을 경우 (버전에 따라 처리가 다름)
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, Uri.fromFile(file))
                iv_profile.setImageBitmap(bitmap)
            }else {//pie 이상일 경우
                val decode = ImageDecoder.createSource(
                    this.contentResolver,
                    Uri.fromFile(file)
                )
                bitmap = ImageDecoder.decodeBitmap(decode)
                iv_profile.setImageBitmap(bitmap)
            }
            savePhoto(bitmap)
        }
    }
    //갤러리에 저장
    private fun savePhoto(bitmap: Any) {
        val folderPath = Environment.getExternalStorageDirectory().absolutePath + "/Pictures/" //사진 폴더로 저장하기 위한 경로
        val timestamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val fileName = "${timestamp}.jpeg"
        val folder = File(folderPath)
        if(!folder.isDirectory) { //현재 해당 경로에 폴더 존재하는지 검사 (존재하지 않는다면)
            folder.mkdirs() //해당 경로에 폴더 자동으로 생성
        }
        //실제적인 저장처리
        val out = FileOutputStream(folderPath + fileName)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        Toast.makeText(this, "사진이 앨범에 저장되었습니다.", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}