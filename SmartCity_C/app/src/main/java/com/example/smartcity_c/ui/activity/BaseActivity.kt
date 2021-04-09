package com.example.smartcity_c.ui.activity

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.greenrobot.eventbus.EventBus

abstract class BaseActivity : AppCompatActivity() {
    private val progressDialog by lazy { ProgressDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResID()) //layout布局
        init() //初始化
    }

    open fun showDialog(){
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.show()
    }

    open fun dismissDialog(){
        progressDialog.dismiss()
    }

    abstract  fun init()

    abstract fun getLayoutResID(): Int
}