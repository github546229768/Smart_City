package com.example.smartcity_c.contract

interface SplashContract {
    interface Presenter{
        fun addPage()
        fun initData()
        fun isFirstShow()
    }
    interface View{
        fun success()
        fun failure()
        fun refresh()
        fun finishNow()

    }
}