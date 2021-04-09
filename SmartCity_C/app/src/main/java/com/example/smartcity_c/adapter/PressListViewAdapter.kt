package com.example.smartcity_c.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.example.smartcity_c.R
import com.example.smartcity_c.emp.CategoryEmp
import com.example.smartcity_c.emp.NewsEmp
import kotlinx.android.synthetic.main.view_news_category.view.*

class PressListViewAdapter(val context: Context, private val newsCategoryItems : MutableList<CategoryEmp>) : BaseAdapter() {
    override fun getCount() = newsCategoryItems.size

    override fun getItem(position: Int) = newsCategoryItems[position]

    override fun getItemId(position: Int)= position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflate = LayoutInflater.from(context).inflate(R.layout.view_news_category, parent, false)
        inflate.home_listview_title.text = newsCategoryItems[position].title
        inflate.home_listview_desc.text = newsCategoryItems[position].content
        inflate.home_listview_time.text = newsCategoryItems[position].createTime
        inflate.home_listview_pinglun.text = newsCategoryItems[position].likeNumber.toString()
        Glide.with(context).load("http://124.93.196.45:10002${newsCategoryItems[position].imgUrl}").error(R.mipmap.ic_launcher).into(inflate.home_listview_img)
        return inflate
    }
}