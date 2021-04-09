package com.example.smartcity_c.presenter

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.ExpandableListView
import com.example.smartcity_c.adapter.BusOrderExpandListAdapter
import com.example.smartcity_c.adapter.ExpandListAdapter
import com.example.smartcity_c.contract.BusOrderContract
import com.example.smartcity_c.contract.DomicileContract
import com.example.smartcity_c.emp.ExpandGroupEmp
import com.example.smartcity_c.network.MM
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONObject

class DomicilePresenter(val context: Context, val view: DomicileContract.View) : DomicileContract.Presenter {
    private val sp: SharedPreferences by lazy {
        context.getSharedPreferences("fistShow", Context.MODE_PRIVATE)
    }
    override fun saveData(group: String, water: String, domicile: String) {
        doAsync {
            val info = MM.getData().getInfo(sp.getString("token", "").toString()).execute().body()
            info?.user?.userId?.let {
                val houseHolder = MM.getData().getHouseHolder(sp.getString("token", "").toString(), it).execute().body()
                val jsonObject = JSONObject()
                jsonObject.put("userId",it)
                jsonObject.put("doorId", houseHolder?.data?.doorNo)
                if (water=="水费")
                    jsonObject.put("classifyId",2)
                else
                    jsonObject.put("classifyId",1)

                val create = RequestBody.create("application/json;charset=UTF-8".toMediaTypeOrNull(), jsonObject.toString())
                val relations = MM.getData().getRelations(sp.getString("token", "").toString(),create).execute().body()
                uiThread {
                    context.toast(relations?.msg.toString())
                    if (relations?.code==200){
                        view.checkButton()
                    }
                }
            }

        }
    }
}