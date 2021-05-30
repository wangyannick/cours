package com.example.cryptoesiea.list

import com.example.cryptoesiea.api.CryptocurrencyListResponse

sealed class CryptocurrencyModel

data class CryptocurrencyListSuccess(val cryptocurrencyList: ArrayList<CryptocurrencyListResponse>) :
    CryptocurrencyModel()

object CryptocurrencyListLoading : CryptocurrencyModel()
object CryptocurrencyListError : CryptocurrencyModel()
