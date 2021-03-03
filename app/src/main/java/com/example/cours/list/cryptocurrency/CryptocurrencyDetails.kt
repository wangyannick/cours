package com.example.cours.list.cryptocurrency

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
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