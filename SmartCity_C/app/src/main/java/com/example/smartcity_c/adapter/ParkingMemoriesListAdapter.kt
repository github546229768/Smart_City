package com.example.smartcity_c.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartcity_c.R
import com.example.smartcity_c.emp.ParkingMemoriesEmp
import com.example.smartcity_c.emp.ServiceEmp
import kotlinx.android.synthetic.main.view_parking_memories_item.view.*

class ParkingMemoriesListAdapter(var context: Context, private val parkingMemoriesItems:MutableList<ParkingMemoriesEmp>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate =
            LayoutInflater.from(context).inflate(R.layout.view_parking_memories_item, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        viewHolder.itemView.textView21.text = parkingMemoriesItems[position].plateNumber
        viewHolder.itemView.textView22.text = parkingMemoriesItems[position].parkName
        viewHolder.itemView.textView25.text = "${parkingMemoriesItems[position].entryTime} - ${parkingMemoriesItems[position].outTime}"
        viewHolder.itemView.textView28.text = parkingMemoriesItems[position].monetary+"￥"
    }

    override fun getItemCount() = parkingMemoriesItems.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}