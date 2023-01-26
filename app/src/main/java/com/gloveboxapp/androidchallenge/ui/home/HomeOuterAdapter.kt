package com.gloveboxapp.androidchallenge.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gloveboxapp.androidchallenge.R
import com.gloveboxapp.androidchallenge.data.Policy

class HomeOuterAdapter(
    private var policiesMap: HashMap<String, ArrayList<Policy>>
) : RecyclerView.Adapter<HomeOuterAdapter.HomeOuterViewHolder>(){

    inner class HomeOuterViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val carrierId: TextView = view.findViewById(R.id.carrierId)

        fun bind(carrier: String) {
            carrierId.text = carrier
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeOuterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.home_outer_recyclerview, parent, false)
        return HomeOuterViewHolder(view)
    }

    override fun getItemCount() = policiesMap.size

    override fun onBindViewHolder(holder: HomeOuterViewHolder, position: Int) {
        holder.bind(policiesMap.keys.elementAt(position))
    }

}