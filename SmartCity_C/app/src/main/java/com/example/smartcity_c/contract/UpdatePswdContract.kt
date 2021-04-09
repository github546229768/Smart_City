package com.example.smartcity_c.contract

interface UpdatePswdContract {
    interface Presenter {
        fun updatePswd(old : String, new :String)
    }
    interface View{
        fun failure()
        fun success()
    }
}