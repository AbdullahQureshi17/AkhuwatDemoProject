package com.example.akhuwatdemo.callback

import com.example.akhuwatdemo.model.FarmerType

interface DataResponseBack<T> {
    fun onSuccess(data: ArrayList<FarmerType>)
    fun onFailure(message:String)
}