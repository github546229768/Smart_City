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
import com.example.smartcity_c.ui.activity.livespend.DomicileActivity
import com.example.smartcity_c.ui.activity.livespend.ElectricityActivity
import com.example.smartcity_c.ui.activity.livespend.WaterActivity
import kotlinx.android.synthetic.main.view_live_spend_item.view.*
import kotlinx.android.synthetic.main.view_parking_list_item.view.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.startActivity

class LiveSpendListAdapter(var context: Context, private val liveSpendItems:MutableList<MutableMap<String,Any>>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate = LayoutInflater.from(context).inflate(R.layout.view_live_spend_item, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemView = holder.itemView
        liveSpendItems[position]?.let { itemView.imageView16.setImageResource(liveSpendItems[position]["img"] as Int) }
        itemView.textView48.text = liveSpendItems[position]["name"].toString()
        itemView.setOnClickListener {
            if (it.textView48.text == "户号管理"){
                context.startActivity<DomicileActivity>()
            }
            if (it.textView48.text == "水费"){
                context.startActivity<WaterActivity>()
            }
            if (it.textView48.text == "电费"){
                context.startActivity<ElectricityActivity>()
            }
        }
    }

    override fun getItemCount() = liveSpendItems.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}