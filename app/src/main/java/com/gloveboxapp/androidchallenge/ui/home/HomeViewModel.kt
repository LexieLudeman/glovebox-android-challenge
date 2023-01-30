package com.gloveboxapp.androidchallenge.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gloveboxapp.androidchallenge.redux.ApplicationState
import com.gloveboxapp.androidchallenge.redux.Store
import com.gloveboxapp.androidchallenge.repository.GloveBoxRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel for Home Fragment
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: GloveBoxRepositoryImpl,
    val store: Store<ApplicationState>
) : ViewModel() {

    /*
        Method that checks to see if the store has a policies list in it and if not
        will pull the policies from the repository
    */
    fun getPoliciesFromRepo() = viewModelScope.launch {
        if (store.read { it.policies }.isNotEmpty()) return@launch
        val policyList = repository.policies
        store.update { applicationState ->
            return@update applicationState.copy(
                policies = policyList
            )
        }
    }

}
