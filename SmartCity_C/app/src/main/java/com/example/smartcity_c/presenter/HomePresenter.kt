package com.example.smartcity_c.presenter

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartcity_c.adapter.NewsCategoryAdapter
import com.example.smartcity_c.adapter.PressListViewAdapter
import com.example.smartcity_c.contract.HomeContract
import com.example.smartcity_c.emp.CategoryEmp
import com.example.smartcity_c.emp.NewsEmp
import com.example.smartcity_c.emp.ServiceEmp
import com.example.smartcity_c.network.MM
import com.example.smartcity_c.ui.fragment.NewsFragment
import com.example.smartcity_c.ui.fragment.ShowNewsFragment
import kotlinx.coroutines.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.uiThread

class HomePresenter(val view: HomeContract.View, val context: Context) : HomeContract.Presenter {

    val imageItems = mutableListOf<String>()
    val appItems = mutableListOf<ServiceEmp>()
    val themeItems = mutableListOf<ServiceEmp>()
    val viewListItems = mutableListOf<View>()
    val title = mutableListOf<String>()
    override fun loadPressCategoryTitle() {
        doAsync {
            val pressCategory = MM.getData().getPressCategory().execute().body()
            pressCategory?.let {
                it.data.forEach { t ->
                    title.add(t.dictLabel)
                    val pressList = MM.getData().getPressList(t.dictCode).execute().body()
                    val recyclerView = RecyclerView(context)
                     val categoryItems = mutableListOf<CategoryEmp>()
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                        adapter = NewsCategoryAdapter(context, categoryItems)
                    }
                    recyclerView.adapter?.notifyDataSetChanged()
//                    recyclerView.adapter?.notifyItemRangeRemoved(0, categoryItems.size)    //将数据源操作与适配器操作写在同一线程中，保证list调用clear()方法后立即执行notifyItemRangeRemoved()
                    pressList?.let { its ->
                        its.rows.forEach { n ->
                            categoryItems.add(CategoryEmp(n.imgUrl, n.title, n.content, n.likeNumber, n.createTime))
                        }
                    }
                    uiThread {
                        viewListItems.add(recyclerView)
                        view.success()
                    }
                }
            }
        }
    }

    override fun loadBannerItems() {
        initBannerData()
    }

    override fun loadRecyclerviewApp() {
        appItems.clear()
        doAsync {
            val body = MM.getData().getServiceList().execute().body()
            body?.let {
                it.rows.forEach { t ->
                    appItems.add(ServiceEmp(t.serviceName, "http://124.93.196.45:10002${t.imgUrl}"))
                }
            }
            appItems.add(ServiceEmp("更多服务", ""))
            uiThread {
                view.success()
            }
        }
    }

    override fun loadRecyclerviewTheme() {
        themeItems.clear()
        doAsync {
            val body = MM.getData().getPress().execute().body()
            body?.let {
                it.rows.forEach { t ->
                    themeItems.add(ServiceEmp(t.title, "http://124.93.196.45:10002${t.imgUrl}"))
                }
            }
            uiThread {
                view.success()
            }
        }
    }

    private fun initBannerData() {
        imageItems.clear()
        doAsync {
            val body = MM.getData().getBanner().execute().body()
            body?.let {
                it.rows.forEach { t ->
                    imageItems.add("http://124.93.196.45:10002${t.imgUrl}")
                }
            }
            uiThread {
                view.startBanner()
            }
        }
    }

}