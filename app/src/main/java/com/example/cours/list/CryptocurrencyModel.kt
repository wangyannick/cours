package com.example.cours.list

import com.example.cours.api.CryptocurrencyListResponse

sealed class CryptocurrencyModel

data class CryptocurrencyListSuccess(val cryptocurrencyList: ArrayList<CryptocurrencyListResponse>) :
    CryptocurrencyModel()

object CryptocurrencyListLoading : CryptocurrencyModel()
object CryptocurrencyListError : CryptocurrencyModel()
