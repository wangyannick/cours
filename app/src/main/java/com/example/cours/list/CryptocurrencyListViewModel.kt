package com.example.cours.list

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cours.CryptoApplication.Companion.context
import com.example.cours.api.CryptocurrencyListResponse
import com.example.cours.api.SingletonsRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CryptocurrencyListViewModel : ViewModel() {
    val cryptoList: MutableLiveData<CryptocurrencyModel> = MutableLiveData()

    init {
        getList()
    }

    private fun getList() {
        cryptoList.value = CryptocurrencyListLoading
        SingletonsRetrofit.cryptoAPI.getCurrencyList()
            .enqueue(object : Callback<ArrayList<CryptocurrencyListResponse>> {
                override fun onFailure(
                    call: Call<ArrayList<CryptocurrencyListResponse>>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Oups, une erreur est survenue !", Toast.LENGTH_SHORT)
                        .show()

                    cryptoList.value = CryptocurrencyListError
                }

                override fun onResponse(
                    call: Call<ArrayList<CryptocurrencyListResponse>>,
                    response: Response<ArrayList<CryptocurrencyListResponse>>
                ) {
                    if (response.isSuccessful && response.body() !== null) {
                        val cryptocurrency = response.body()!!
                        cryptoList.value = CryptocurrencyListSuccess(cryptocurrency)
                    } else {
                        cryptoList.value = CryptocurrencyListError
                    }
                }
            })
    }
}