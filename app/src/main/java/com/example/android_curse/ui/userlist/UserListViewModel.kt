package com.example.android_curse.ui.userlist

import androidx.lifecycle.viewModelScope
import com.example.android_curse.Api
import com.example.android_curse.entity.User
import com.example.android_curse.ui.base.BaseViewModel
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class UserListViewModel : BaseViewModel() {

    companion object {
        val logTag = "MyMainActivity"
    }

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)

    val viewState: Flow<ViewState> get() = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            _viewState.emit(ViewState.Loading)
            val userList = loadUsers()
            _viewState.emit(ViewState.Data(userList))
        }
    }

    sealed class ViewState {
        object Loading : ViewState()
        data class Data(val userList: List<User>) : ViewState()
    }

    private suspend fun loadUsers(): List<User> {
//        return  whithco Log.d(logTag,"loadUsers()")
        Thread.sleep(2000)
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