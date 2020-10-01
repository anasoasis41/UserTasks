package com.riahi.usertasks.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Users (
    @PrimaryKey
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val website: String
)