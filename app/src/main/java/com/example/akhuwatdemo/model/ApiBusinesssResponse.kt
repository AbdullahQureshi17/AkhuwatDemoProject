package com.example.akhuwatdemo.model

data class ApiBusinesssResponse(
    val Message: String,
    val StatusCode: Int,
    val result: List<Result>
)