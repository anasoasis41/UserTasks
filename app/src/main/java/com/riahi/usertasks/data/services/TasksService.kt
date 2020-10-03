package com.riahi.usertasks.data.services

import com.riahi.usertasks.data.models.tasks.Tasks
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TasksService {
    @GET("/todos")
    suspend fun getUserTasks(
        @Query("userId") userId: Int): Response<List<Tasks>>
}