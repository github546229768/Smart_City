package com.example.smartcity_c.network

import android.content.Context
import android.content.SharedPreferences
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MM {
    companion object{
        fun getData(): Api {
            val build =
                Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://124.93.196.45:10002")
                    .build()
            return build.create(Api::class.java)
        }
    }
}