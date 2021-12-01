package com.example.android_curse.repositories

import com.example.android_curse.data.network.Api
import com.example.android_curse.entity.User
import com.haroldadmin.cnradapter.NetworkResponse
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: Api
) {
    suspend fun getUsers(): NetworkResponse<List<User>, Unit> {
//        TODO("Not yet implemented")
        return api.getUsers()
    }

}