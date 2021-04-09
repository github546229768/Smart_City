package com.example.smartcity_c.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartcity_c.R
import com.example.smartcity_c.emp.ServiceEmp
import com.example.smartcity_c.ui.activity.LiveSpendActivity
import com.example.smartcity_c.ui.activity.ParkingActivity
import com.example.smartcity_c.ui.activity.SmartBusActivity
import kotlinx.android.synthetic.main.view_service_item.view.*
import org.jetbrains.anko.startActivity

class HomeServiceAdapter(var context: Context, private val homeServiceItems:MutableList<ServiceEmp>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate =
            LayoutInflater.from(context).inflate(R.layout.view_service_item, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        viewHolder.itemView.textView4.text = homeServiceItems[position].name
        Glide.with(context).load(homeServiceItems[position].img).thumbnail(0.1f).error(R.drawable.moreservice).into(viewHolder.itemView.imageView2)
        viewHolder.itemView.setOnClickListener {
            when(it.textView4.text.toString()){
                "停车场" -> context.startActivity<ParkingActivity>()
                "智慧巴士" -> context.startActivity<SmartBusActivity>()
                "生活缴费" -> context.startActivity<LiveSpendActivity>()
            }
        }
    }

    override fun getItemCount() = homeServiceItems.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}