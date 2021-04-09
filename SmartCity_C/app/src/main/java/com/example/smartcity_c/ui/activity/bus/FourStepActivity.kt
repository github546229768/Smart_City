package com.example.smartcity_c.ui.activity.bus

import android.content.Context
import android.content.SharedPreferences
import com.example.smartcity_c.MainActivity
import com.example.smartcity_c.R
import com.example.smartcity_c.emp.EventBusEmp
import com.example.smartcity_c.emp.ExpandGroupEmp
import com.example.smartcity_c.network.MM
import com.example.smartcity_c.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_four_step.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONObject

class FourStepActivity : BaseActivity() {
    var price  = ""
    var path  = ""
    var start  = ""
    var end  = ""
    private val sp: SharedPreferences by lazy {
        getSharedPreferences("fistShow", Context.MODE_PRIVATE)
    }
    override fun init() {
        EventBus.getDefault().register(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        button8.setOnClickListener {
            doAsync {
                val jsonObject = JSONObject()
                jsonObject.put("start", start)
                jsonObject.put("end", end)
                jsonObject.put("userName", edit_name.text.toString())
                jsonObject.put("userTel", edit_phone.text.toString())
                jsonObject.put("price", price)
                jsonObject.put("path", path)
                jsonObject.put("status", "0")
                val body1 = MM.getData().getInfo("${sp.getString("token","")}").execute().body()
                jsonObject.put("userId", body1?.user?.userId)
                val create = RequestBody.create("application/json;charset=UTF-8".toMediaTypeOrNull(), jsonObject.toString())
                val body = MM.getData().getBusOrders("${sp.getString("token","")}", create).execute().body()
                uiThread {
                    if (body?.string()?.contains("操作成功") == true) {
                        toast("提价成功")
                        startActivity<MainActivity>()
                        finish()
                    }
                    else
                        toast("提交错误")
                }
            }
        }
    }

    override fun getLayoutResID() = R.layout.activity_four_step


    @Subscribe(sticky = true)
    fun getEvenBut(expandGroupEmp: ExpandGroupEmp) {
        (expandGroupEmp.first + "——" + expandGroupEmp.end).also { textView41.text = "乘车路线：$it" }
        price = expandGroupEmp.price.toString()
        path = expandGroupEmp.name.toString()
        start = expandGroupEmp.first.toString()
        end = expandGroupEmp.end.toString()
    }

    @Subscribe(sticky = true)
    fun getEmp(eventBusEmp: EventBusEmp) {
        edit_name.text =  eventBusEmp.name
        edit_phone.text = eventBusEmp.phone
        edit_start.text =  eventBusEmp.start
        edit_end.text =  eventBusEmp.end

    }

    @Subscribe(sticky = true)
    fun getEmp(list: MutableList<String>) {
        var desc = " "
        list.forEach { desc += "$it," }
        editTextTextPersonName5.text = desc
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }
}