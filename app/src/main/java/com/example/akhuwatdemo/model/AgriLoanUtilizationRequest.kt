package com.example.akhuwatdemo.model

data class AgriLoanUtilizationRequest(
    var Amount: Int,
    var ApplicationIDHash: String,
    var AvailableAmount: Int,
    var LoanUtilizationTypeName: String,
    var RequiredAmount: Int
)