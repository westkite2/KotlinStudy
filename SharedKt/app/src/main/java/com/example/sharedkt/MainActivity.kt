package com.example.sharedkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { //액티비티 처음 실행 시 한 번 수행 (초기화)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //1000: 저장된 데이터를 로드
        loadData() //edit text 저장되어 있던 값을 setText
    }

    private fun loadData() {
        val pref = getSharedPreferences("pref", 0) //내부 폴더 경로에 pref 이름으로 저장 (저장 옵션 0)
        et_hello.setText(pref.getString("name", "")) //1번 인자는 저장 당시의 키 값, 2번 인자는 키 값에 데이터 없을 경우의 대체값
    }

    private fun saveData() {
        val pref = getSharedPreferences("pref", 0) //내부 폴더 경로에 pref 이름으로 저장 (저장 옵션 0)
        val edit = pref.edit() //수정모드
        edit.putString("name", et_hello.text.toString()) //1번 인자는 키 값, 2번 인자는 실제 담아둘 값
        edit.apply() //값 저장 완료
    }

    override fun onDestroy() { //액티비티 종료 시점이 다가올 때 호출
        super.onDestroy()

        saveData() //edit text 값을 저장
    }
}