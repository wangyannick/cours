package com.example.cryptoesiea.api

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.cryptoesiea.CryptoApplication.Companion.context
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException

class SingletonsRetrofit {
    companion object {
        @RequiresApi(Build.VERSION_CODES.M)
        @SuppressLint("ServiceCast")
        fun isInternetAvailable(): Boolean {
            var isConnected = false // Initial Value
            val connectivityManager =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetwork
            if (activeNetwork != null)
                isConnected = true
            return isConnected
        }

        private var onlineInterceptor: Interceptor = object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response? {
                val response: Response = chain.proceed(chain.request())
                val maxAge =
                    60 // read from cache for 60 seconds even if there is internet connection
                return response.newBuilder()
                    .header("Cache-Control", "public, max-age=$maxAge")
                    .removeHeader("Pragma")
                    .build()
            }
        }

        @RequiresApi(Build.VERSION_CODES.M)
        private var offlineInterceptor = Interceptor { chain ->
            var request: Request = chain.request()
            if (!isInternetAvailable()) {
                val maxStale = 60 * 60 * 24 * 30 // Offline cache available for 30 days
                request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .removeHeader("Pragma")
                    .build()
            }
            chain.proceed(request)
        }

        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        var cache: Cache = Cache(File(context?.cacheDir, "responses"), cacheSize)

        @RequiresApi(Build.VERSION_CODES.M)
        val okHttpClient: OkHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(offlineInterceptor)
            .addNetworkInterceptor(onlineInterceptor)
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