package com.gloveboxapp.androidchallenge.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.gloveboxapp.androidchallenge.R
import com.gloveboxapp.androidchallenge.data.Policy

class HomeInnerAdapter(
    private var policiesList: List<Policy>
) : RecyclerView.Adapter<HomeInnerAdapter.HomeInnerViewHolder>(){

    inner class HomeInnerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val policyTypeText: TextView = view.findViewById(R.id.policyTypeText)
        private val policyHolderText: TextView = view.findViewById(R.id.policyHolderName)
        private val policyNumberText: TextView = view.findViewById(R.id.policyNumberText)
        private val editButton: Button = view.findViewById(R.id.editButton)

        fun bind(policy: Policy) {
            if (policy.type.id == "auto") policyTypeText.text = "Auto"
            else policyTypeText.text = policy.type.name
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

            editButton.setOnClickListener {
                Log.d("Buttons", policy.policyNumber + " pressed")
                val editPolicy = HomeFragmentDirections.actionNavigationHomeToEditPolicyFragment(policy)
                it.findNavController().navigate(editPolicy)
            }
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