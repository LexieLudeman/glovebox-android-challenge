package com.gloveboxapp.androidchallenge.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Policy(
    var carrierID: String,
    var policyNumber: String,
    var type: PolicyType,
    var primaryHolder: PrimaryHolder,
    var agencyName: String
) : Parcelable
