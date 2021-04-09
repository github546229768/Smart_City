package com.example.smartcity_c.contract

interface BusOrderContract {
    interface Presenter{
        fun  loadData()
    }
    interface View{
        fun refresh()
    }
}