package com.example.cours.list.cryptocurrency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cours.R
import com.example.cours.api.CryptocurrencyAPI
import com.example.cours.api.CryptocurrencyListResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CryptocurrencyFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val adapter = CryptocurrencyAdapter(arrayListOf());
    private val layoutManager = LinearLayoutManager(context)
    private var disposable: Disposable? = null

    private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.coinpaprika.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    private val service: CryptocurrencyAPI = retrofit.create(CryptocurrencyAPI::class.java)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cryptocurrency, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.apply {
            layoutManager = this@CryptocurrencyFragment.layoutManager
            adapter = this@CryptocurrencyFragment.adapter
        }

        getCryptocurrencyList()
       /* disposable = Observable.interval(1000, 5000,
                TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getCryptocurrencyListdz, this::onError);*/
        Observable.interval(5, TimeUnit.SECONDS)
                .subscribe { getCryptocurrencyList() }
    }

    private fun getCryptocurrencyList() {
        println("Reload")
        service.getCurrencyList().enqueue(object : Callback<ArrayList<CryptocurrencyListResponse>> {
            override fun onFailure(call: Call<ArrayList<CryptocurrencyListResponse>>, t: Throwable) {
                TODO("Not yet implemented")
            }
            override fun onResponse(call: Call<ArrayList<CryptocurrencyListResponse>>, response: Response<ArrayList<CryptocurrencyListResponse>>) {
                if (response.isSuccessful && response.body() !== null) {
                    adapter.updateList(response.body()!!)
                }
            }
        })
    }

}