package com.example.navigationkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_navi.setOnClickListener {
            layout_drawer.openDrawer(GravityCompat.START) //START: left, END: right 의미
        }

        naviView.setNavigationItemSelectedListener (this) //네비 메뉴 아이템 클릭 속성 부여
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean { //네비 메뉴 아이템 클릭 시 수행
        when (item.itemId)
        {
            R.id.access -> Toast.makeText(applicationContext, "접근성", Toast.LENGTH_SHORT).show()
            R.id.email -> Toast.makeText(applicationContext, "이메일", Toast.LENGTH_SHORT).show()
            R.id.message -> Toast.makeText(applicationContext, "메시지", Toast.LENGTH_SHORT).show()
        }
        layout_drawer.closeDrawers() //열린 네비 메뉴 닫기
        return false
    }

    override fun onBackPressed() {
        if(layout_drawer.isDrawerOpen(GravityCompat.START)) //네비가 열려 있을 때
        {
            layout_drawer.closeDrawers() //백버튼 누르면 앱 종료 말고 네비 닫기
        }
        else{ //네비가 열린 상황 이외에선 그냥 백버튼
            super.onBackPressed() //일반 백버튼 기능 실행() (이 구문은 finish 같은 역할)
        }
    }
}