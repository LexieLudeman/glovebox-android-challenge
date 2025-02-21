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

// Recyclerview Adapter for the outer recyclerview found in fragment_home.xml
class HomeOuterAdapter(
    private val context: Context,
    policyList: List<Policy>
) : RecyclerView.Adapter<HomeOuterAdapter.HomeOuterViewHolder>() {

    private lateinit var innerAdapter: HomeInnerAdapter
    private var policiesMap: HashMap<String, List<Policy>> = HashMap()

    init {
        createPolicyMap(policyList)
    }

    // Method to fill the policiesMap variable
    private fun createPolicyMap(list: List<Policy>) {
        for (p in list) {
            val carrierPolicyList = policiesMap.getOrDefault(p.carrierID, emptyList()).toMutableList()
            carrierPolicyList += p
            policiesMap[p.carrierID] = carrierPolicyList
        }
    }

    // Inner class for the recyclerview and textview in fragment_home.xml
    inner class HomeOuterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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

    /*
        Method that takes each key-value pair from the map and passes the value into
        the HomeInnerAdapter to form the nested recyclerview
    */
    override fun onBindViewHolder(holder: HomeOuterViewHolder, position: Int) {
        val currentCarrier = policiesMap.keys.elementAt(position)
        val policiesList = policiesMap[currentCarrier]
        holder.carrierId.text = currentCarrier

        if (policiesList != null) {
            innerAdapter = HomeInnerAdapter(policiesList)
            holder.innerRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            holder.innerRecyclerView.adapter = innerAdapter
        }
    }

    fun updatePolicies(policies: List<Policy>) {
        createPolicyMap(policies)
    }
}