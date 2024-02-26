package com.example.akhuwatdemo.model

data class AgriLoanProduct(
    val AgriLoanProductIDHash: String,
    val ApplicationIDHash: String,
    val FarmSeasonTypeIDHash: String,
    val FarmSeasonTypeName: String,
    val PreviousProduction: Int
)