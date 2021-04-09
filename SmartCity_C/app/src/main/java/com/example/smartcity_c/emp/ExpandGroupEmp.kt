package com.example.smartcity_c.emp

data class ExpandGroupEmp(val name : String?,
                          val first : String?,
                          val end : String?,
                          val startTime : String?,
                          val endTime : String?,
                          val price : Int?,
                          val mileage : String?,
                           val listStop:MutableList<String>)