package com.example.smartcity_c.ui.activity.personal

import android.content.Context
import android.content.SharedPreferences
import android.text.Editable
import android.text.TextWatcher
import com.example.smartcity_c.R
import com.example.smartcity_c.network.MM
import com.example.smartcity_c.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_suggest.*
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONObject

class SuggestActivity : BaseActivity() {
    private val sp: SharedPreferences by lazy {
        getSharedPreferences("fistShow", Context.MODE_PRIVATE)
    }
    override fun init() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        edit_suggest.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                button5.isEnabled = s.toString().isNotEmpty()
            }
        })
        button5.setOnClickListener {
            doAsync {
                val jsonObject = JSONObject()
                jsonObject.put("content", edit_suggest.text.toString())
                val body1 = MM.getData().getInfo("${sp.getString("token","")}").execute().body()
                jsonObject.put("userId", body1?.user?.userId)
                val create = RequestBody.create("application/json;charset=UTF-8".toMediaTypeOrNull(), jsonObject.toString())
                val body = MM.getData().getFeedBack("${sp.getString("token","")}", create).execute().body()
                uiThread {
                    if (body?.string()?.contains("操作成功") == true){
                    toast("提交成功")
                        edit_suggest.text.clear()
                    }
                    else
                        toast("提交失败 ，请检查网络")
                }
            }
        }
    }

    override fun getLayoutResID() = R.layout.activity_suggest
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}