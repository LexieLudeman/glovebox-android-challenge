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

    // Function to parse policies.json
    fun parsePolicies()

    // Function to parse policy_types.json
    fun parsePolicyTypes()

    fun getJsonString(fileName: String) : String?
}

class GloveBoxRepositoryImpl(
    private val context: Context
    )
    : GloveBoxRepository {
    override val policies: ArrayList<Policy> = ArrayList()
    override val policyTypes: ArrayList<PolicyType> = ArrayList()

    override fun parsePolicies() {
        TODO("Not yet implemented")
    }

    override fun parsePolicyTypes() {
        TODO("Not yet implemented")
        val policyTypeString = getJsonString("policy_types.json")
        policyTypeString?.let { Log.i("data", it) }

        if (!policyTypeString.isNullOrEmpty()) {
            val gson = Gson()
            val listPolicyType = object : TypeToken<List<PolicyType>>(){}.type

            policies = gson.fromJson(policyTypeString, listPolicyType)
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
