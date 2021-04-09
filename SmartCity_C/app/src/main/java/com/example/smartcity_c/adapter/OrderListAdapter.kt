package com.example.smartcity_c.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.smartcity_c.R
import com.example.smartcity_c.emp.OrderEmp
import kotlinx.android.synthetic.main.item_view_order.view.*
import org.jetbrains.anko.toast

class OrderListAdapter(val context: Context , val list : MutableList<OrderEmp>) : BaseAdapter() {
    override fun getCount() = list.size

    override fun getItem(position: Int) = list[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflate = LayoutInflater.from(context).inflate(R.layout.item_view_order, null)
        inflate.textView30.text = "订单编号："+list[position].orderNum
        if (list[position].status == 0 ){
        inflate.textView31.text = "已支付"
        inflate.textView31.setTextColor(Color.GREEN)
        }
        else{
        inflate.textView31.text = "未支付"
        inflate.textView31.setTextColor(Color.RED)}
        inflate.textView32.text = list[position].createTime
        inflate.setOnClickListener { context.toast("完成跳转!") }
        return inflate
    }
}