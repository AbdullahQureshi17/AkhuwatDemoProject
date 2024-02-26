package com.example.akhuwatdemo.callback

import com.example.akhuwatdemo.model.Result

interface ApiResponseBack<T> {

    fun onSuccess(data: T)
    fun onFailure(message:String)
}