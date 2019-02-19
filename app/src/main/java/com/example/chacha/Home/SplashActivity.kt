package com.example.chacha.Home

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import com.example.chacha.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startLoading()

        var str = "차차차"
        var sb = SpannableStringBuilder(str)
        sb.setSpan(ForegroundColorSpan(Color.parseColor("#81e855")),0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        sb.setSpan(ForegroundColorSpan(Color.parseColor("#db1551")),1,2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        sb.setSpan(ForegroundColorSpan(Color.parseColor("#efef09")),2,3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        text.text = sb
    }

    fun startLoading() {
        val handler = Handler()
        handler.postDelayed(Runnable { finish() }, 1500)
    }
}
