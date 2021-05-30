package com.example.cours.api

data class CryptocurrencyOHLCVResponse(
    val time_open: String,
    val time_close: String,
    val open: Float,
    val high: Float,
    val low: Float,
    val close: Float,
    val volume: Float,
    val market_cap: Float
)


