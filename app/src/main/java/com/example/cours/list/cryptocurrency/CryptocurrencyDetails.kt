package com.example.cours.list.cryptocurrency

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cours.R
import com.example.cours.api.CryptocurrencyAPI
import com.example.cours.api.CryptocurrencyResponse
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CryptocurrencyDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cryptocurrency_details)
        // Get the Intent that started this activity and extract the string
        val message = intent.getStringExtra("EXTRA_MESSAGE")
        val actionbar = supportActionBar
        //set actionbar title
        /*actionbar!!.title = message*/
        //set back button
        actionbar!!.setDisplayHomeAsUpEnabled(true)

        val name = findViewById<TextView>(R.id.details_name)
        val rank = findViewById<TextView>(R.id.details_rank)
        val description = findViewById<TextView>(R.id.details_description_text)
        val img = findViewById<ImageView>(R.id.details_img)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.coinpaprika.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: CryptocurrencyAPI = retrofit.create(CryptocurrencyAPI::class.java)

        service.getCurrency(message.toString()).enqueue(object: Callback<CryptocurrencyResponse> {
            override fun onFailure(call: Call<CryptocurrencyResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<CryptocurrencyResponse>,
                response: Response<CryptocurrencyResponse>
            ) {
                if (response.isSuccessful && response.body() !== null) {
                    val response = response.body()!!
                    actionbar!!.title = response.symbol
                    name.text = response.name
                    description.text = response.description
                    rank.text = "#" + response.rank
                    val link = StringBuilder()
                    link.append("https://static.coincap.io/assets/icons/").append(response.symbol.toLowerCase()).append("@2x.png")
                    Picasso.get().load(link.toString()).into(img)
                }
            }
        })
    }
   override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

}