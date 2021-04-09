package com.example.smartcity_c.ui.activity.bus

import android.widget.ArrayAdapter
import com.example.smartcity_c.R
import com.example.smartcity_c.emp.ExpandGroupEmp
import com.example.smartcity_c.ui.activity.BaseActivity
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import kotlinx.android.synthetic.main.activity_one_step.*
import kotlinx.android.synthetic.main.activity_two_step.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.startActivity

class TwoStepActivity : BaseActivity() {
    override fun init() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var blag = true
        val list = mutableListOf<String>()
        val list_null = mutableListOf<String>()
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, list)
        listview.adapter = arrayAdapter
        calendarview.setOnCalendarMultiSelectListener(object : CalendarView.OnCalendarMultiSelectListener {
            override fun onCalendarMultiSelectOutOfRange(calendar: Calendar?) {

            }

            override fun onMultiSelectOutOfSize(calendar: Calendar?, maxSize: Int) {
            }

            override fun onCalendarMultiSelect(calendar: Calendar, curSize: Int, maxSize: Int) {
                val desc = "${calendar.year}-${calendar.month}-${calendar.day}"

//                for (i in list.indices) {
//                    if (list[i] == desc) {
//                        list.removeAt(i)
//                        list_null.add(desc)
//                    }
//                }
//                list.forEachIndexed { index, s ->
//                    if (list[index]==desc){
//                        list.removeAt(index)
//                        list_null.add(desc)
//                    }
//                }
                list.add(desc)
                list.removeAll(list_null)
                list_null.clear()
                arrayAdapter.notifyDataSetChanged()
            }
        })

        button7.setOnClickListener {
            EventBus.getDefault().postSticky(list)
            startActivity<ThereStepActivity>()
        }
    }


    override fun getLayoutResID() = R.layout.activity_two_step
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}