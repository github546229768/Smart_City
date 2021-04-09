package com.example.smartcity_c.ui.activity

import android.app.ListActivity
import android.app.ProgressDialog
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.smartcity_c.R
import com.example.smartcity_c.adapter.NewsCategoryAdapter
import com.example.smartcity_c.adapter.PressListViewAdapter
import com.example.smartcity_c.emp.CategoryEmp
import com.example.smartcity_c.network.MM
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jetbrains.anko.yesButton

class SearchActivity : ListActivity(){
    private val categoryItems = mutableListOf<CategoryEmp>()
    private val pressListViewAdapter by lazy { PressListViewAdapter(this, categoryItems) }
    private val progressDialog  by lazy { ProgressDialog(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog.show()
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also {
                mQuery(it)
            }
        }
        listAdapter = pressListViewAdapter
    }

    private fun mQuery(query: String) {
        doAsync {
            val pressList = MM.getData().getPressList().execute().body()
            pressList?.let { it.rows.forEach { t ->
                if (t.title.contains(query))   //向
                categoryItems.add(CategoryEmp(t.imgUrl,t.title,t.content,t.likeNumber,t.createTime))
            } }
            uiThread {
                pressListViewAdapter.notifyDataSetChanged()
                if (progressDialog.isShowing)progressDialog.dismiss()
                if (categoryItems.size==0){
                    alert("抱歉没有找到与$query 相关的新闻"){
                        yesButton { finish() }
                    }.show()
                }
            }
        }
    }

}