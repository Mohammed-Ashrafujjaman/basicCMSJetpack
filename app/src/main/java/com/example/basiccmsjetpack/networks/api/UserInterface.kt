package com.example.basiccmsjetpack.networks.api

import com.example.basiccmsjetpack.models.UserDataModelItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserInterface {
    @GET("users")
    suspend fun getUserData(@Query("page") page: Long): Response<List<UserDataModelItem>>

    @POST("users")
    suspend fun addUser(@Body user: UserDataModelItem): Response<UserDataModelItem>
}
