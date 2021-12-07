package com.example.android_curse.ui

import com.example.android_curse.interactor.AuthInteractor
import com.example.android_curse.repositories.AuthRepositoryOld
import com.example.android_curse.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NewMainViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): BaseViewModel() {
//    val isAuthorizedFlow: Flow<Boolean> = MutableStateFlow<Boolean>(true)

    suspend fun isAuthorizedFlow():Flow<Boolean>{
        return authInteractor.isAuthorized()
    }
}
