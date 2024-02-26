package com.example.akhuwatdemo.model

data class ApiLivestockAppraisalRequest(
    var ApLsBusinessPlaceTypeIDHash: String,
    var ApLsExpectedIncomIncrease: Int,
    var ApLsLatitude: Int,
    var ApLsLongitude: Int,
    var ApLsMonthlyProfit: Int,
    var ApplicationIDHash: String,
    var LiveStockDetail: List<LiveStockDetail>,
    var LiveStockLoanUtilization: List<LiveStockLoanUtilization>
)