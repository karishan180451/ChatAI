package com.example.chatai.data.repository

import com.example.chatai.data.dao.ChatDao
import com.example.chatai.data.dao.MessageDao
import com.example.chatai.data.model.Chat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Date
import javax.inject.Inject

class ChatRepository@Inject constructor(private val chatDao: ChatDao, private val messageDao: MessageDao) {

    suspend fun insertChat(chat: Chat){
        chatDao.insertChat(chat)
    }

    suspend fun deleteChat(chat: Chat){
        chatDao.deleteChat(chat)
    }

    fun getResponseByID(id: Int): Flow<Chat>{
        return chatDao.getResponseByID(id)
    }

//    fun getChat(): Flow<List<Chat>?>{
//        return chatDao.getChat()
//    }

    suspend fun getChatByTopic(topic: String?):  List<Chat>?{
        return chatDao.getChatByTopic(topic)
    }

    fun getChatByDate(chatDate: Long): Flow<List<Chat>?>{
        return chatDao.getChatByDate(chatDate)
    }

    suspend fun getLastChat(): Chat{
        return chatDao.getLastChat()
    }

    suspend fun clearAllHistory() {
        chatDao.deleteAllChats()
        messageDao.deleteAllMessages()
    }

}

