package com.gloveboxapp.androidchallenge.ui.carriers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CarriersViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply { value = "This is Carriers Fragment" }
    val text: LiveData<String> = _text
}
