package com.riahi.usertasks.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.riahi.usertasks.data.models.tasks.Tasks

@Dao
interface TasksDao {

    @Query("SELECT * from tasks")
    fun getAllTasks(): List<Tasks>

    @Query("SELECT * from tasks WHERE userId = :idUser")
    fun getTaskById(idUser: Int): List<Tasks>

    @Query("SELECT EXISTS(SELECT * FROM tasks WHERE userId = :idUser)")
    fun isTaskExist(idUser : Int) : Boolean

    @Insert
    suspend fun insertTasks(users: List<Tasks>)

    @Query("DELETE from tasks")
    suspend fun deleteAllTasks()
}