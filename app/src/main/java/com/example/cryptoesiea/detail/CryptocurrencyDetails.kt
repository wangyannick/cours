package com.example.cryptoesiea.detail

import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptoesiea.R
import com.example.cryptoesiea.api.CryptocurrencyOHLCVResponse
import com.example.cryptoesiea.api.CryptocurrencyResponse
import com.example.cryptoesiea.api.SingletonsRetrofit
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*


class CryptocurrencyDetails : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
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
        val candleStickChart = findViewById<CandleStickChart>(R.id.candle_stick_graph)

        candleStickChart.isHighlightPerDragEnabled = true

        candleStickChart.setDrawBorders(true)

        candleStickChart.setBorderColor(Color.rgb(211, 211, 211))

        val yAxis = candleStickChart.axisLeft
        val rightAxis = candleStickChart.axisRight
        yAxis.setDrawGridLines(false)
        rightAxis.setDrawGridLines(false)
        candleStickChart.requestDisallowInterceptTouchEvent(true)

        val xAxis = candleStickChart.xAxis

        xAxis.setDrawGridLines(false) // disable x axis grid lines

        rightAxis.textColor = Color.WHITE
        xAxis.granularity = 1f
        xAxis.isGranularityEnabled = true
        xAxis.setAvoidFirstLastClipping(true)

        val l = candleStickChart.legend
        l.isEnabled = false

/*
        Get cryptocurrency information

*/
        SingletonsRetrofit.cryptoAPI.getCurrency(message.toString())
            .enqueue(object : Callback<CryptocurrencyResponse> {
                override fun onFailure(call: Call<CryptocurrencyResponse>, t: Throwable) {
                    println("Failed")
                }

                override fun onResponse(
                    call: Call<CryptocurrencyResponse>,
                    response: Response<CryptocurrencyResponse>
                ) {
                    if (response.isSuccessful && response.body() !== null) {
                        val resp = response.body()!!
                        actionbar.title = resp.symbol
                        name.text = resp.name
                        description.text = resp.description
                        rank.text = "#" + resp.rank
                        val link = StringBuilder()
                        link.append("https://static.coincap.io/assets/icons/")
                            .append(resp.symbol.toLowerCase()).append("@2x.png")
                        Picasso.get().load(link.toString()).into(img)
                    }
                }
            })

        /*
             Get cryptocurrency graph information
        */

        SingletonsRetrofit.cryptoAPI.getCryptocurrencyOHLCV(
            message.toString(), DateTimeFormatter
                .ofPattern("yyyy-MM-dd")
                .withZone(ZoneOffset.UTC)
                .format(Instant.now().minus(360, ChronoUnit.DAYS)).toString(), DateTimeFormatter
                .ofPattern("yyyy-MM-dd")
                .withZone(ZoneOffset.UTC)
                .format(Instant.now()).toString(), "usd"
        ).enqueue(object : Callback<ArrayList<CryptocurrencyOHLCVResponse>> {
            override fun onResponse(
                call: Call<ArrayList<CryptocurrencyOHLCVResponse>>,
                response: Response<ArrayList<CryptocurrencyOHLCVResponse>>
            ) {
                if (response.isSuccessful && response.body() !== null) {
                    val ceList = ArrayList<CandleEntry>();
                    for ((index, item) in response.body()!!.withIndex()) {
                        ceList.add(
                            CandleEntry(
                                index.toFloat(),
                                item.high,
                                item.low,
                                item.open,
                                item.close
                            )
                        )
                    }

                    val cds = CandleDataSet(ceList, "Entries")
                    cds.color = Color.rgb(80, 80, 80)
                    cds.shadowColor = Color.rgb(211, 211, 211)
                    cds.shadowWidth = 0.8f
                    cds.decreasingColor = Color.rgb(250, 128, 114)
                    cds.decreasingPaintStyle = Paint.Style.FILL
                    cds.increasingColor = Color.rgb(0, 139, 139)
                    cds.increasingPaintStyle = Paint.Style.FILL
                    cds.neutralColor = Color.LTGRAY
                    cds.setDrawValues(false)
                    val cd = CandleData(cds)
                    candleStickChart.data = cd
                    candleStickChart.invalidate()
                }

            }

            override fun onFailure(
                call: Call<ArrayList<CryptocurrencyOHLCVResponse>>,
                t: Throwable
            ) {
                println("Failed")
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