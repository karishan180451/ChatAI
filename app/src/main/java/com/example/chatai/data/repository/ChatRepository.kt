package com.example.chatai.data.repository

import com.example.chatai.data.dao.ChatDao
import com.example.chatai.data.model.Chat
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class ChatRepository@Inject constructor(private val chatDao: ChatDao) {

    suspend fun insertChat(chat: Chat){
        chatDao.insertChat(chat)
    }

    suspend fun deleteChat(chat: Chat){
        chatDao.deleteChat(chat)
    }

    fun getResponseByID(id: Int): Flow<Chat>{
        return chatDao.getResponseByID(id)
    }

    fun getChatByTopic(topic: String): Flow<List<Chat>>{
        return chatDao.getChatByTopic(topic)
    }

    fun getChatByDate(chatDate: Long): Flow<List<Chat>>{
        return chatDao.getChatByDate(chatDate)
    }

    suspend fun getLastChat(): Chat{
        return chatDao.getLastChat()
    }

}

