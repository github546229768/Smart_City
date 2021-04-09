package com.example.smartcity_c.contract

interface HomeContract {
    interface Presenter {
        fun loadBannerItems()
        fun loadRecyclerviewApp()
        fun loadRecyclerviewTheme()
        fun loadPressCategoryTitle()
    }
    interface View{
        fun startBanner()
        fun success()
    }
}