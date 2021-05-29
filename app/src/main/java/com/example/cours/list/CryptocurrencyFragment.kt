package com.example.cours.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cours.R
import com.example.cours.api.CryptocurrencyListViewModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CryptocurrencyFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val adapter = CryptocurrencyAdapter(arrayListOf());
    private val layoutManager = LinearLayoutManager(context)
    private var disposable: Disposable? = null
    private val viewModel: CryptocurrencyListViewModel by viewModels()

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


        viewModel.cryptoList.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })

    }

}