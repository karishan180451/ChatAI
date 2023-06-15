package com.example.chatai.data.repository

import com.example.chatai.data.dao.MessageDao
import com.example.chatai.data.model.Message
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class MessageRepository@Inject constructor(private val messageDao: MessageDao) {
    suspend fun insertMessage(message: Message){
        messageDao.insertMessage(message)
    }
    suspend fun deleteMessage(message: Message){
        messageDao.deleteMessage(message)
    }

    fun getMessageById(id: Int): Flow<Message>{
        return messageDao.getMessageByID(id)
    }

    suspend fun getMessageByDate(date: Long): Flow<List<Message>>{
        return messageDao.getMessageByDate(date)
    }

    fun getMessageByChatID(id: Int): Flow<List<Message>>{
        return messageDao.getMessageByChatID(id)
    }
}