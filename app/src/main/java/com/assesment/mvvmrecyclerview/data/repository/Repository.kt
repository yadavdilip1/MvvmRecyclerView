package com.assesment.mvvmrecyclerview.data.repository

import com.assesment.mvvmrecyclerview.data.network.Api
import com.assesment.mvvmrecyclerview.utils.SafeApiCall
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: Api
) : SafeApiCall {
    suspend fun getUsers() = safeApiCall { api.getUsers() }
}