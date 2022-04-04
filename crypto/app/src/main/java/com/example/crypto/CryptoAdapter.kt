package com.example.crypto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon

class CryptoAdapter(var dataSet: List<Coin>, val callback: (Coin) -> Unit) :
    RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {


    inner class CryptoViewHolder(cellView: View) : RecyclerView.ViewHolder(
        cellView
    ) {
        var coinName: TextView = cellView.findViewById(R.id.textViewCoinName)
        var coinPrice: TextView = cellView.findViewById(R.id.textViewCoinPrice)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val cellView = LayoutInflater.from(parent.context)
            .inflate(R.layout.simple_chat_cell_item, parent, false)


        return CryptoViewHolder(cellView)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {

        val cryptoObject = dataSet[position]

        holder.coinName.text = cryptoObject.name
        holder.coinPrice.text = cryptoObject.priceUsd
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateData(list: List<Coin>){
        dataSet = list
        notifyDataSetChanged()
    }


}


