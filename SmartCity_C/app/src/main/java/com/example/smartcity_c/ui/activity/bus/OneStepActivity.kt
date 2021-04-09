package com.example.smartcity_c.ui.activity.bus

import com.example.smartcity_c.R
import com.example.smartcity_c.emp.ExpandGroupEmp
import com.example.smartcity_c.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_one_step.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.startActivity

class OneStepActivity : BaseActivity() {
    lateinit var  expandGroupEmps:ExpandGroupEmp
    override fun init() {
        EventBus.getDefault().register(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        button6.setOnClickListener {
            EventBus.getDefault().postSticky(expandGroupEmps)
            startActivity<TwoStepActivity>()
        }
    }

    @Subscribe(sticky = true)
    fun getEvenBut(expandGroupEmp: ExpandGroupEmp) {
        expandGroupEmps = expandGroupEmp
        (expandGroupEmp.first + "——" + expandGroupEmp.end).also { textView39.text = it }
        ("票价: " + expandGroupEmp.price + "   里程:" + expandGroupEmp.mileage).also { textView40.text = it }
    }

    override fun getLayoutResID() = R.layout.activity_one_step
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }
}