package com.example.smartcity_c.ui.activity

import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.smartcity_c.MainActivity
import com.example.smartcity_c.R
import com.example.smartcity_c.contract.SplashContract
import com.example.smartcity_c.presenter.SplashPresenter
import com.example.smartcity_c.widght.NetWorkDialog
import kotlinx.android.synthetic.main.activity_network_setting.*
import kotlinx.android.synthetic.main.activity_splash.*
import okhttp3.OkHttpClient
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity() , SplashContract.View{
    val presenter = SplashPresenter(this,this)
    override fun finishNow() {
        finish()
    }

    private val mSplashPagerAdapter  = object : PagerAdapter(){
        override fun getCount() = presenter.viewListItems.size

        override fun isViewFromObject(view: View, `object`: Any) = view == `object`

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            container.addView(presenter.viewListItems[position])
            return presenter.viewListItems[position]
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(presenter.viewListItems[position])
        }
    }

    override fun init() {
        presenter.isFirstShow()
    }

    private fun addPositionView() {
        for (i in 0..4 ){
            val radioButton = RadioButton(this)
            radioButton.id  = i
            radioGroup_splash.addView(radioButton)
        }
        radioGroup_splash.check(0)
    }

    private fun initViewPager() {
        viewpager_img.adapter =mSplashPagerAdapter
        viewpager_img.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                radioGroup_splash.check(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
    }

    override fun getLayoutResID() = R.layout.activity_splash

    override fun success() {
        startActivity<MainActivity>()
        finish()
    }

    override fun failure() {
        addPositionView()
        presenter.initData()
        initViewPager()
    }

    override fun refresh() {
        mSplashPagerAdapter.notifyDataSetChanged()
    }

}