package com.example.cours.api

import com.example.cours.CryptoApplication.Companion.context
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class SingletonsRetrofit {
    companion object {
        var cache: Cache = Cache(File(context?.cacheDir, "responses"), 100 * 1024 * 1024)

        val okHttpClient: OkHttpClient = OkHttpClient()
            .newBuilder()
            .cache(cache)
            .build()

        val cryptoAPI: CryptocurrencyAPI = Retrofit.Builder()
            .baseUrl("https://api.coinpaprika.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(CryptocurrencyAPI::class.java)
    }
}