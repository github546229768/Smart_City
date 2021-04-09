package com.example.smartcity_c.contract

import java.io.File

interface PersonalContract {
    interface Presenter {
        fun loadUserData()
        fun saveData(picture:File,idCard : String,nickName : String,phonenumber : String,sexs : String,)
    }
    interface View{
        fun setUserInfo(img : String,name : String,sex : String,iphone : String,carId : String)
        fun checkUn()
    }
}