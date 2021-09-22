package com.example.android_curse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {

    var viewState: StateFlow<ViewState> = flow<ViewState> { ViewState.Data(loadUsers()) }.stateIn(viewModelScope,
        SharingStarted.Eagerly, ViewState.Loading)//ViewState.Loading

//    init {
//        viewModelScope.launch {
//            viewState = ViewState.Loading
//            viewState = ViewState.Data(loadUsers())
//        }
//    }

    sealed class ViewState {
        object Loading : ViewState()
        data class Data(val userList: List<User>) : ViewState()
    }

    private suspend fun loadUsers(): List<User> {
//        Thread.sleep(2000)
        return provideApi().getUsers().data
    }

    private fun provideApi(): Api {
        return Retrofit.Builder()
            .client(provideOkHttpClient())
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
            .build()
            .create(Api::class.java)
    }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    private fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

}