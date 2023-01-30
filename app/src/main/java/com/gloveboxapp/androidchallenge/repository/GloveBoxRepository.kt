package com.gloveboxapp.androidchallenge.repository

import android.content.Context
import com.gloveboxapp.androidchallenge.data.Policy
import com.gloveboxapp.androidchallenge.data.PolicyType
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

interface GloveBoxRepository {

    val policyTypes: ArrayList<PolicyType>
    val policies: ArrayList<Policy>
    val gson: Gson

    fun getPolicies()

    fun getPolicyTypes()

    fun getJsonString(fileName: String): String?

}

/*
    Singleton class to get the policies and policyTypes from the json files and cache them
 */
@Singleton
class GloveBoxRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : GloveBoxRepository {
    override var policyTypes: ArrayList<PolicyType> = ArrayList()
    override var policies: ArrayList<Policy> = ArrayList()
    override val gson = Gson()

    init {
        getPolicies()
        getPolicyTypes()
    }

    // Method to read policies.json and fill the policies array
    override fun getPolicies() {
        val policiesString = getJsonString("policies.json")

        if (!policiesString.isNullOrEmpty()) {
            val policyListType = object : TypeToken<List<Policy>>() {}.type
            policies = gson.fromJson(policiesString, policyListType)
        }

    }

    // Method to read policy_types.json and fill the policyType array
    override fun getPolicyTypes() {
        val policyTypeString = getJsonString("policy_types.json")

        if (!policyTypeString.isNullOrEmpty()) {
            val listPolicyType = object : TypeToken<List<PolicyType>>() {}.type
            policyTypes = gson.fromJson(policyTypeString, listPolicyType)
        }
    }

    // Method to read a json and return a string for conversion
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
