package com.example.smartcity_c.emp

data class PressList(
    val code: Int,
    val msg: String,
    val rows: List<Row2>,
    val total: Int
)

data class Row2(
    val content: String,
    val createBy: Any,
    val createTime: String,
    val id: Int,
    val imgUrl: String,
    val isRecommend: Int,
    val likeNumber: Int,
    val params: Params,
    val pressCategory: String,
    val pressStatus: Int,
    val remark: Any,
    val searchValue: Any,
    val title: String,
    val updateBy: Any,
    val updateTime: String,
    val userId: Int,
    val viewsNumber: Int
)
