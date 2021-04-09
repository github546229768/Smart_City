package com.example.smartcity_c.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.collection.arrayMapOf
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.example.smartcity_c.R
import com.example.smartcity_c.adapter.LiveSpendListAdapter
import com.example.smartcity_c.network.MM.Companion.getData
import com.example.smartcity_c.ui.activity.livespend.DomicileActivity
import kotlinx.android.synthetic.main.activity_live_spend.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LiveSpendActivity : BaseActivity() {
    //声明AMapLocationClient类对象
    private val mLocationClient: AMapLocationClient by lazy {
        AMapLocationClient(applicationContext)
    }

    //声明定位回调监听器
    private val mLocationListener = AMapLocationListener {
        if (it.address.isNotEmpty()){
            Toast.makeText(this, "${it.address}", Toast.LENGTH_SHORT).show()
            textView47.text = it.address
            mLocationClient.stopLocation()
        }  else{
            Toast.makeText(this, "当前没有定位权限!", Toast.LENGTH_SHORT).show()
        }
    }
    private val mLocationOption by lazy { AMapLocationClientOption () }

    companion object {
        val name = arrayOf("水费","电费","户号管理","相关资讯")
        val img = intArrayOf(R.drawable.shui,R.drawable.dian,R.drawable.huhao,R.drawable.xixun)
    }
    override fun init() {
        imageView15.setOnClickListener { finish() }
        recycleview.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context,2)
            adapter = LiveSpendListAdapter(context,getRecycleData())
        }
        button.setOnClickListener { startActivity<DomicileActivity>() }
        if(checkoutLocation())
            initLocation()  //初始化定位
            else
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 500)

    }

    private fun initLocation() {
//设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy;
//设置定位回调监听
        mLocationClient.apply {
            setLocationListener(mLocationListener)
            //给定位客户端对象设置定位参数
            setLocationOption(mLocationOption)
            startLocation()
        }
    }

    private fun checkoutLocation(): Boolean {
        return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            initLocation()  //初始化定位
        else
            toast("权限被拒绝！无法完成定位服务")
    }

    private fun getRecycleData():MutableList<MutableMap<String,Any>> {
        val listMap = mutableListOf<MutableMap<String, Any>>()
        for (position in name.indices){
            val map = mutableMapOf<String,Any>()
            map["name"] = name[position]
            map["img"] = img[position]
            listMap.add(map)
        }
        return listMap
    }


    override fun getLayoutResID() = R.layout.activity_live_spend
}