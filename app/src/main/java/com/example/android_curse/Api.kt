package com.example.android_curse

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.http.GET

interface Api {

    @GET("users?per_page=100")
    suspend fun getUsers(): GetUsersResponse
}

@JsonClass(generateAdapter = true)
data class GetUsersResponse(
    @Json(name = "data") val data: List<User>
)