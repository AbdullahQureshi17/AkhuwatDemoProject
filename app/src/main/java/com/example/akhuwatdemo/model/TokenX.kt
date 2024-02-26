package com.example.akhuwatdemo.model

data class TokenX(
    val RedirectKeyHash: String,
    val RefreshTokenExpirationHash: String,
    val RefreshTokenHash: String,
    val Token: String,
    val TokenExpiration: String
)