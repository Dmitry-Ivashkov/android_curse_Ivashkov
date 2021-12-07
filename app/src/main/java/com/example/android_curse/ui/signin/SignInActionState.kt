package com.example.android_curse.ui.signin

import com.example.android_curse.data.network.response.error.SignInWithEmailErrorResponse
import com.haroldadmin.cnradapter.NetworkResponse

sealed class SignInActionState {
    object Pending : SignInActionState()
    object Loading : SignInActionState()
    data class ServerError(val e: NetworkResponse.ServerError<SignInWithEmailErrorResponse>) : SignInActionState()
    data class NetworkError(val e: NetworkResponse.NetworkError) : SignInActionState()
    data class UnknownError(val e: NetworkResponse.UnknownError) : SignInActionState()
}