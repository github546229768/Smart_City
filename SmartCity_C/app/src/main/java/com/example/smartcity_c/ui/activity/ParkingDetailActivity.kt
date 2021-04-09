package com.example.smartcity_c.ui.activity

import com.bumptech.glide.Glide
import com.example.smartcity_c.R
import com.example.smartcity_c.emp.ParkLotListEmp
import kotlinx.android.synthetic.main.activity_parking_detail.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class ParkingDetailActivity : BaseActivity() {
    override fun init() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        EventBus.getDefault().register(this)

    }

    @Subscribe(sticky = true)
    fun onMessageEvent(parkingListItem : ParkLotListEmp){
        Glide.with(this).load("http://124.93.196.45:10002"+parkingListItem.imgUrl).into(imageView8)
        textView19.text = parkingListItem.parkName
        textView20.text = parkingListItem.address
        tv_distance.text = "<"+parkingListItem.distance+"/km"
        tv_open.text = "对外开放"
        tv_chewei.text = parkingListItem.vacancy
        tv_info.text = "停车费每小时${parkingListItem.rates}元，最高${parkingListItem.priceCaps}元/天"
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun getLayoutResID() = R.layout.activity_parking_detail
}