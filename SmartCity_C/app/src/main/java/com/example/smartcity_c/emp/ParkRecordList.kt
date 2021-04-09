package com.example.smartcity_c.emp

data class ParkRecordList(
    val code: Int,
    val msg: String,
    val rows: List<Row4>,
    val total: Int
)

data class Row4(
    val createBy: Any,
    val createTime: Any,
    val entryTime: String,
    val id: Int,
    val monetary: String,
    val outTime: String,
    val params: Params,
    val parkName: String,
    val plateNumber: String,
    val remark: Any,
    val searchValue: Any,
    val updateBy: Any,
    val updateTime: Any
)

