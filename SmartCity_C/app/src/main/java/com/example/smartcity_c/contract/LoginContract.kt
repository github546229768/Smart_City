package com.example.smartcity_c.contract

interface LoginContract {
    interface Presenter{
        fun checkLogin(username :String ,paswd :String)
    }
    interface View{
        fun success(token:String)
        fun failure()
    }
}