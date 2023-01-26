package com.gloveboxapp.androidchallenge.redux

import com.gloveboxapp.androidchallenge.data.Policy

data class ApplicationState(

    val policies: List<Policy> = emptyList()

)
