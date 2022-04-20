package com.example.edittextbuttonkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.edittextbuttonkt.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) { // 액티비티가 최소 실행되면 수행.
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnGetText.setOnClickListener{ // 에딧텍스트에 입력된 값을 가져와 텍스트뷰에 출력
            val resultText = edId.text.toString() //에딧텍스트에 입력된 값
            binding.tvResult.setText(resultText as CharSequence) //입력된 값을 텍스트뷰에 set
        }
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}