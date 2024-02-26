package com.example.akhuwatdemo.model

data class AgriLoanProductRequest(
    var ApplicationIDHash: String,
    var FarmSeasonTypeIDHash: String,
    var PreviousProduction: Int
)