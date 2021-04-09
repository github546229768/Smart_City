package com.example.smartcity_c.emp

data class PressCategory(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
)

data class Data(
    val createBy: String,
    val createTime: String,
    val cssClass: Any,
    val default: Boolean,
    val dictCode: Int,
    val dictLabel: String,
    val dictSort: Int,
    val dictType: String,
    val dictValue: String,
    val isDefault: String,
    val listClass: Any,
    val params: Params2,
    val remark: Any,
    val searchValue: Any,
    val status: String,
    val updateBy: Any,
    val updateTime: Any
)

class Params2(
)