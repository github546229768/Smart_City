package com.example.smartcity_c.presenter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.edit
import com.bumptech.glide.Glide
import com.example.smartcity_c.R
import com.example.smartcity_c.contract.SplashContract
import com.example.smartcity_c.network.MM
import com.example.smartcity_c.ui.activity.LoginActivity
import com.example.smartcity_c.widght.NetWorkDialog
import kotlinx.android.synthetic.main.activity_network_setting.*
import kotlinx.android.synthetic.main.activity_network_setting.view.*
import kotlinx.android.synthetic.main.view_image_view_show_splash.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class SplashPresenter(private val view : SplashContract.View, private val context:Context) : SplashContract.Presenter {
    /*companion object{
         val IMAGES = arrayOf(R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher)
    }
    */

    private val sp: SharedPreferences by lazy {
        context.getSharedPreferences("fistShow",Context.MODE_PRIVATE)
    }

    val viewListItems  = mutableListOf<View>()

    private val imageItems = mutableListOf<String>()

    override fun addPage() {
        for (i in 0 until imageItems.size-1){
            val inflate = LayoutInflater.from(context).inflate(R.layout.view_image_view_show_splash, null)
            Glide.with(context).load("http://124.93.196.45:10002${imageItems[i]}").error(R.mipmap.ic_launcher).into(inflate.imageView14)
            viewListItems.add(inflate)
        }
        val inflate = LayoutInflater.from(context).inflate(R.layout.activity_network_setting, null)
        Glide.with(context).load("http://124.93.196.45:10002${imageItems[imageItems.size-2]}").error(R.mipmap.ic_launcher).into(inflate.imageView)
        viewListItems.add(inflate)
        inflate.network_setting.setOnClickListener { NetWorkDialog(context).show() }
        inflate.enter_home.setOnClickListener { context.startActivity<LoginActivity>()
            view.finishNow()
           }
    }

    override fun initData() {
        doAsync {
            val body = MM.getData().getCarousel().execute().body()
            body?.let {
                it.rows.forEach { t ->
                    imageItems.add(t.imgUrl)
                }
            }
            uiThread {
                addPage()
                    view.refresh()
            }
        }
    }

    override fun isFirstShow() {
        if (sp.getBoolean("isFirst",true)){
            view.failure()
        }else{
            view.success()
        }
    }

}