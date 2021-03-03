package com.example.cours.api

import retrofit2.http.GET
import retrofit2.Call

interface CryptocurrencyAPI {
    @GET("tickers/?quotes=EUR")
    fun getCurrencyList(): Call<ArrayList<CryptocurrencyResponse>>
}