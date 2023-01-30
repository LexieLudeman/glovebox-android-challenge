package com.gloveboxapp.androidchallenge.ui.account

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gloveboxapp.androidchallenge.R
import com.gloveboxapp.androidchallenge.data.Policy
import com.gloveboxapp.androidchallenge.data.PrimaryHolder

// RecyclerView adapter for account page
class AccountAdapter(
    private var policyHolderList: List<String>
) : RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {

    inner class AccountViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val policyHolderName: TextView = view.findViewById(R.id.policy_holder_name)

        fun bind(policyHolder: String) {
            policyHolderName.text = policyHolder
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.account_recycler_view_item, parent, false)
        return AccountViewHolder(view)
    }

    override fun getItemCount() = policyHolderList.size

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        holder.bind(policyHolderList[position])
    }

    fun updatePolicyHolderList(policies: List<Policy>) {
        val holdersSet: HashSet<String> = HashSet()
        for (p in policies) {
            holdersSet.add(convertName(p.primaryHolder))
        }
        policyHolderList = holdersSet.toList()
    }

    private fun convertName(holder: PrimaryHolder): String {
        val nameBuilder = StringBuilder()
        nameBuilder.append(holder.firstName)
        nameBuilder.append(" ")
        if (holder.middleName != null) {
            nameBuilder.append(holder.middleName)
            nameBuilder.append(" ")
        }
        nameBuilder.append(holder.lastName)
        return nameBuilder.toString()
    }
}