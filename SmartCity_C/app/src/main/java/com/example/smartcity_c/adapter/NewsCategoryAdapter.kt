package com.example.smartcity_c.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartcity_c.R
import com.example.smartcity_c.emp.CategoryEmp
import kotlinx.android.synthetic.main.view_news_category.view.*

class NewsCategoryAdapter(val context: Context, private val newsCategoryItems:MutableList<CategoryEmp> ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate = LayoutInflater.from(context).inflate(R.layout.view_news_category, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.home_listview_title.text = newsCategoryItems[position].title
        holder.itemView.home_listview_desc.text = newsCategoryItems[position].content
        holder.itemView.home_listview_time.text = newsCategoryItems[position].createTime
        holder.itemView.home_listview_pinglun.text = newsCategoryItems[position].likeNumber.toString()
        Glide.with(context).load("http://124.93.196.45:10002${newsCategoryItems[position].imgUrl}").error(R.mipmap.ic_launcher).into(holder.itemView.home_listview_img)
    }

    override fun getItemCount() = newsCategoryItems.size
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}