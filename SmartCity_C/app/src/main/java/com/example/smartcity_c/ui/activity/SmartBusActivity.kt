package com.example.smartcity_c.ui.activity

import android.view.Menu
import android.view.MenuItem
import com.example.smartcity_c.R
import com.example.smartcity_c.adapter.ExpandListAdapter
import com.example.smartcity_c.contract.SmartBusContract
import com.example.smartcity_c.presenter.SmartBusPresenter
import com.example.smartcity_c.ui.activity.bus.OneStepActivity
import kotlinx.android.synthetic.main.activity_smartbus.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.startActivity

class SmartBusActivity : BaseActivity() ,SmartBusContract.View{
    val presenter by lazy {
        SmartBusPresenter(this)
    }
    private val expandListAdapter by lazy {
        ExpandListAdapter(this,presenter.list)
    }
    override fun init() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.loadData()
        expandListView.setAdapter(expandListAdapter)
        expandListView.setOnGroupClickListener { parent, v, groupPosition, id ->
            startActivity<OneStepActivity>()
            EventBus.getDefault().postSticky(presenter.list[groupPosition])
            false
        }
    }

    override fun refresh() {
        expandListAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.smart_bus_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.order)
            startActivity<BusOrderActivity>()
        return super.onOptionsItemSelected(item)
    }

    override fun getLayoutResID() = R.layout.activity_smartbus

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}