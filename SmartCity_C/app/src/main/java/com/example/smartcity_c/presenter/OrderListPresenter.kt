package com.example.smartcity_c.presenter

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.ListView
import com.example.smartcity_c.adapter.OrderListAdapter
import com.example.smartcity_c.contract.OrderListContract
import com.example.smartcity_c.emp.OrderEmp
import com.example.smartcity_c.network.MM
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.progressDialog
import org.jetbrains.anko.uiThread

class OrderListPresenter(val context: Context, val view: OrderListContract.View) : OrderListContract.Presenter {
    val viewList = mutableListOf<View>()
    val title = mutableListOf<String>()
//    private val listItems = mutableListOf<OrderEmp>()
    private val listItems2 = mutableListOf<OrderEmp>()
    private val listItems3 = mutableListOf<OrderEmp>()
    private val titles = arrayOf("全部订单", "已支付", "未支付")
    private val sp: SharedPreferences by lazy {
        context.getSharedPreferences("fistShow", Context.MODE_PRIVATE)
    }
    override fun initPagerData() {
        for (i in 0..2){
            initData(i)
        }
    }
    private fun initData(i: Int) {
        val listItems = mutableListOf<OrderEmp>()
        doAsync {
            val body = MM.getData().getOrdersList("${sp.getString("token", "")}").execute().body()
            body?.let {
                it.data.forEach { t ->
                    listItems.add(OrderEmp(t.orderNum, t.status, t.createTime))
                }
                uiThread {
                    val listView = ListView(context)
                    when (i) {
                        0 -> {
                            listView.adapter = OrderListAdapter(context, listItems)
                        }
                        1 -> {
                            for (i in 0 until listItems.size) {
                                if (listItems[i].status == 0) {
                                    listItems2.add(listItems[i])
                                }
                            }
                            listView.adapter = OrderListAdapter(context, listItems2)
                        }
                    2 -> {
                        for (i in 0 until listItems.size){
                            if (listItems[i].status == 1){
                                listItems3.add(listItems[i])
                            }
                        }
                        listView.adapter = OrderListAdapter(context, listItems3)
                    }
                    }
                    viewList.add(listView)
                    title.add(titles[i])
                    view.refresh()
                }
            }
        }


    }

}