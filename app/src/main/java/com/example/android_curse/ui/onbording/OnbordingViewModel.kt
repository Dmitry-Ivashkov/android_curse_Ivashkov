package com.example.android_curse.ui.onbording


import com.example.android_curse.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class OnbordingViewModel : BaseViewModel() {

    private val _isOffVolumeControlStateFlow = MutableStateFlow<Boolean>(true)

    fun isOffVolumeControlStateFlow(): Flow<Boolean> = _isOffVolumeControlStateFlow

    fun change() {
        val compareAndSet : Boolean = _isOffVolumeControlStateFlow.compareAndSet(true, false)
        if (!compareAndSet){
            _isOffVolumeControlStateFlow.compareAndSet(false, true)
        }
    }

}