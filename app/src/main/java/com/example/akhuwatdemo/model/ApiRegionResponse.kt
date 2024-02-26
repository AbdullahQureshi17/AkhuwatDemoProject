package com.example.akhuwatdemo.model

data class ApiRegionResponse(
    val Message: String,
    val StatusCode: Int,
    val result: List<ResultX>
)