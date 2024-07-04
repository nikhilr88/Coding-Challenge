package com.example.microproject.account.login.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.microproject.account.login.data.util.UserInfo
import com.example.microproject.common.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import pt.deloitte.smart_connect.domain.repository.IAccountRepository

class LoginViewModel(
    private val accountRepository: IAccountRepository
) : BaseViewModel() {
    private val _userInfo = MutableSharedFlow<UserInfo>()
    private val userInfo: SharedFlow<UserInfo> = _userInfo.asSharedFlow()


    fun getLoginData() {
        viewModelScope.launch {
            _userInfo.emit(accountRepository.getUserInfo())
        }

    }
}