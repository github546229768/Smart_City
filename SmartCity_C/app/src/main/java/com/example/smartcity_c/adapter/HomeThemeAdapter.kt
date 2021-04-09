package com.example.smartcity_c.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartcity_c.R
import com.example.smartcity_c.emp.ServiceEmp
import kotlinx.android.synthetic.main.view_service_item.view.*
import kotlinx.android.synthetic.main.view_theme_item.view.*

class HomeThemeAdapter(var context: Context, private val homeThemeItems:MutableList<ServiceEmp>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate =
            LayoutInflater.from(context).inflate(R.layout.view_theme_item, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        viewHolder.itemView.textView6.text = homeThemeItems[position].name
        Glide.with(context).load(homeThemeItems[position].img).thumbnail(0.1f).error(R.mipmap.ic_launcher).into(viewHolder.itemView.imageView4)
    }

    override fun getItemCount() = homeThemeItems.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}