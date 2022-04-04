package com.example.crypto

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon


class OverviewFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: CryptoAdapter
    lateinit var queue: RequestQueue
    lateinit var coinName: TextView
    lateinit var coinPrice: TextView
    var coinList = listOf<Coin>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        queue = Volley.newRequestQueue(activity)

        recyclerView = view.findViewById(R.id.recyclerView2)
        layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        adapter = CryptoAdapter(coinList) { coin ->
            coinPrice.text = coin.priceUsd

        }
        recyclerView.adapter = adapter

        getCryptoAPI(Volley.newRequestQueue(requireContext())) { coin ->
            // hva vi ønsker å gjøre med callback her

            //coinName = view.findViewById(R.id.textViewCoinName)
            //coinPrice = view.findViewById(R.id.textViewCoinPrice)

            adapter.updateData(coin?.data ?: listOf())

            Toast.makeText(this.requireContext(), "You get information", Toast.LENGTH_SHORT).show()

        }
    }

    fun getCryptoAPI(requestQueue: RequestQueue, callback: (CryptoObject?) -> Unit){

        // Instantiate the RequestQueue.
         val queue = Volley.newRequestQueue(activity)
        val url = "https://api.coincap.io/v2/assets"

// Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
             { json ->
                 val money = Klaxon().parse<CryptoObject>(json)
                 callback(money)


            },
             {

             })

// Add the request to the RequestQueue.
      //  queue.add(stringRequest)
      requestQueue.add(stringRequest)

    }



}