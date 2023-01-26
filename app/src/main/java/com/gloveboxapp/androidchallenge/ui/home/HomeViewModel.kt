package com.gloveboxapp.androidchallenge.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gloveboxapp.androidchallenge.repository.GloveBoxRepositoryImpl

class HomeViewModel (
    private val repository: GloveBoxRepositoryImpl
): ViewModel() {

/*
To do:
    Load the policies into a recyclerview that is retrieved by the HomeViewModel
*/


    private val _text = MutableLiveData<String>().apply { value = "This is home Fragment" }
    val text: LiveData<String> = _text
}
