package com.gloveboxapp.androidchallenge.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gloveboxapp.androidchallenge.R
import com.gloveboxapp.androidchallenge.data.Policy

class HomeInnerAdapter(
    private var policiesList: ArrayList<Policy>
) : RecyclerView.Adapter<HomeInnerAdapter.HomeInnerViewHolder>(){

    inner class HomeInnerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val policyTypeText: TextView = view.findViewById(R.id.policyTypeText)
        private val policyHolderText: TextView = view.findViewById(R.id.policyHolderName)
        private val agencyNameText: TextView = view.findViewById(R.id.agencyNameText)
        private val policyNumberText: TextView = view.findViewById(R.id.policyNumberText)

        fun bind(policy: Policy) {
            policyTypeText.text = policy.type.name
            agencyNameText.text = policy.agencyName
            policyNumberText.text = policy.policyNumber

            val nameBuilder = StringBuilder()
            nameBuilder.append(policy.primaryHolder.firstName)
            nameBuilder.append(" ")
            if (policy.primaryHolder.middleName != null) {
                nameBuilder.append(policy.primaryHolder.middleName)
                nameBuilder.append(" ")
            }
            nameBuilder.append(policy.primaryHolder.lastName)
            policyHolderText.text = nameBuilder.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeInnerViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.home_inner_recyclerview, parent, false)
        return HomeInnerViewHolder(view)
    }

    override fun getItemCount() = policiesList.size

    override fun onBindViewHolder(holder: HomeInnerViewHolder, position: Int) {
        holder.bind(policiesList[position])
    }


}