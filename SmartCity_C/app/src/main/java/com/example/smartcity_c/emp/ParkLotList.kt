package com.example.smartcity_c.emp

data class ParkLotList(
    val code: Int,
    val msg: String,
    val rows: List<Row3>,
    val total: Int
)

data class Row3(
    val address: String,
    val allPark: String,
    val createBy: Any,
    val createTime: Any,
    val distance: String,
    val id: Int,
    val imgUrl: String,
    val params: Params,
    val parkName: String,
    val priceCaps: String,
    val rates: String,
    val remark: Any,
    val searchValue: Any,
    val updateBy: Any,
    val updateTime: Any,
    val vacancy: String
)

