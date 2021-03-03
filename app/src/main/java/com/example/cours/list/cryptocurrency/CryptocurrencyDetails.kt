package com.example.cours.list.cryptocurrency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cours.R

class CryptocurrencyDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cryptocurrency_details)
        // Get the Intent that started this activity and extract the string
        val message = intent.getStringExtra("EXTRA_MESSAGE")


        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = message
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
    }
}