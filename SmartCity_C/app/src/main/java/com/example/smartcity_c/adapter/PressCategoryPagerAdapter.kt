package com.example.smartcity_c.adapter

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

class PressCategoryPagerAdapter(private val viewList: MutableList<View>,
                                private val title: MutableList<String>) : PagerAdapter() {
    override fun getCount() = viewList.size
    override fun isViewFromObject(view: View, `object`: Any) = view == `object`

    override fun getPageTitle(position: Int) = title[position]

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(viewList[position])
        return viewList[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(viewList[position])
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }
}