package com.example.smartcity_c.emp

data class BusStopList(
    val code: Int,
    val msg: String,
    val rows: List<Row6>,
    val total: Int
)

data class Row6(
    val createBy: Any,
    val createTime: Any,
    val linesId: String,
    val name: String,
    val params: Params,
    val remark: Any,
    val searchValue: Any,
    val sequence: String,
    val stepsId: String,
    val updateBy: Any,
    val updateTime: Any
)

