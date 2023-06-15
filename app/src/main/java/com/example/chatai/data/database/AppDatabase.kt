package com.example.chatai.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chatai.data.dao.ChatDao
import com.example.chatai.data.dao.MessageDao
import com.example.chatai.data.model.Chat
import com.example.chatai.data.model.Message

@Database(
    entities = [Message :: class , Chat :: class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){

    abstract val chatDao: ChatDao
    abstract val messageDao: MessageDao
}