package com.assesment.mvvmrecyclerview.data.network

import com.assesment.mvvmrecyclerview.data.model.User
import retrofit2.http.GET

interface Api {

    @GET("users")
    suspend fun getUsers(): List<User>

}