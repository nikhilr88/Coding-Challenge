package com.example.microproject.account.login.data.repository

import com.example.microproject.account.login.data.util.UserInfo

interface IAccountRepository {
    suspend fun getUserInfo(): UserInfo
}