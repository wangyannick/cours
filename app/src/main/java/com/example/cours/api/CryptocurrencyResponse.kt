package com.example.cours.api

data class CryptocurrencyResponse(
        val id: String,
        val name: String,
        val symbol: String,
        val rank: Int,/*
        val circulating_supply: Int?,
        val total_supply: Int?,
        val max_supply: Int?,
        val beta_value: Float?,*/
        val first_data_at: String,
        val last_updated: String,
        val quotes: Devise
)

data class Devise(
        val EUR: CurrencyObject,
        val BTC: CurrencyObject
)

data class CurrencyObject(
        val price: Float,
        val volume_24h_change_24h: Float,
        val market_cap: Float,
        val market_cap_change_24h: Float,
        val percent_change_15m: Float,
        val percent_change_30m: Float,
        val percent_change_1h: Float,
        val percent_change_6h: Float,
        val percent_change_12h: Float,
        val percent_change_24h: Float,
        val percent_change_7d: Float,
        val percent_change_30d: Float,
        val percent_change_1y: Float
)