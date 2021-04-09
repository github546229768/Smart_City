package com.example.smartcity_c.contract

interface ParkingMemoriesContract  {
    interface Presenter{
        fun loadRecyclerData()
        fun loadMoreRecyclerData()
        fun searchRecyclerData(startTime : String , endTime: String)
    }
    interface View{
        fun refresh()
    }
}