package com.example.cours.api

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

interface CryptocurrencyAPI {
    @GET("tickers/?quotes=EUR")
    fun getCurrencyList(): Call<ArrayList<CryptocurrencyListResponse>>

    @GET("coins/{coins}")
    fun getCurrency(@Path("coins") coins: String): Call<CryptocurrencyResponse>
}