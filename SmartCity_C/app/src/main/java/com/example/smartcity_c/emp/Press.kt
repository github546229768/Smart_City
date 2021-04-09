package com.example.smartcity_c.emp

data class Press(
    val code: Int,
    val msg: String,
    val rows: List<Rows1>,
    val total: Int
)

data class Rows1(
    val content: String,
    val createBy: Any,
    val createTime: Any,
    val id: Int,
    val imgUrl: String,
    val isRecommend: Int,
    val likeNumber: Int,
    val params: Params1,
    val pressCategory: Any,
    val pressStatus: Any,
    val remark: Any,
    val searchValue: Any,
    val title: String,
    val updateBy: Any,
    val updateTime: Any,
    val userId: Any,
    val viewsNumber: Int
)

class Params1(
)