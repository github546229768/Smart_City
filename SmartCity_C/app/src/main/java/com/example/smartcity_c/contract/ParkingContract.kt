package com.example.smartcity_c.contract

interface ParkingContract {
    interface Presenter{
        fun loadRecyclerData()
        fun loadMoreRecyclerData()
    }
    interface View{
        fun refresh()
    }
}