package com.example.listviewkt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class UserAdapter(val context: Context, val UserList: ArrayList<User>) : BaseAdapter() {
    override fun getCount(): Int {
        return UserList.size
    }

    override fun getItem(position: Int): Any {
        // 어떤 아이템을 끌고 올 것인가.
        return UserList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // listview에서 뷰를 가져왔을 때 어떻게 뿌려줄 것인지.
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_user, null)

        val profile = view.findViewById<ImageView>(R.id.ivProfile) // id로부터 뷰를 찾기.
        val name = view.findViewById<TextView>(R.id.tvName)
        val age = view.findViewById<TextView>(R.id.tvAge)
        val greet = view.findViewById<TextView>(R.id.tvGreet)

        val user = UserList[position]
        profile.setImageResource(user.profile)
        name.text = user.name
        age.text = user.age
        greet.text = user.greet

        return view

    }
}