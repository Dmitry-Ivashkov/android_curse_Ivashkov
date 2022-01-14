package com.example.android_curse.data.network

import com.example.android_curse.data.network.request.CreateProfileRequest
import com.example.android_curse.data.network.request.RefreshAuthTokensRequest
import com.example.android_curse.data.network.request.SignInWithEmailRequest
import com.example.android_curse.data.network.response.VerificationTokenResponse
import com.example.android_curse.data.network.response.error.*
import com.example.android_curse.entity.AuthTokens
import com.example.android_curse.entity.Lakes
import com.example.android_curse.entity.Post
import com.example.android_curse.entity.User
import com.haroldadmin.cnradapter.NetworkResponse
import com.squareup.moshi.Json

class MockApi : Api {
    override suspend fun getUsers(): NetworkResponse<List<User>, Unit> {
        return NetworkResponse.Success(
            listOf(
                User(
                    id = 7,
                    firstName = "bob",
                    lastName = "bi",
                    userName = "biBob"
                )
            ).flatMap { listOf(it, it, it, it, it, it) }.flatMap { listOf(it, it, it, it, it, it) },
            code=200
        )
    }

//    override suspend fun getUsers(): GetUsersResponse {
//        return GetUsersResponse(emptyList())
//    }

    override suspend fun signInWithEmail(request: SignInWithEmailRequest): NetworkResponse<AuthTokens, SignInWithEmailErrorResponse> {
        return NetworkResponse.Success(
            AuthTokens(
                accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsb2dnZWRJbkFzIjoiYWRtaW4iLCJpYXQiOjE0MjI3Nzk2MzgsImV4cCI6MTY0MDg3MTc3MX0.gzSraSYS8EXBxLN_oWnFSRgCzcmJmMjLiuyu5CSpyHI",
                refreshToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsb2dnZWRJbkFzIjoiYWRtaW4iLCJpYXQiOjE0MjI3Nzk2MzgsImV4cCI6MTY0MDg3MTc3MX0.gzSraSYS8EXBxLN_oWnFSRgCzcmJmMjLiuyu5CSpyHI",
                accessTokenExpiration = 1640871771000,
                refreshTokenExpiration = 1640871771000,
            ),
            code = 200
        )
    }

    override suspend fun refreshAuthTokens(request: RefreshAuthTokensRequest): NetworkResponse<AuthTokens, RefreshAuthTokensErrorResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun sendRegistrationVerificationCode(email: String): NetworkResponse<Unit, SendRegistrationVerificationCodeErrorResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun verifyRegistrationCode(
        code: String,
        email: String
    ): NetworkResponse<VerificationTokenResponse, VerifyRegistrationCodeErrorResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun createProfile(request: CreateProfileRequest): NetworkResponse<AuthTokens, CreateProfileErrorResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getPosts(): NetworkResponse<List<Post>, Unit> {
        return NetworkResponse.Success(
            listOf(
                Post(
                    id = 7,
                    linkUrl = null,
                    imageUrl = null,
                    title = "aaaaaaaaaaaaaa",
                    text = null,
                    createAt = "",
                    updateAt = "",
                    likes = Lakes(listOf(),15)
                )
            ),
            code=200
        )
    }
}