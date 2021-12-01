package com.example.android_curse.data.network.interceptor

import com.example.android_curse.repositories.AuthRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(
    private val authRepository: AuthRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain
                .request()
                .newBuilder()
                .apply {
                    runBlocking {
                        authRepository.getAuthTokensFlow().first()?.let {
                            addHeader("Authorization", "Bearer ${it.accessToken}")
                        }
                    }
                }
                .build()
        )
}