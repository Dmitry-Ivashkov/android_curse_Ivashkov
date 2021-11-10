package com.example.android_curse.ui.signin

import androidx.lifecycle.viewModelScope
import com.example.android_curse.repositories.AuthRepository
import com.example.android_curse.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import java.net.PasswordAuthentication

class SignInViewModel : BaseViewModel() {
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            AuthRepository.signIn(email,password)
        }
    }
}