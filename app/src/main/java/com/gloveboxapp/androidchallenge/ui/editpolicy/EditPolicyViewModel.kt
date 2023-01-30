package com.gloveboxapp.androidchallenge.ui.editpolicy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gloveboxapp.androidchallenge.data.Policy
import com.gloveboxapp.androidchallenge.redux.ApplicationState
import com.gloveboxapp.androidchallenge.redux.Store
import com.gloveboxapp.androidchallenge.repository.GloveBoxRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditPolicyViewModel @Inject constructor(
    private val repository: GloveBoxRepositoryImpl,
    val store: Store<ApplicationState>
) : ViewModel() {

    /*
        Method that checks to see if the store has a policyType list in it and if not
        will pull the policyTypes from the repository
    */
    fun getTypesFromRepo() = viewModelScope.launch {
        if (store.read { it.policyTypes }.isNotEmpty()) return@launch
        val policyTypes = repository.policyTypes
        store.update { applicationState ->
            return@update applicationState.copy(
                policyTypes = policyTypes
            )
        }
    }

    /*
        Method that replaces the policy list in the store with an updated version
    */
    fun updateStorePolicies(policies: ArrayList<Policy>) = viewModelScope.launch {
        store.update { applicationState ->
            return@update applicationState.copy(
                policies = policies
            )
        }
    }

}