package com.gloveboxapp.androidchallenge.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gloveboxapp.androidchallenge.redux.ApplicationState
import com.gloveboxapp.androidchallenge.redux.Store
import com.gloveboxapp.androidchallenge.repository.GloveBoxRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: GloveBoxRepositoryImpl,
    val store: Store<ApplicationState>
): ViewModel() {

    fun getPoliciesFromRepo() {
        viewModelScope.launch {
            val policyList = repository.policies
            store.update { applicationState ->
                return@update applicationState.copy(
                    policies = policyList
                )
            }
        }
    }

    fun getTypesFromRepo() {
        viewModelScope.launch {
            val policyTypes = repository.policyTypes
            store.update { applicationState ->
                return@update applicationState.copy(
                    policyTypes = policyTypes
                )
            }
        }
    }
}
