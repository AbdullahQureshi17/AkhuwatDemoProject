package com.example.akhuwatdemo.model

data class ResultXXXXXX(
    val ApLsBusinessPlaceTypeIDHash: String,
    val ApLsBusinessPlaceTypeName: String,
    val ApLsExpectedIncomIncrease: Int,
    val ApLsMonthlyProfit: Int,
    val ApplicationIDHash: String,
    val LiveStockDetail: List<LiveStockDetailX>,
    val LiveStockLoanUtilization: List<LiveStockLoanUtilizationX>
)