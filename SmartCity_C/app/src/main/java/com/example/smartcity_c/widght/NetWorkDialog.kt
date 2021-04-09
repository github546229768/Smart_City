package com.example.smartcity_c.widght

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.smartcity_c.R
import com.example.smartcity_c.network.IpSetting
import kotlinx.android.synthetic.main.dialog_network.*
import kotlinx.android.synthetic.main.dialog_network.view.*
import org.jetbrains.anko.toast

class NetWorkDialog(context: Context) : Dialog(context) {
    private val sp: SharedPreferences by lazy {
        context.getSharedPreferences("network",Context.MODE_PRIVATE)
    }
    init {
        val inflate = layoutInflater.inflate(R.layout.dialog_network, null)
        setContentView(inflate)
        sp.getString("ip","")?.let {
            inflate.edit_ip.setText(it)
        }
        sp.getString("prot","")?.let {
            inflate.edit_prot.setText(it)
        }
        if (inflate.edit_ip.text.isNotEmpty() || inflate.edit_prot.text.isNotEmpty() ) {
            inflate.edit_ip.isEnabled = false
            inflate.edit_prot.isEnabled = false
        }
        inflate.bt_update.setOnClickListener {
            inflate.edit_ip.isEnabled = true
            inflate.edit_prot.isEnabled = true
        }
        inflate.bt_save.setOnClickListener {
            if (edit_ip.text.isEmpty() && edit_prot.text.isEmpty()) {
                context.toast("不为空！")
                return@setOnClickListener
            }else{
                inflate.edit_ip.isEnabled = false
                inflate.edit_prot.isEnabled = false
                context.toast("设置成功")
                IpSetting.ip = edit_ip.text.toString()+":"+edit_prot.text.toString()
                sp.edit {
                    putString("ip",edit_ip.text.toString())
                    putString("prot",edit_prot.text.toString())
                }
            }
        }
    }
}