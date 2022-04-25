package com.example.recyclerkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val profileList = arrayListOf(
            Profiles(R.drawable.man, "문수", 27, "안드로이드 앱 개발자"),
            Profiles(R.drawable.woman, "가영", 30, "ios 앱 개발자"),
            Profiles(R.drawable.man, "지민", 26, "디자이너"),
            Profiles(R.drawable.woman, "하은", 34, "웹 퍼블리셔"),
            Profiles(R.drawable.woman, "인영", 32, "클라이언트 개발자"),
            Profiles(R.drawable.woman, "윤희", 28, "백엔드 개발자"),
            Profiles(R.drawable.man, "박현", 29, "서버 개발자")
        )
        rv_profile.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false )
        rv_profile.setHasFixedSize(true) //성능개선
        rv_profile.adapter = ProfileAdapter(profileList)
    }
}