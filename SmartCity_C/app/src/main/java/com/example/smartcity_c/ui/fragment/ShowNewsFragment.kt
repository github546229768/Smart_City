package com.example.smartcity_c.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartcity_c.R
import com.example.smartcity_c.adapter.PressListViewAdapter
import com.example.smartcity_c.contract.HomeContract
import com.example.smartcity_c.emp.NewsEmp
import com.example.smartcity_c.presenter.HomePresenter
import kotlinx.android.synthetic.main.fragment_show_news.*

class ShowNewsFragment : BaseFragment() {
//    private val adapter by lazy { PressListViewAdapter(pressList,requireActivity()) }
//        val pressList = mutableListOf<NewsEmp>()
    override fun init() {
//        listview.adapter = adapter
//        pressList.add(NewsEmp("1","2","3","4","5"))
//        adapter.notifyDataSetChanged()
    }
    override fun getLayoutInflaterId() = R.layout.fragment_show_news
}