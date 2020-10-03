package com.riahi.usertasks.data.models.tasks

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Tasks (
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String,
    val completed: Boolean
)