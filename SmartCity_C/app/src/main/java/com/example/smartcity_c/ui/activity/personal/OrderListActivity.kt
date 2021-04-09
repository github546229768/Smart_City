package com.example.smartcity_c.ui.activity.personal

import com.example.smartcity_c.R
import com.example.smartcity_c.adapter.OrderListPagerAdapter
import com.example.smartcity_c.contract.OrderListContract
import com.example.smartcity_c.presenter.OrderListPresenter
import com.example.smartcity_c.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_order_list.*

class OrderListActivity : BaseActivity(), OrderListContract.View {
    val presenter  by lazy { OrderListPresenter(this,this) }
    override fun init() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tabLayout.setupWithViewPager(viewpager)
        viewpager.adapter = OrderListPagerAdapter(presenter.viewList,presenter.title)
        presenter.initPagerData()
    }

    override fun showDialogs() {
        showDialog()
    }

    override fun dismissDialogs() {
        dismissDialog()
    }

    override fun refresh() {
        viewpager.adapter?.notifyDataSetChanged()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun getLayoutResID() = R.layout.activity_order_list
}