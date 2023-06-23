package com.example.chatai.data.API

data class Request(
    val prompt: String,
    val model: String = "text-davinci-003",
    val max_tokens: Int = 200
)