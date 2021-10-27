package com.example.android_curse.ui.signin

import androidx.lifecycle.viewModelScope
import com.example.android_curse.repositories.AuthRepository
import com.example.android_curse.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SignInViewModel : BaseViewModel() {
    fun signIn() {
        viewModelScope.launch {
            AuthRepository.signIn()
        }
    }
}