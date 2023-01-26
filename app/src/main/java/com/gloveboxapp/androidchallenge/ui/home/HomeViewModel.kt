package com.gloveboxapp.androidchallenge.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gloveboxapp.androidchallenge.data.Policy
import com.gloveboxapp.androidchallenge.data.PolicyType
import com.gloveboxapp.androidchallenge.repository.GloveBoxRepositoryImpl
import kotlinx.coroutines.launch

class HomeViewModel (
    private val repository: GloveBoxRepositoryImpl
): ViewModel() {

/*
To do:
    Load the policies into a recyclerview that is retrieved by the HomeViewModel
*/
    private val _policies: MutableLiveData<Map<String, List<Policy>>> = MutableLiveData()
    val policies: LiveData<Map<String,List<Policy>>> = _policies

    private val _policyTypes: MutableLiveData<List<PolicyType>> = MutableLiveData()
    val policyTypes: LiveData<List<PolicyType>> = _policyTypes

    fun getPoliciesFromRepo() {
        viewModelScope.launch {
            val policies = repository.policies
            _policies.value = policies
        }
    }

    fun getTypesFromRepo() {
        viewModelScope.launch {
            val policyTypes = repository.policyTypes
            _policyTypes.value = policyTypes
        }
    }
}
