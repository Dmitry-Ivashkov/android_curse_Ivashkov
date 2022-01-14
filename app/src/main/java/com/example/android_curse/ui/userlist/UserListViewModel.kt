package com.example.android_curse.ui.userlist

import androidx.lifecycle.viewModelScope
import com.example.android_curse.data.network.Api
import com.example.android_curse.entity.User
import com.example.android_curse.interactor.UsersInteractor
import com.example.android_curse.ui.base.BaseViewModel
import com.haroldadmin.cnradapter.NetworkResponse
import com.squareup.moshi.Moshi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val usersInteractor : UsersInteractor
) : BaseViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)

    val viewState: Flow<ViewState> get() = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            _viewState.emit(ViewState.Loading)
            loadUsers()
//            _viewState.emit(ViewState.Data(userList))
        }
    }

    sealed class ViewState {
        object Loading : ViewState()
        object Error : ViewState()
        object EmptyList : ViewState()
        data class Data(val userList: List<User>) : ViewState()
    }

//    init {
//        loadUsers()
//    }

    private fun loadUsers() {
        viewModelScope.launch {
            _viewState.emit(ViewState.Loading)
            when (val response = usersInteractor.loadUsers()){
                is NetworkResponse.Success -> {
                    val userList = response.body
                    if (userList.isEmpty()){
                        _viewState.emit(ViewState.EmptyList)
                    }else {
                        _viewState.emit(ViewState.Data(userList))
                    }
                }
                else -> {
                    _viewState.emit(ViewState.Error)
                }
            }
        }
    }

}