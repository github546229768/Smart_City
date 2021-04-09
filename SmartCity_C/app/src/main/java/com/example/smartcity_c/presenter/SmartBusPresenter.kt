package com.example.smartcity_c.presenter

import com.example.smartcity_c.contract.SmartBusContract
import com.example.smartcity_c.emp.ExpandGroupEmp
import com.example.smartcity_c.network.MM
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SmartBusPresenter(val view : SmartBusContract.View): SmartBusContract.Presenter {
    val list  = mutableListOf<ExpandGroupEmp>()

    override fun loadData() {
        doAsync {
            val body = MM.getData().getLinesList().execute().body()
            body?.let { it.rows.forEach { t ->
                val busStopList = MM.getData().getBusStopList(t.id).execute().body()
                val listChild  = mutableListOf<String>()
                busStopList?.rows?.forEach { n ->
                    listChild.add(n.name)
                }
                list.add(ExpandGroupEmp(t.name,t.first,t.end,t.startTime,t.endTime,t.price,t.mileage,listChild))

            } }
            uiThread { view.refresh() }
        }

    }
}