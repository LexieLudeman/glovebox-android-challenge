package com.gloveboxapp.androidchallenge.repository

import android.content.Context
import com.gloveboxapp.androidchallenge.data.Policy
import com.gloveboxapp.androidchallenge.data.PolicyType
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.io.IOException

interface GloveBoxRepository {

    val policyTypes: ArrayList<PolicyType>
    val policies: ArrayList<Policy>
    val gson: Gson

    // Function to parse policies.json
    fun getPolicies()

    // Function to parse policy_types.json
    fun getPolicyTypes()

    fun getJsonString(fileName: String) : String?

}

class GloveBoxRepositoryImpl(
    private val context: Context
) : GloveBoxRepository {
    override var policyTypes: ArrayList<PolicyType> = ArrayList()
    override var policies: ArrayList<Policy> = ArrayList()
    override val gson = Gson()

    init {
        getPolicies()
        getPolicyTypes()
    }

    override fun getPolicies() {
        val policiesString = getJsonString("policies.json")

        if (!policiesString.isNullOrEmpty()) {
            val policyListType = object: TypeToken<List<Policy>>(){}.type
            policies = gson.fromJson(policiesString, policyListType)
        }

    }

    override fun getPolicyTypes() {
        val policyTypeString = getJsonString("policy_types.json")

        if (!policyTypeString.isNullOrEmpty()) {
            val listPolicyType = object : TypeToken<List<PolicyType>>(){}.type
            policyTypes = gson.fromJson(policyTypeString, listPolicyType)
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
