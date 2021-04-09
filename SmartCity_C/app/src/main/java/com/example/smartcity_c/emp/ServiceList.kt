package com.example.smartcity_c.emp

data class ServiceList(
    val code: Int,
    val msg: String,
    val rows: List<Rows>,
    val total: Int
)

data class Rows(
    val createBy: Any,
    val createTime: String,
    val id: Int,
    val imgUrl: String,
    val isRecommend: Int,
    val link: String,
    val params: Params,
    val pid: Int,
    val remark: Any,
    val searchValue: Any,
    val serviceDesc: String,
    val serviceName: String,
    val serviceType: String,
    val updateBy: Any,
    val updateTime: String
)

class Params(
)