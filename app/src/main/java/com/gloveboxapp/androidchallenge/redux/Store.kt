package com.gloveboxapp.androidchallenge.redux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/*
    Class for application's redux store
*/
class Store<T>(initialState: T) {

    private val _stateFlow = MutableStateFlow(initialState)
    val stateFlow: StateFlow<T> = _stateFlow

    private val mutex = Mutex()

    // Method to update any store data
    suspend fun update(updateBlock: (T) -> T) = mutex.withLock {
        val newState = updateBlock(_stateFlow.value)
        _stateFlow.value = newState
    }

    // Method to read any store data
    suspend fun <S> read(readBlock: (T) -> S) = mutex.withLock {
        readBlock(_stateFlow.value)
    }
}