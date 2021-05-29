package com.example.cours.api

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CryptocurrencyListViewModel: ViewModel() {
    val cryptoList: MutableLiveData<ArrayList<CryptocurrencyListResponse>> = MutableLiveData()

    init {
        getList()
    }

    private fun getList() {
        Singletons.cryptoAPI.getCurrencyList()
            .enqueue(object : Callback<ArrayList<CryptocurrencyListResponse>> {
                override fun onFailure(
                    call: Call<ArrayList<CryptocurrencyListResponse>>,
                    t: Throwable
                ) {
                    println("Failed")
                }

                override fun onResponse(
                    call: Call<ArrayList<CryptocurrencyListResponse>>,
                    response: Response<ArrayList<CryptocurrencyListResponse>>
                ) {
                    if (response.isSuccessful && response.body() !== null) {
                        val cryptocurrency = response.body()!!
                        cryptoList.value = cryptocurrency
                    }
                }
            })
    }
}