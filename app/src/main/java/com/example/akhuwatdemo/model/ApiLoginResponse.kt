package com.example.akhuwatdemo.model

import com.google.gson.annotations.SerializedName

data class ApiLoginResponse(
   @SerializedName("List")
    val Data: Data,
    val StatusCode: Int
)