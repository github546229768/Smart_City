package com.example.smartcity_c.ui.activity.livespend

import com.example.smartcity_c.R
import com.example.smartcity_c.ui.activity.BaseActivity

class ElectricityActivity : BaseActivity() {
    override fun init() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun getLayoutResID() = R.layout.activity_electricit
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}