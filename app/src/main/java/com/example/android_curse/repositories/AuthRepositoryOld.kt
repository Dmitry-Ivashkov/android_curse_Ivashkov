package com.example.android_curse.repositories

import android.app.usage.UsageEvents
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object AuthRepositoryOld {

    private var _isAuthorizedFlow = MutableStateFlow(false)
//    private var _isAuthorizedFlow = MutableStateFlow(true)

    val isAuthorizedFlow: StateFlow<Boolean> get() = _isAuthorizedFlow

    suspend fun signIn(email: String, password: String) {
        _isAuthorizedFlow.emit(true)
    }

    suspend fun logOut() {
        _isAuthorizedFlow.emit(false)
    }

    suspend fun signUp(
        firstname: String,
        lastname: String,
        nickname: String,
        email: String,
        password: String
    ) {
        //TODO: Get API response for email availability, change screen to email confirm
    }
}