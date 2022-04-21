package com.example.intentkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnA.setOnClickListener{
            // var: 값 변경 가능한 변수
            // val: 값 변경 불가 (자바의 final)

            val intent = Intent(this, SubActivity::class.java) //다음 화면으로 이동하기 위한 인텐트 객체 생성.
            intent.putExtra("msg", tvSendMsg.text.toString()) // 텍스트 값(hello world)을 담은 뒤 msg라는 키로 잠그기.
            startActivity(intent) //intent에 저장된 액티비티 쪽으로 이동.
            finish() //자기 자신 액티비티를 파괴. (sub 화면으로 간 뒤에 뒤로가기를 하면 main 화면이 안 나오고 앱 종료)
        }
    }
}