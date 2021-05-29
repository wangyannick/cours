package com.example.cours

import com.example.cours.CryptoApplication.Companion.context
import com.example.cours.api.CryptocurrencyAPI
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class Singletons {
    companion object {
        var cache: Cache = Cache(File(context?.cacheDir, "responses"), 10 * 1024 * 1024)

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