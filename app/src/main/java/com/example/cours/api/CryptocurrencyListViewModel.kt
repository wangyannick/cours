package com.example.cours.api

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cours.CryptoApplication.Companion.context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CryptocurrencyListViewModel: ViewModel() {
    val cryptoList: MutableLiveData<ArrayList<CryptocurrencyListResponse>> = MutableLiveData()

    init {
        getList()
    }

    private fun getList() {
        SingletonsRetrofit.cryptoAPI.getCurrencyList()
            .enqueue(object : Callback<ArrayList<CryptocurrencyListResponse>> {
                override fun onFailure(
                    call: Call<ArrayList<CryptocurrencyListResponse>>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Oups, une erreur est survenue !", Toast.LENGTH_SHORT)
                        .show()
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