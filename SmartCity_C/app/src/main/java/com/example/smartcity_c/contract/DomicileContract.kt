package com.example.smartcity_c.contract

interface DomicileContract {
    interface Presenter{
        fun saveData(group:String,water:String,domicile:String)
    }
    interface View{
        fun checkButton()
    }
}