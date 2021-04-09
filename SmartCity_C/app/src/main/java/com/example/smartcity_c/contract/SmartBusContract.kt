package com.example.smartcity_c.contract

interface SmartBusContract {
    interface Presenter{
        fun loadData()
    }
    interface View {
        fun refresh()
    }
}