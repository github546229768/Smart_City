package com.example.smartcity_c.emp

data class Carousel(
    val code: Int,
    val msg: String,
    val rows: List<Row>,
    val total: Int
)

data class Row(
    val createTime: String,
    val display: String,
    val id: Int,
    val imgUrl: String,
    val sort: String,
    val type: String
)