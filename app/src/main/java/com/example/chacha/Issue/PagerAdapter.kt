package com.example.chacha.Issue

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    val mData : ArrayList<Fragment> = ArrayList()

    init {
        mData.add(IssueFragment1())
        mData.add(IssueFragment2())
    }
    override fun getItem(i: Int): Fragment? {
        return mData.get(i)
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "뉴스·연예·스포츠"
            1 -> return "실시간 검색 순위"
        }
        return super.getPageTitle(position)
    }
}
