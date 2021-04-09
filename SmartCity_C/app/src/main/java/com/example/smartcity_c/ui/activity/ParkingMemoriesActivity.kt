package com.example.smartcity_c.ui.activity

import android.graphics.Rect
import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.example.smartcity_c.R
import com.example.smartcity_c.adapter.ParkingMemoriesListAdapter
import com.example.smartcity_c.contract.ParkingMemoriesContract
import com.example.smartcity_c.presenter.ParkingMemoriesPresenter
import kotlinx.android.synthetic.main.activity_parking_memories.*
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat

class ParkingMemoriesActivity : BaseActivity(), ParkingMemoriesContract.View {
    private val presenter by lazy {
        ParkingMemoriesPresenter(this)
    }

    override fun refresh() {
        recyclerview.adapter?.notifyDataSetChanged()
    }

    override fun init() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initView()
        presenter.loadRecyclerData()
        button3.setOnClickListener {
            presenter.loadMoreRecyclerData()
            it.visibility = View.GONE
        }
        timeOnclick(start_time)
        timeOnclick(end_time)
        time_search.setOnClickListener {
            if ((start_time.text.toString() == "请选择") or (end_time.text.toString()=="请选择") ){
                toast("请选择时间")
                return@setOnClickListener
            }

            presenter.searchRecyclerData(start_time.text.toString(),end_time.text.toString())
        }
    }

    private fun timeOnclick( start_time : TextView) {
        start_time.setOnClickListener {
            TimePickerBuilder(this) { date, _ ->
                val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
                start_time.text = simpleDateFormat.format(date)
            }
                .isDialog(true)
                .setType(booleanArrayOf(true, true, true, true, true, false))
                .build()
                .show()
        }
    }

    private fun initView() {
        recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = ParkingMemoriesListAdapter(context,presenter.parkingMemoriesItems)
            addItemDecoration(object : RecyclerView.ItemDecoration(){
                override fun getItemOffsets(
                    outRect: Rect,
                    itemPosition: Int,
                    parent: RecyclerView
                ) {
                    outRect.top = 10
                    outRect.bottom = 10
                    outRect.left = 5
                    outRect.right = 5
                }
            })
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }


    override fun getLayoutResID() = R.layout.activity_parking_memories
}