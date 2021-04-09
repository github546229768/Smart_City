package com.example.smartcity_c.contract

interface OrderListContract {
    interface Presenter{
        fun initPagerData()
    }
    interface View{
        fun refresh()
        fun showDialogs()
        fun dismissDialogs()
    }
}