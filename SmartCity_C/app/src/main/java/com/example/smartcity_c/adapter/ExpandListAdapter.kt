package com.example.smartcity_c.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseExpandableListAdapter
import android.widget.ListAdapter
import android.widget.TextView
import com.example.smartcity_c.R
import com.example.smartcity_c.emp.ExpandGroupEmp
import kotlinx.android.synthetic.main.view_child_item.view.*
import kotlinx.android.synthetic.main.view_group_item.view.*
import kotlin.text.Typography.nbsp

class ExpandListAdapter(val context: Context, val list: MutableList<ExpandGroupEmp>) : BaseExpandableListAdapter() {
    override fun getGroupCount() = list.size

    override fun getChildrenCount(groupPosition: Int) = list[groupPosition].listStop.size

    override fun getGroup(groupPosition: Int) = groupPosition

    override fun getChild(groupPosition: Int, childPosition: Int) = childPosition

    override fun getGroupId(groupPosition: Int) = groupPosition.toLong()

    override fun getChildId(groupPosition: Int, childPosition: Int) = childPosition.toLong()

    override fun hasStableIds() = false

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val inflate = LayoutInflater.from(context).inflate(R.layout.view_group_item, parent, false)
        inflate.textView33.text = list[groupPosition].name
        (list[groupPosition].first + "——" + list[groupPosition].end).also { inflate.textView34.text = it }
        ("票价: ￥" + list[groupPosition].price + "里程: " + list[groupPosition].mileage).also { inflate.textView35.text = it }
        (list[groupPosition].startTime + "——" + list[groupPosition].endTime).also { inflate.textView36.text = it }
        list[groupPosition].startTime + "——" + list[groupPosition].endTime.also { inflate.textView37.text = it }
        return inflate
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        val inflate = LayoutInflater.from(context).inflate(R.layout.view_child_item, parent, false)
        when (childPosition) {
            0 -> ("起点：  "+list[groupPosition].listStop[childPosition]).also { inflate.textView38.text = it }
            list[groupPosition].listStop.size -1 -> {
                ("终点：  " + list[groupPosition].listStop[childPosition]).also { inflate.textView38.text = it }
            }
            else -> {
                 list[groupPosition].listStop[childPosition].also { inflate.textView38.text = "\u3000\u3000\u3000\u3000"+it }
            }
        }
        return inflate
    }


    override fun isChildSelectable(groupPosition: Int, childPosition: Int) = false
}