package com.example.smartcity_c.presenter

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.ExpandableListView
import com.example.smartcity_c.adapter.BusOrderExpandListAdapter
import com.example.smartcity_c.adapter.ExpandListAdapter
import com.example.smartcity_c.contract.BusOrderContract
import com.example.smartcity_c.contract.WaterContract
import com.example.smartcity_c.emp.ExpandGroupEmp
import com.example.smartcity_c.network.MM
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONObject

class WaterPresenter(val context: Context, val view: WaterContract.View) : WaterContract.Presenter {
    var mDoorNo :Int = 0
    var mId :Int = 0
    var mOwnerName:String?= null
    var mBalance:String?= null
    var mUserId :Int = 0
    private val sp: SharedPreferences by lazy {
        context.getSharedPreferences("fistShow", Context.MODE_PRIVATE)
    }

    override fun startSpend(balance:Int) {
        doAsync {
            val jsonObject = JSONObject()
            jsonObject.put("balance",balance)
            jsonObject.put("classifyId",2)
            jsonObject.put("doorNo",mDoorNo)
            jsonObject.put("id",mId)
            jsonObject.put("userId",mUserId)
            val create = ResponseBody.create("application/json;charset=UTF-8".toMediaTypeOrNull(), jsonObject.toString())
            val putHouseHolder = MM.getData().putHouseHolder(sp.getString("token", "").toString(), create).execute().body()
            uiThread {
                context.toast(putHouseHolder?.msg.toString())
                if (putHouseHolder?.code==200){
//                    view.checkButton()
                }
            }
        }
    }

    override fun loadData() {
        doAsync {
            val info = MM.getData().getInfo(sp.getString("token", "").toString()).execute().body()
            mUserId = info!!.user.userId
            val houseHolder = MM.getData().getHouseHolder(sp.getString("token", "").toString(), mUserId).execute().body()
            houseHolder?.let {
                mDoorNo = it.data.doorNo
                mId = it.data.id
                mOwnerName = it.data.ownerName
                mBalance = it.data.balance
            }
            uiThread { view.updateView(mDoorNo,mId, mOwnerName.toString(), mBalance.toString()) }
        }
    }

}