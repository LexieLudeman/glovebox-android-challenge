package com.gloveboxapp.androidchallenge.repository

import android.content.Context
import android.util.Log
import com.gloveboxapp.androidchallenge.data.Policy
import com.gloveboxapp.androidchallenge.data.PolicyType
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.io.IOException

interface GloveBoxRepository {

    val policies: ArrayList<Policy>
    val policyTypes: ArrayList<PolicyType>
    val gson: Gson

    // Function to parse policies.json
    fun getPolicies()

    // Function to parse policy_types.json
    fun getPolicyTypes()

    fun getJsonString(fileName: String) : String?
}

class GloveBoxRepositoryImpl(
    private val context: Context
    )
    : GloveBoxRepository {
    override var policies: ArrayList<Policy> = ArrayList()
    override var policyTypes: ArrayList<PolicyType> = ArrayList()
    override val gson = Gson()

    override fun getPolicies() {
        val policiesString = getJsonString("policies.json")
        policiesString?.let { Log.i("data", it) }

        if (!policiesString.isNullOrEmpty()) {
            val listPolicies = object: TypeToken<List<Policy>>(){}.type

            policies = gson.fromJson(policiesString, listPolicies)
            policies.forEachIndexed {
                    index, policy ->  Log.i("data", ">Item $index:\n$policy")}
        }
    }

    override fun getPolicyTypes() {
        val policyTypeString = getJsonString("policy_types.json")
        policyTypeString?.let { Log.i("data", it) }

        if (!policyTypeString.isNullOrEmpty()) {
            val listPolicyType = object : TypeToken<List<PolicyType>>(){}.type

            policyTypes = gson.fromJson(policyTypeString, listPolicyType)
            policyTypes.forEachIndexed {
                    index, policyType -> Log.i("data", "> Item $index:\n$policyType")

            }
        }
    }


    override fun getJsonString(fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context
                .assets
                .open(fileName)
                .bufferedReader()
                .use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }

}
