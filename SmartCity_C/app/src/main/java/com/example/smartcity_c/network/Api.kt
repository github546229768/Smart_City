package com.example.smartcity_c.network

import com.example.smartcity_c.emp.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.io.File

interface Api {
    //登录
    @POST("/login")
    fun getLogin(@Body info : RequestBody):Call<Login>
    //首页图
    @GET("/userinfo/rotation/lists?type=47")
    fun getCarousel():Call<Carousel>
    //轮播图
    @GET("/userinfo/rotation/lists?type=45")
    fun getBanner():Call<Carousel>
    //服务图
    @GET("/service/service/list")
    fun getServiceList(): Call<ServiceList>
    //热门新闻主题图
    @GET("/press/press/list?pressCategory=48")
    fun getPress(): Call<Press>
    //新闻分类标题
    @GET("/system/dict/data/type/press_category")
    fun getPressCategory(): Call<PressCategory>
    //按新闻分类查询分类列表
    @GET("/press/press/list")
    fun getPressList(@Query("pressCategory")  pressCategory : Int): Call<PressList>
    //查询所有新闻列表
    @GET("/press/press/list")
    fun getPressList(): Call<PressList>
    //停车场列表
    @GET("/userinfo/parklot/list")
    fun getParkLotList(): Call<ParkLotList>
    //查询所有停车记录
    @GET("/userinfo/parkrecord/list")
    fun getParkRecordList(): Call<ParkRecordList>
    //查询时间段停车记录
    @GET("/userinfo/parkrecord/list")
    fun getParkRecordList(@Query("entryTime") entryTime :String ,
                          @Query("outTime") outTime :String ): Call<ParkRecordList>
    //查询个人信息
    @GET("/getInfo")
    fun getInfo(@Header("Authorization")  auth : String): Call<PersonInfo>
    //查询详细个人信息
    @GET("/system/user/{id}")
    fun getSystemUser(@Header("Authorization")  auth : String,@Path("id") id :Int): Call<SystemUser>
    //修改密码
    @PUT("/system/user/resetPwd")
    fun updatePswd(@Header("Authorization")  auth : String,@Body info : RequestBody): Call<ResponseInfo>
    //查询全部订单
    @GET("/userinfo/orders/list")
    fun getOrdersList(@Header("Authorization")  auth : String): Call<OrdersList>
    //提交意见
    @POST("/userinfo/feedback")
    fun getFeedBack(@Header("Authorization")  auth : String,@Body info : RequestBody): Call<ResponseBody>
    //查询全部路线
    @GET("/userinfo/lines/list")
    fun getLinesList(): Call<LinesList>
    //查询该路线的站点
    @GET("/userinfo/busStop/list")
    fun getBusStopList(@Query("linesId") linesId :Int): Call<BusStopList>
    //查询全部大巴订单
    @GET("/userinfo/busOrders/list")
    fun getBusOrdersList(@Header("Authorization")  auth : String): Call<BusOrdersList>
    // 新增大巴订单
    @POST("/userinfo/busOrders")
    fun getBusOrders(@Header("Authorization")  auth : String,
                     @Body info :RequestBody): Call<ResponseBody>
    //查询用户生活缴费信息
    @GET("/userinfo/householder/{id}")
    fun getHouseHolder(@Header("Authorization")  auth : String,
                       @Path("id") id :Int): Call<HouseHolder>
    //缴费
    @PUT("/userinfo/householder")
    fun putHouseHolder(@Header("Authorization")  auth : String,
                       @Body info: ResponseBody): Call<ResponseInfo>
    //添加缴费类型
    @POST("/userinfo/relations")
    fun getRelations(@Header("Authorization")  auth : String,
                     @Body info :RequestBody): Call<ResponseInfo>
    //修改用户信息(上传文件)
    @POST("/system/user/updata")
    @Multipart
    fun getUserUpdate(@Header("Authorization")  auth : String
                    ,@Part requestBodyMap : MutableList<MultipartBody.Part>)
                   : Call<ResponseInfo>

}