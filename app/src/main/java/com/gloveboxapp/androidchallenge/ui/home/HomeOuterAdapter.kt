package com.gloveboxapp.androidchallenge.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gloveboxapp.androidchallenge.R
import com.gloveboxapp.androidchallenge.data.Policy

class HomeOuterAdapter(
    private val context: Context,
    private var policiesMap: HashMap<String, ArrayList<Policy>>
) : RecyclerView.Adapter<HomeOuterAdapter.HomeOuterViewHolder>(){

    private lateinit var innerAdapter: HomeInnerAdapter
    inner class HomeOuterViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val carrierId: TextView = view.findViewById(R.id.carrierId)
        val innerRecyclerView: RecyclerView = view.findViewById(R.id.policiesRecyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeOuterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.home_outer_recyclerview, parent, false)
        return HomeOuterViewHolder(view)
    }

    override fun getItemCount() = policiesMap.size

    override fun onBindViewHolder(holder: HomeOuterViewHolder, position: Int) {
        val currentCarrier = policiesMap.keys.elementAt(position)
        val policiesList = policiesMap[currentCarrier]
        holder.carrierId.text = currentCarrier

        if (policiesList != null) {
            innerAdapter = HomeInnerAdapter(policiesList)
            holder.innerRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            holder.innerRecyclerView.adapter = innerAdapter
        }
    }

    fun updatePolicies(policies: Map<String, List<Policy>>) {
        policiesMap = policies as HashMap<String, ArrayList<Policy>>
        notifyDataSetChanged()
    }

}