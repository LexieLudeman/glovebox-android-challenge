package com.gloveboxapp.androidchallenge.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gloveboxapp.androidchallenge.data.Policy
import com.gloveboxapp.androidchallenge.data.PolicyType
import com.gloveboxapp.androidchallenge.repository.GloveBoxRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: GloveBoxRepositoryImpl
//    ,
//    val store: Store<ApplicationState>
): ViewModel() {

    private val _policies: MutableLiveData<List<Policy>> = MutableLiveData()
    val policies: LiveData<List<Policy>> = _policies

    private val _policyTypes: MutableLiveData<List<PolicyType>> = MutableLiveData()
    val policyTypes: LiveData<List<PolicyType>> = _policyTypes

    fun getPoliciesFromRepo() {
        viewModelScope.launch {
            val policyList = repository.policies
            _policies.value = policyList

//            store.update { applicationState ->
//                return@update applicationState.copy(
//                    policies = policyList
//                )
//            }
        }
    }

    fun getTypesFromRepo() {
        viewModelScope.launch {
            val policyTypes = repository.policyTypes
            _policyTypes.value = policyTypes
        }
    }
}
