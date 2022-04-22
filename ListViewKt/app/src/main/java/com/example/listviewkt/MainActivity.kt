package com.example.listviewkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val UserList = arrayListOf<User>(
        User(R.drawable.girl1, "서연", "21", "Hello!"),
        User(R.drawable.girl2, "현정", "15", "Heyy!"),
        User(R.drawable.girl3, "서영", "23", "Hi!"),
        User(R.drawable.girl4, "성은", "17", "Yo!"),
        User(R.drawable.girl5, "신희", "21", "Hoho"),
        User(R.drawable.girl6, "수연", "19", "Yay!!")
    )

    override fun onCreate(savedInstanceState: Bundle?) { // 액티비티의 실행 시작 지점
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val item = arrayOf("apple", "orange", "banana", "melon", "grape")
//        // context는 한 액티비티의 모든 정보를 담고 있음.
//        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, item)

        val Adapter = UserAdapter(this, UserList)
        listView.adapter = Adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectItem = parent.getItemAtPosition(position) as User
            Toast.makeText(this, selectItem.name, Toast.LENGTH_SHORT).show()

        }
    }
}