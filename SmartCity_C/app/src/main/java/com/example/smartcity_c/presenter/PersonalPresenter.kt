package com.example.smartcity_c.presenter

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.smartcity_c.contract.PersonalContract
import com.example.smartcity_c.network.MM
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.io.File

class PersonalPresenter(val context: Context ,val view: PersonalContract.View) : PersonalContract.Presenter {
    private val sp: SharedPreferences by lazy {
        context.getSharedPreferences("fistShow", Context.MODE_PRIVATE)
    }
     private var nickname : String?=null
     private var img :String?=null
     private var sex :String?=null
     private var iphone:String?=null
     private var carId:String?=null
    override fun saveData(picture: File, idCard : String, nickName : String, phonenumber : String, sexs : String,) {
        doAsync {
            val info = MM.getData().getInfo(sp.getString("token", "").toString()).execute().body()
            val userId = info?.user?.userId
            val partList = mutableListOf<MultipartBody.Part>()
            val create = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), picture)  //文件提交
            val formDataFile = MultipartBody.Part.createFormData("file", picture.name, create)
            partList.add(formDataFile)
            val mapValues = mapOf("userId" to userId.toString(),"idCard" to idCard,"nickName" to nickName,"phonenumber" to phonenumber,"sex" to sex.toString())
           mapValues.forEach{ (v, k) ->
               val formDataValues = MultipartBody.Part.createFormData(v, k);  //参数提交
               partList.add(formDataValues)
           }
            val userUpdate = MM.getData().getUserUpdate(sp.getString("token", "").toString(),partList).execute().body()
           uiThread {
                context.toast(userUpdate?.msg.toString())
               if (userUpdate?.code==200){
                   view.checkUn()
               }
           }
        }
    }

    override fun loadUserData() {
        doAsync {
            val body = MM.getData().getInfo(sp.getString("token","").toString()).execute().body()
            body?.let {
                val body1 = MM.getData().getSystemUser("${sp.getString("token","")}", it.user.userId).execute().body()
                body1?.let { t ->
                    nickname = t.data.nickName
                    img = t.data.avatar
                    sex = t.data.sex
                    iphone = t.data.phonenumber
                    carId = t.data.idCard
                }
            }
            uiThread {
                view.setUserInfo(img.toString(), nickname.toString(), sex.toString(), iphone.toString(), carId.toString())
            }
        }
    }
}