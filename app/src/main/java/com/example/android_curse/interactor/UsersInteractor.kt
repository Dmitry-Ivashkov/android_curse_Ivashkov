package com.example.android_curse.interactor

import com.example.android_curse.entity.User
import com.example.android_curse.repositories.UserRepository
import com.haroldadmin.cnradapter.NetworkResponse
import javax.inject.Inject

class UsersInteractor @Inject constructor(
    private val usersRepository: UserRepository
) {
    suspend fun loadUsers(): NetworkResponse<List<User>, Unit> {
//        TODO("Not yet implemented")
        return usersRepository.getUsers()
    }
}