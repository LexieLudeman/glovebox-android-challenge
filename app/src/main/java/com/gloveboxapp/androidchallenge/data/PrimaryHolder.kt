package com.gloveboxapp.androidchallenge.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PrimaryHolder(
    var firstName: String,
    var middleName: String?,
    var lastName: String
) : Parcelable
