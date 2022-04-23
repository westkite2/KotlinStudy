package com.example.webviewkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.settings.javaScriptEnabled = true //자바 스크립트 허용
        /* 웹뷰에서 새 창이 뜨지 않도록 방지하는 구문. (디폴트 브라우저로 새로 띄움). 내부에서 뜨도록. */
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        webView.loadUrl("https://www.naver.com") //링크 주소를 load 함

    }

    override fun onBackPressed() { //백버튼 선택 시 수행
        if(webView.canGoBack())
        { //웹사이트에서 뒤로 갈 페이지 존재 시.
            webView.goBack() //웹사이트 뒤로가기 수행
        }
        else
        {
            super.onBackPressed() // 안드로이드 본래의 백버튼 기능 수행
        }

    }
}