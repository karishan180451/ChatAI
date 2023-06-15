package com.example.chatai.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.chatai.data.model.Message
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface MessageDao {

    @Insert
    suspend fun insertMessage(message: Message)

    @Delete
    suspend fun deleteMessage(message: Message)

    @Query("select * From Message WHERE messageId == :id")
    fun getMessageByID(id: Int): Flow<Message>

    @Query("Select * From Message WHERE date == :msgDate")
    fun getMessageByDate(msgDate: Long): Flow<List<Message>>

    @Query("Select * from MESSAGE WHERE chatId == :id")
    fun getMessageByChatID(id: Int): Flow<List<Message>>
}