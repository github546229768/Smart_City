package com.example.smartcity_c.emp

data class PersonInfo(
    val code: Int,
    val msg: String,
    val permissions: List<Any>,
    val roles: List<Any>,
    val user: User
)

data class User(
    val admin: Boolean,
    val avatar: String,
    val createBy: String,
    val createTime: String,
    val delFlag: String,
    val dept: Any,
    val deptId: Any,
    val email: String,
    val `file`: Any,
    val idCard: Any,
    val loginDate: Any,
    val loginIp: String,
    val nickName: String,
    val oldPwd: Any,
    val params: Params,
    val phonenumber: String,
    val postIds: Any,
    val remark: Any,
    val roleIds: Any,
    val roles: List<Any>,
    val salt: Any,
    val searchValue: Any,
    val sex: String,
    val status: String,
    val updateBy: Any,
    val updateTime: Any,
    val userId: Int,
    val userName: String
)

