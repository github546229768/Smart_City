package com.example.smartcity_c.ui.activity

import com.example.smartcity_c.R
import com.example.smartcity_c.adapter.OrderListPagerAdapter
import com.example.smartcity_c.contract.BusOrderContract
import com.example.smartcity_c.presenter.BusOrderPresenter
import kotlinx.android.synthetic.main.activity_bus_order.*

class BusOrderActivity : BaseActivity(), BusOrderContract.View {
    val presenter  by lazy {
        BusOrderPresenter(this,this)
    }
    override fun init() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tabLayout2.setupWithViewPager(viewpager)
        presenter.loadData()
        viewpager.adapter = OrderListPagerAdapter(presenter.viewList, arrayListOf("待支付","已支付"))
    }

    override fun refresh() {
        viewpager.adapter?.notifyDataSetChanged()
    }

    override fun getLayoutResID() = R.layout.activity_bus_order
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
