package com.example.smartcity_c.ui.fragment

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.graphics.Outline
import android.graphics.Rect
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartcity_c.R
import com.example.smartcity_c.adapter.HomeServiceAdapter
import com.example.smartcity_c.adapter.HomeThemeAdapter
import com.example.smartcity_c.adapter.OrderListPagerAdapter
import com.example.smartcity_c.contract.HomeContract
import com.example.smartcity_c.presenter.HomePresenter
import com.example.smartcity_c.ui.activity.LiveSpendActivity
import com.example.smartcity_c.ui.activity.ParkingActivity
import com.example.smartcity_c.ui.activity.SmartBusActivity
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class HomeFragment : BaseFragment(), HomeContract.View {
    override fun getLayoutInflaterId() = R.layout.fragment_home
    private val presenter by lazy { HomePresenter(this, requireActivity()) }
    private val pressCategoryPagerAdapter by lazy {
        OrderListPagerAdapter(
            presenter.viewListItems,
            presenter.title
        )
    }

    override fun init() {
        initSearchView()
        initRecyclerviewApp()
        initRecyclerviewTheme()
        presenter.loadBannerItems()
        presenter.loadRecyclerviewApp()
        presenter.loadRecyclerviewTheme()
        initViewpager()
        presenter.loadPressCategoryTitle()
    }

    private fun initSearchView() {
        searchView.apply {
            val searchManager =
                requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            isIconifiedByDefault = false
        }
    }

    private fun initViewpager() {
        tablayout.setupWithViewPager(viewpager)
        viewpager.apply {
            adapter = pressCategoryPagerAdapter
        }
    }

    private fun initRecyclerviewTheme() {
        recyclerview_theme.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = HomeThemeAdapter(context, presenter.themeItems)
        }
    }

    private fun initRecyclerviewApp() {
        recyclerview_app.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 5)
            adapter = HomeServiceAdapter(context, presenter.appItems)
        }
    }

    override fun startBanner() {
        banner.apply {
            setImages(presenter.imageItems)
            setDelayTime(1500)
            setImageLoader(object : ImageLoader() {
                override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                    context?.let { imageView?.let { it1 -> Glide.with(it).load(path).into(it1) } }
                }
            })
            setBannerAnimation(Transformer.CubeIn);
            outlineProvider = object : ViewOutlineProvider() {
                override fun getOutline(view: View?, outline: Outline?) {
                    if (view != null) {
                        outline?.setRoundRect(0, 0, view.width, view.height, 40f)
                    }
                }
            }
            clipToOutline = true
            start()
        }
        banner.setOnBannerListener {
            when (it) {
                0 -> context?.startActivity<SmartBusActivity>()
                1 -> context?.startActivity<LiveSpendActivity>()
                2 -> context?.startActivity<ParkingActivity>()
                3 -> context?.startActivity<LiveSpendActivity>()
            }
        }
    }

    override fun success() {
        recyclerview_app?.let {
            it.adapter?.notifyDataSetChanged()
        }
        recyclerview_theme?.let {
            it.adapter?.notifyDataSetChanged()
        }
        pressCategoryPagerAdapter.notifyDataSetChanged()
    }
}


