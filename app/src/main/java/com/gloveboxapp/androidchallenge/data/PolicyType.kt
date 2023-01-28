package com.gloveboxapp.androidchallenge.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PolicyType(
    var id: String,
    var name: String
) : Parcelable
