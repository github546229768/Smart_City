package com.example.smartcity_c.emp

data class LinesList(
    val code: Int,
    val msg: String,
    val rows: List<Row5>,
    val total: Int
)

data class Row5(
    val createBy: Any,
    val createTime: String,
    val end: String,
    val endTime: String,
    val first: String,
    val id: Int,
    val mileage: String,
    val name: String,
    val params: Params,
    val price: Int,
    val remark: Any,
    val searchValue: Any,
    val startTime: String,
    val updateBy: Any,
    val updateTime: String
)

