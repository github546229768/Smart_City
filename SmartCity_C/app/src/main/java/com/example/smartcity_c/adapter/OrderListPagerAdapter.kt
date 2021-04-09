package com.example.smartcity_c.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.viewpager.widget.PagerAdapter

class OrderListPagerAdapter( private val viewList : MutableList<View>,private val title : MutableList<String> ) : PagerAdapter() {
    override fun getCount() = viewList.size
    override fun getPageTitle(position: Int): CharSequence? = title[position]
    override fun isViewFromObject(view: View, `object`: Any) = view == `object`
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(viewList[position])
        return viewList[position]
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(viewList[position])
    }
}
