package com.example.smartcity_c.emp

data class SystemUser(
    val code: Int,
    val `data`: Data2,
    val msg: String,
    val postIds: List<Any>,
    val posts: List<Post>,
    val roleIds: List<Any>,
    val roles: List<Role>
)

data class Data2(
    val admin: Boolean,
    val avatar: String,
    val createBy: String,
    val createTime: String,
    val delFlag: String,
    val dept: Any,
    val deptId: Any,
    val email: String,
    val `file`: Any,
    val idCard: String,
    val loginDate: Any,
    val loginIp: String,
    val nickName: String,
    val oldPwd: Any,
    val params: Params,
    val phonenumber: String,
    val postIds: Any,
    val remark: String,
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

data class Post(
    val createBy: String,
    val createTime: String,
    val flag: Boolean,
    val params: ParamsX,
    val postCode: String,
    val postId: Int,
    val postName: String,
    val postSort: String,
    val remark: String,
    val searchValue: Any,
    val status: String,
    val updateBy: Any,
    val updateTime: Any
)

data class Role(
    val admin: Boolean,
    val createBy: Any,
    val createTime: String,
    val dataScope: String,
    val delFlag: String,
    val deptIds: Any,
    val flag: Boolean,
    val menuIds: Any,
    val params: ParamsXX,
    val remark: String,
    val roleId: Int,
    val roleKey: String,
    val roleName: String,
    val roleSort: String,
    val searchValue: Any,
    val status: String,
    val updateBy: Any,
    val updateTime: Any
)


class ParamsX(
)

class ParamsXX(
)