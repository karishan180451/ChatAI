package com.example.chatai.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.chatai.data.model.Chat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Date

@Dao
interface ChatDao {

    @Insert
    suspend fun insertChat(chat: Chat)

    @Delete
    suspend fun deleteChat(chat: Chat)

    @Query("select * From Chat WHERE ChatId = :id")
    fun getResponseByID(id: Int): Flow<Chat>

    @Query("Select * From Chat WHERE date = :chatDate")
    fun getChatByDate(chatDate: Long): Flow<List<Chat>>

    @Query("select * From Chat WHERE topic = :topic")
    suspend fun getChatByTopic(topic: String?): List<Chat>?

    @Query("SELECT * FROM Chat ORDER BY ChatId DESC LIMIT 1")
    suspend fun getLastChat(): Chat

    @Query("DELETE FROM Chat")
    suspend fun deleteAllChats()

//    @Query("Select * From Chat")
//    fun getChat(): Flow<List<Chat>?>
}