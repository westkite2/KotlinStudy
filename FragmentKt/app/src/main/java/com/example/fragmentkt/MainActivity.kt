package com.example.fragmentkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //var a : Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //a = 2
        /*
        if(a == 2){
            Log.d("if", "Enter")
            Log.e("if", "Enter") //error (marked red)
        }
        else if(a == 1){
            Log.d("else if", "enter")
        }
        */
        //Log.d("var a", a.toString())

        setFrag(0)
        btn_fragment1.setOnClickListener{
            setFrag(0)
        }
        btn_fragment2.setOnClickListener{
            setFrag(1)
        }
        btn_fragment3.setOnClickListener{
            setFrag(2)
        }
    }

    private fun setFrag(fragNum: Int) {
        val ft = supportFragmentManager.beginTransaction()
        when(fragNum)
        {
            0 -> {
                ft.replace(R.id.main_frame, Fragment1()).commit()
            }
            1 -> {
                ft.replace(R.id.main_frame, Fragment2()).commit()
            }
            2 -> {
                ft.replace(R.id.main_frame, Fragment3()).commit()
            }
        }
    }
}