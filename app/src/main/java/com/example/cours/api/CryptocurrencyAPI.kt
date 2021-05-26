package com.example.cours.api

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptocurrencyAPI {
    @GET("tickers/?quotes=EUR")
    fun getCurrencyList(): Call<ArrayList<CryptocurrencyListResponse>>

    @GET("coins/{coins}")
    fun getCurrency(@Path("coins") coins: String): Call<CryptocurrencyResponse>

    @GET("coins/{coins}/ohlcv/historical")
    fun getCryptocurrencyOHLCV(
        @Path("coins") coins: String,
        @Query("start") start: String,
        @Query("end") end: String,
        @Query("quote") quote: String
    ): Call<ArrayList<CryptocurrencyOHLCV>>
}