package com.example.chatai.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class Message(
    @PrimaryKey(autoGenerate = true) var messageId: Int = 0,
    var chatId: Int = 0,
    var message: String? = "",
    var date: Long?,
    var isAI: Boolean = false
    ) : Parcelable