package com.example.smartcity_c.ui.activity.livespend

import android.app.AlertDialog
import android.text.method.DigitsKeyListener
import android.view.View
import android.widget.EditText
import com.example.smartcity_c.R
import com.example.smartcity_c.contract.WaterContract
import com.example.smartcity_c.presenter.WaterPresenter
import com.example.smartcity_c.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_water.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

class WaterActivity : BaseActivity(),WaterContract.View {
    val presenter by lazy { WaterPresenter(this,this) }
    override fun init() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.loadData()
        button12.setOnClickListener {
            val editText = EditText(this)
            editText.keyListener = DigitsKeyListener.getInstance("1234567890")
            editText.hint = "请输入1-10000之间的整数"
             AlertDialog.Builder(this).setTitle("缴费金额")
                 .setPositiveButton("确定") { _, _ ->
                    if (editText.text.isEmpty() || editText.text.length > 5) {
                        toast("输入不符合规范")
                        return@setPositiveButton }
                    presenter.startSpend(editText.text.toString().toInt())
                }.setView(editText).show()
        }
    }

    override fun updateView(mDoorNo: Int, mId: Int, mOwnerName: String, mBalance: String) {
        if (mBalance.toInt()>0)
            textView62.visibility = View.GONE
        textView68.text = mId.toString()
        textView69.text = mDoorNo.toString()
        textView70.text = mOwnerName
        textView71.text = mBalance
    }

    override fun getLayoutResID() = R.layout.activity_water
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}