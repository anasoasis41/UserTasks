package com.riahi.usertasks.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.riahi.usertasks.data.repositories.UsersRepository

class UserViewModel(val app: Application) : AndroidViewModel(app) {

    private val userRepository = UsersRepository(app)
    val usersListData = userRepository.userData

    fun refreshUsersData() {
        userRepository.usersDataFromWeb()
    }
}