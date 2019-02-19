package com.example.chacha.Issue

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.ViewPager
import com.example.chacha.R
import kotlinx.android.synthetic.main.snippet_top_issuetoolbar.*

class IssueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issue)

        var viewPager : ViewPager = findViewById(R.id.fragment_container)
        var adapter = PagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter

        var tablLayout : TabLayout = findViewById(R.id.tabLayout)
        tablLayout.setupWithViewPager(viewPager)

        ivBackArrow.setOnClickListener {
            finish()
        }
    }
}

