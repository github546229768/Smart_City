package com.example.smartcity_c.contract

interface WaterContract {
    interface Presenter{
        fun  loadData()
        fun startSpend(balance:Int)
    }
    interface View {
        fun updateView(mDoorNo:Int,mId:Int,mOwnerName:String,mBalance:String)
    }
}