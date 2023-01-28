package com.gloveboxapp.androidchallenge.redux

import com.gloveboxapp.androidchallenge.data.Policy
import com.gloveboxapp.androidchallenge.data.PolicyType

data class ApplicationState(
    val policies: List<Policy> = emptyList(),
    val policyTypes: List<PolicyType> = emptyList()
)
