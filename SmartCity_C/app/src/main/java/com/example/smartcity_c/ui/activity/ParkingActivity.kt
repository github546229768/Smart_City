package com.example.smartcity_c.ui.activity

import android.graphics.Rect
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartcity_c.R
import com.example.smartcity_c.adapter.ParkingListAdapter
import com.example.smartcity_c.contract.ParkingContract
import com.example.smartcity_c.presenter.ParkingPresenter
import kotlinx.android.synthetic.main.activity_paking.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.startActivity

class ParkingActivity : BaseActivity() ,ParkingContract.View{

    override fun getLayoutResID() =R.layout.activity_paking

    val presenter by lazy { ParkingPresenter(this) }

    override fun init() {
        initView()
        presenter.loadRecyclerData()
        button2.setOnClickListener { presenter.loadMoreRecyclerData()
            it.visibility = View.GONE
        }
    }

    private fun initView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = ParkingListAdapter(context, presenter.parkingListItems)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
                    outRect.top = 10
                    outRect.bottom = 10
                    outRect.left = 5
                    outRect.right = 5
                }
            })
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.parking_list,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.list) {
            startActivity<ParkingMemoriesActivity>()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun refresh() {
        recyclerview.adapter?.notifyDataSetChanged()
    }


}


