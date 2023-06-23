package com.example.chatai

import android.app.Application
import com.example.chatai.data.API.OpenAi
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class ChatAI : Application(){
    companion object {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://api.openai.com/")
                .client(
                    OkHttpClient.Builder()
                        .callTimeout(100, TimeUnit.SECONDS)
                        .readTimeout(100, TimeUnit.SECONDS)
                        .build()
                )

                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api: OpenAi by lazy {
            retrofit.create(OpenAi::class.java)
        }
    }
}








