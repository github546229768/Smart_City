package com.example.smartcity_c.ui.activity.personal

import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import com.example.smartcity_c.R
import com.example.smartcity_c.adapter.TextChangedListenerAdapter
import com.example.smartcity_c.contract.UpdatePswdContract
import com.example.smartcity_c.presenter.UpdatePswdPresenter
import com.example.smartcity_c.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_update_password.*
import kotlinx.android.synthetic.main.activity_update_password.bt_update
import kotlinx.android.synthetic.main.dialog_network.*
import org.jetbrains.anko.toast

class UpdatePasswordActivity : BaseActivity(),UpdatePswdContract.View {
    val blag = booleanArrayOf(false,false,false)
    val presenter by lazy {
        UpdatePswdPresenter(this,this)
    }
    override fun init() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        bt_update.setOnClickListener {
            if (new_paswd.text.toString() != agan_paswd.text.toString()){
                toast("密码不一致!")
                return@setOnClickListener
            }else{
                presenter.updatePswd(old_paswd.text.toString(),new_paswd.text.toString())
                it.isEnabled = false
            }
        }
        old_paswd.addTextChangedListener(object : TextChangedListenerAdapter(){
            override fun afterTextChanged(s: Editable?) {
                blag[0] = s.toString().isNotEmpty()
                bt_update.isEnabled = true
                blag.forEach { if (!it) bt_update.isEnabled = false }
            }
        })
        new_paswd.addTextChangedListener(object : TextChangedListenerAdapter(){
            override fun afterTextChanged(s: Editable?) {
                blag[1] = s.toString().isNotEmpty()
                bt_update.isEnabled = true
                blag.forEach { if (!it) bt_update.isEnabled = false}
            }
        })
        agan_paswd.addTextChangedListener(object : TextChangedListenerAdapter(){
            override fun afterTextChanged(s: Editable?) {
                blag[2] = s.toString().isNotEmpty()
                bt_update.isEnabled = true
                blag.forEach { if (!it) bt_update.isEnabled = false}
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun getLayoutResID()  = R.layout.activity_update_password
    override fun failure() {
        bt_update.isEnabled  = false
    }

    override fun success() {
        bt_update.isEnabled  = false
        old_paswd.text.clear()
        new_paswd.text.clear()
        agan_paswd.text.clear()
    }
}