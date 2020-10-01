package com.riahi.usertasks.data.services

import com.riahi.usertasks.data.models.Users
import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET("/users/")
    suspend fun getUserData(): Response<List<Users>>
}