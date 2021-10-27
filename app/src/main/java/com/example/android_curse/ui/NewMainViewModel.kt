package com.example.android_curse.ui

import com.example.android_curse.repositories.AuthRepository
import com.example.android_curse.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

class NewMainViewModel : BaseViewModel() {
    val isAuthorizedFlow: Flow<Boolean> = AuthRepository.isAuthorizedFlow
}
