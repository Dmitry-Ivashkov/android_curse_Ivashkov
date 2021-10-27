package com.example.android_curse.repositories

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object AuthRepository {
    private var _isAuthorizedFlow = MutableStateFlow(false)

    val isAuthorizedFlow: StateFlow<Boolean> get() = _isAuthorizedFlow

    suspend fun signIn() {
        _isAuthorizedFlow.emit(true)
    }

    suspend fun logOut() {
        _isAuthorizedFlow.emit(false)
    }
}