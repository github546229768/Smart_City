package com.example.smartcity_c.ui.activity

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.smartcity_c.MainActivity
import com.example.smartcity_c.R
import com.example.smartcity_c.contract.LoginContract
import com.example.smartcity_c.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity(), LoginContract.View {

    private val sp: SharedPreferences by lazy {
        getSharedPreferences("fistShow", Context.MODE_PRIVATE)
    }
    val presenter by lazy { LoginPresenter(this,this) }
    override fun init() {
        btn_register.setOnClickListener { toast("抱歉，暂不提供注册通道！") }
        btnLogin.setOnClickListener {
            presenter.checkLogin(userName.text.toString(),passwd.text.toString())
        }
    }

    override fun failure() {
    }

    override fun success(token :String) {
        sp.edit { putBoolean("isFirst",false) }
        startActivity<MainActivity>()
        sp.edit { putString("token",token) }
        finish()
    }

    override fun getLayoutResID() = R.layout.activity_login
}