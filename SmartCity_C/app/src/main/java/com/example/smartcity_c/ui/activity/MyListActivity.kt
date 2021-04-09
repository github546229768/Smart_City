package com.example.smartcity_c.ui.activity

import android.R
import android.app.ListActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SimpleAdapter
import org.jetbrains.anko.toast
import java.util.*
import kotlin.collections.ArrayList


class MyListActivity : ListActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = arrayOf("老师", "学生", "课桌", "书本", "铅笔", "橡皮", "粉笔", "黑板", "凳子", "扫帚", "簸箕", "炉子", "窗花", "讲台", "教鞭", "小红花", "花瓶")
//        listAdapter = ArrayAdapter<Any?>(this, R.layout.simple_list_item_1, data)

        val listData = mutableListOf<MutableMap<String,String>>()
        for (i in 0..10){
            val hashMap = mutableMapOf<String, String>()
            hashMap["name"] = "name$i"
            hashMap["desc"] = "desc$i"
            listData.add(hashMap)
        }
        listAdapter = SimpleAdapter(this,listData,R.layout.simple_list_item_2, arrayOf("name","desc"), intArrayOf(android.R.id.text1,android.R.id.text2))
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        toast("点击了$position")
    }
}