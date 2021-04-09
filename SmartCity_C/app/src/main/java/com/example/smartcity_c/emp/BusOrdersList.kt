package com.example.smartcity_c.emp

data class BusOrdersList(
    val code: Int,
    val msg: String,
    val rows: List<Row7>,
    val total: Int
)

data class Row7(
    val createBy: Any,
    val createTime: String,
    val end: String,
    val id: Int,
    val orderNum: String,
    val params: Params,
    val path: String,
    val price: Int,
    val remark: Any,
    val searchValue: Any,
    val start: String,
    val status: Int,
    val updateBy: Any,
    val updateTime: Any,
    val userId: Int,
    val userName: String,
    val userTel: String
)

