package com.example.smartcity_c.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartcity_c.R
import com.example.smartcity_c.emp.ParkLotListEmp
import com.example.smartcity_c.ui.activity.ParkingDetailActivity
import kotlinx.android.synthetic.main.view_parking_list_item.view.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.startActivity

class ParkingListAdapter(var context: Context, private val parkingListItem:MutableList<ParkLotListEmp>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object{
        const val TYPE_CONTENT =0
        const val TYPE_FOOTER = 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == parkingListItem.size)
            return TYPE_FOOTER
        return TYPE_CONTENT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_FOOTER){
            val inflater = LayoutInflater.from(context).inflate(R.layout.view_parking_foot_list_item, parent, false)
            return ViewHolder(inflater)
        }
        val inflate = LayoutInflater.from(context).inflate(R.layout.view_parking_list_item, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        Glide.with(context).load("http://124.93.196.45:10002" + parkingListItem[position].imgUrl).error(R.mipmap.ic_launcher).into(viewHolder.itemView.imageView6)
        viewHolder.itemView.textView11.text = parkingListItem[position].parkName
        viewHolder.itemView.textView14.text = parkingListItem[position].vacancy
        viewHolder.itemView.textView12.text = parkingListItem[position].address
        viewHolder.itemView.textView17.text = parkingListItem[position].rates+"/小时"
        viewHolder.itemView.textView15.text = "<"+parkingListItem[position].distance+"/km"
        viewHolder.itemView.setOnClickListener {
            context.startActivity<ParkingDetailActivity>()
            EventBus.getDefault().postSticky(parkingListItem[position])
        }
    }

    override fun getItemCount() = parkingListItem.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}