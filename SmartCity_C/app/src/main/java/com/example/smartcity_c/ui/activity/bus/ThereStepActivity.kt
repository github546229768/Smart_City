package com.example.smartcity_c.ui.activity.bus

import com.example.smartcity_c.R
import com.example.smartcity_c.emp.EventBusEmp
import com.example.smartcity_c.emp.ExpandGroupEmp
import com.example.smartcity_c.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_one_step.*
import kotlinx.android.synthetic.main.activity_there_step.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ThereStepActivity : BaseActivity() {
    override fun init() {
        EventBus.getDefault().register(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        button8.setOnClickListener {
            when {
                edit_name.text.toString().isEmpty() -> {toast("不能为空！")
                    return@setOnClickListener}
                edit_phone.text.toString().isEmpty() -> {toast("不能为空！")
                    return@setOnClickListener}
                edit_start.text.toString().isEmpty() -> {toast("不能为空！")
                    return@setOnClickListener}
                edit_end.text.toString().isEmpty() -> {toast("不能为空！")
                    return@setOnClickListener}
            }
            EventBus.getDefault().postSticky(EventBusEmp(edit_name.text.toString(),edit_phone.text.toString(),edit_start.text.toString(),edit_end.text.toString()))
            startActivity<FourStepActivity>()
        }
    }

    @Subscribe(sticky = true)
    fun getEvenBut(expandGroupEmp: ExpandGroupEmp) {
        (expandGroupEmp.first + "——" + expandGroupEmp.end).also { textView41.text = "乘车路线：\u3000\u3000 $it" }
    }

    override fun getLayoutResID() = R.layout.activity_there_step

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }
}