package com.example.cours.api

data class CryptocurrencyResponse(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val is_new: Boolean,
    val is_active: Boolean,
    val type: String,
    val description: String,
    val started_at: String
)