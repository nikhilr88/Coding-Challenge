package com.example.microproject.account.login.data.repository.account

import com.example.microproject.account.login.data.util.UserInfo
import pt.deloitte.smart_connect.domain.repository.IAccountRepository

class AccountRepository() : IAccountRepository{

    override suspend fun getUserInfo(): UserInfo {
        // Call API to get data from Firebase Database or AWS
        return UserInfo("Nikhil", "nikhilr88@gmail.com","123456789")
    }
}