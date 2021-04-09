package com.example.smartcity_c.presenter

import android.content.Context
import android.content.SharedPreferences
import com.example.smartcity_c.contract.UpdatePswdContract
import com.example.smartcity_c.network.MM
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONObject

class UpdatePswdPresenter(val context: Context ,val view : UpdatePswdContract.View):UpdatePswdContract.Presenter {
    private val sp: SharedPreferences by lazy {
        context.getSharedPreferences("fistShow", Context.MODE_PRIVATE)
    }
    override fun updatePswd(old: String, new: String) {
        doAsync {
            val body = MM.getData().getInfo("${sp.getString("token","")}").execute().body()
            body?.let {
                val jsonObject = JSONObject()
                jsonObject.put("userId",it.user.userId)
                jsonObject.put("oldPwd",old)
                jsonObject.put("password",new)
                val create = RequestBody.create(
                    "application/json;charset=UTF-8".toMediaTypeOrNull(),
                    jsonObject.toString()
                )
                val updatePswd = MM.getData().updatePswd("${sp.getString("token", "")}", create).execute().body()
                uiThread {
                    context.toast("${updatePswd?.msg}")
                    if (updatePswd?.code==200){
                        view.success()
                    }else{
                        view.failure()
                    }
                }
            }
        }
    }
}