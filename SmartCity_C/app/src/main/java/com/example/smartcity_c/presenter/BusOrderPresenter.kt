package com.example.smartcity_c.presenter

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.ExpandableListView
import com.example.smartcity_c.adapter.BusOrderExpandListAdapter
import com.example.smartcity_c.adapter.ExpandListAdapter
import com.example.smartcity_c.contract.BusOrderContract
import com.example.smartcity_c.emp.ExpandGroupEmp
import com.example.smartcity_c.network.MM
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class BusOrderPresenter(val context: Context , val view: BusOrderContract.View) : BusOrderContract.Presenter {
    val viewList = mutableListOf<View>()
    private val listItems = mutableListOf<ExpandGroupEmp>()
    private val listItems2 = mutableListOf<ExpandGroupEmp>()
    private val expandableListView by lazy { ExpandableListView(context) }
    private val expandableListView2 by lazy { ExpandableListView(context) }
    private val sp: SharedPreferences by lazy {
        context.getSharedPreferences("fistShow", Context.MODE_PRIVATE)
    }
    override fun loadData() {
        viewList.add(expandableListView)
        viewList.add(expandableListView2)
        initExpandView()
    }

    private fun initExpandView() {
        expandableListView.setGroupIndicator(null)
        expandableListView2.setGroupIndicator(null)
        val busOrderExpandListAdapter = BusOrderExpandListAdapter(context, listItems)
        val busOrderExpandListAdapter2 = BusOrderExpandListAdapter(context, listItems2)
        expandableListView.setAdapter(busOrderExpandListAdapter)
        expandableListView2.setAdapter(busOrderExpandListAdapter2)
        doAsync {
            val body = MM.getData().getBusOrdersList("${sp.getString("token","")}").execute().body()
            body?.let { it.rows.forEach { t->
                if (0 == t.status){
                    listItems.add(ExpandGroupEmp(t.path+t.id,t.start,t.end,null,null,t.price, t.orderNum, mutableListOf()))
                }else {
                listItems2.add(ExpandGroupEmp(t.path+t.id,t.start,t.end,null,null,t.price, t.orderNum, mutableListOf()))
            }
            } }
            uiThread {
                busOrderExpandListAdapter.notifyDataSetChanged()
                busOrderExpandListAdapter2.notifyDataSetChanged()
            }
        }

    }
}