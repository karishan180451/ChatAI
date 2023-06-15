package com.example.chatai.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class Chat(
    @PrimaryKey(autoGenerate = true) var ChatId: Int = 0,
    var date: Long?,
    var topic: String? = null
) : Parcelable