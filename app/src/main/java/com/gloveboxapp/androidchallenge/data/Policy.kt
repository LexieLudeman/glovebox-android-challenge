package com.gloveboxapp.androidchallenge.data

data class Policy(
    var carrierID: String,
    var policyNumber: String,
    var type: PolicyType,
    var primaryHolder: PrimaryHolder,
    var agencyName: String
)
