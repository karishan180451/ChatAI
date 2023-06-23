package com.example.chatai.data.API

import com.example.chatai.BuildConfig
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface OpenAi {
    @Headers("Authorization: ${BuildConfig.apiAuthenticationOne}")
    @POST("v1/completions")
    suspend fun getCompletion(@Body request: Request): Response<ApiData>
}