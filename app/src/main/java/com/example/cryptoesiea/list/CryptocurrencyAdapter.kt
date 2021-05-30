package com.example.cryptoesiea.list

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoesiea.R
import com.example.cryptoesiea.api.CryptocurrencyListResponse
import com.example.cryptoesiea.detail.CryptocurrencyDetails
import com.squareup.picasso.Picasso
const val EXTRA_MESSAGE = "com.example.cryptoesiea.MESSAGE"

class CryptocurrencyAdapter(private var dataSet: ArrayList<CryptocurrencyListResponse>) :
    RecyclerView.Adapter<CryptocurrencyAdapter.ViewHolder>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var id: String = ""
        val name: TextView
        val symbol: TextView
        val cryptoImg: ImageView
        val price: TextView
        val percentChange1H: TextView
        val percentChange24H: TextView
        val percentChange7D: TextView



        init {
            // Define click listener for the ViewHolder's View.
            name = view.findViewById(R.id.crypto_name)
            symbol = view.findViewById(R.id.crypto_symbol)
            cryptoImg = view.findViewById(R.id.crypto_img)
            price = view.findViewById(R.id.price)
            percentChange1H = view.findViewById(R.id.percent_change_1h)
            percentChange24H = view.findViewById(R.id.percent_change_24h)
            percentChange7D = view.findViewById(R.id.percent_change_7d)

            view.setOnClickListener {
                val intent = Intent(view.context, CryptocurrencyDetails::class.java).apply {
                    putExtra("EXTRA_MESSAGE", id)
                }
                view.context.startActivity(intent)
            }
        }
    }

    fun updateList(item: ArrayList<CryptocurrencyListResponse>) {
        dataSet = item;
        notifyDataSetChanged()

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cryptocurrency_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.name.text = dataSet[position].name
        viewHolder.symbol.text = dataSet[position].symbol
        viewHolder.price.text = dataSet[position].quotes.EUR.price.toString() + "€"
        viewHolder.percentChange1H.text = dataSet[position].quotes.EUR.percent_change_1h.toString() + "%"
        viewHolder.percentChange24H.text = dataSet[position].quotes.EUR.percent_change_24h.toString() + "%"
        viewHolder.percentChange7D.text = dataSet[position].quotes.EUR.percent_change_7d.toString() + "%"
        viewHolder.id = dataSet[position].id

        if (dataSet[position].quotes.EUR.percent_change_1h.toString().contains("-"))
            viewHolder.percentChange1H.setTextColor(Color.parseColor("#FF0000"))
        else
            viewHolder.percentChange1H.setTextColor(Color.parseColor("#07b607"))

        if (dataSet[position].quotes.EUR.percent_change_24h.toString().contains("-"))
            viewHolder.percentChange24H.setTextColor(Color.parseColor("#FF0000"))
        else
            viewHolder.percentChange24H.setTextColor(Color.parseColor("#07b607"))

        if (dataSet[position].quotes.EUR.percent_change_7d.toString().contains("-"))
            viewHolder.percentChange7D.setTextColor(Color.parseColor("#FF0000"))
        else
            viewHolder.percentChange7D.setTextColor(Color.parseColor("#07b607"))


        val link = StringBuilder()
        link.append("https://static.coincap.io/assets/icons/").append(dataSet[position].symbol.toLowerCase()).append("@2x.png")
        Picasso.get().load(link.toString()).into(viewHolder.cryptoImg)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}
