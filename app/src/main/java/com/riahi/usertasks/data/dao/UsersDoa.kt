package com.riahi.usertasks.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.riahi.usertasks.data.models.users.Users

@Dao
interface UsersDoa {

    @Query("SELECT * from users")
    fun getAllUsers(): List<Users>

    @Insert
    suspend fun insertUsers(users: List<Users>)

    @Query("DELETE from users")
    suspend fun deleteAllUsers()
}