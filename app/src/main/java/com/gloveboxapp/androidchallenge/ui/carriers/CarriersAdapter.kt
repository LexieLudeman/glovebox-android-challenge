package com.gloveboxapp.androidchallenge.ui.carriers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gloveboxapp.androidchallenge.R
import com.gloveboxapp.androidchallenge.data.Policy

// RecyclerView adapter for carriers page
class CarriersAdapter(
    private var carrierList: List<String>
) : RecyclerView.Adapter<CarriersAdapter.CarriersViewHolder>() {

    inner class CarriersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val carrierName: TextView = view.findViewById(R.id.carrier_name)

        fun bind(carrier: String) {
            carrierName.text = carrier
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarriersViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.carrier_recycler_view_item, parent, false)
        return CarriersViewHolder(view)
    }

    override fun getItemCount() = carrierList.size

    override fun onBindViewHolder(holder: CarriersViewHolder, position: Int) {
        holder.bind(carrierList[position])
    }

    fun updateCarriersList(policies: List<Policy>) {
        val carrierSet: HashSet<String> = HashSet()
        for (p in policies) {
            carrierSet.add(p.carrierID)
        }
        carrierList = carrierSet.toList()
    }
}