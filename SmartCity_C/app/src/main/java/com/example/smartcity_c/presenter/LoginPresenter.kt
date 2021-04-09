package com.example.smartcity_c.presenter

import android.content.Context
import android.content.SharedPreferences
import com.example.smartcity_c.contract.LoginContract
import com.example.smartcity_c.network.MM
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONObject

class LoginPresenter(val context: Context , val view: LoginContract.View) : LoginContract.Presenter {
    lateinit var token: String
    private val sp: SharedPreferences by lazy {
        context.getSharedPreferences("fistShow", Context.MODE_PRIVATE)
    }
    override fun checkLogin(username: String, paswd: String) {
        doAsync {
            val jsonObject = JSONObject()
            jsonObject.put("username",username)
            jsonObject.put("password",paswd)
            val create = RequestBody.create("application/json;charset=UTF-8".toMediaTypeOrNull(), jsonObject.toString())
            val body = MM.getData().getLogin(create).execute().body()
            uiThread {
                if (body?.code==200){
                    token = body?.token
                    view.success(token)
                }else{
                    context.toast("账号密码错误！")
                    view.failure()
                }
            }
        }
    }
}