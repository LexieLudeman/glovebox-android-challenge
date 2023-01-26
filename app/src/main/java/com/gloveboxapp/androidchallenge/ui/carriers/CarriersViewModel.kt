package com.gloveboxapp.androidchallenge.ui.carriers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CarriersViewModel : ViewModel() {

/*
To do:
    Group the records in the policies array by carrierID and render one list for each carrier
    with each list containing the policies associated with that carrier
*/

    private val _text = MutableLiveData<String>().apply { value = "This is Carriers Fragment" }
    val text: LiveData<String> = _text
}
