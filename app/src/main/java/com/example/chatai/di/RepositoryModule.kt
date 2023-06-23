package com.example.chatai.di

import com.example.chatai.data.dao.ChatDao
import com.example.chatai.data.dao.MessageDao
import com.example.chatai.data.repository.ChatRepository
import com.example.chatai.data.repository.MessageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun getMessageRepository(messageDao: MessageDao)  : MessageRepository = MessageRepository(messageDao)

    @Provides
    @Singleton
    fun getChatRepository(chatDao: ChatDao, messageDao: MessageDao): ChatRepository = ChatRepository(chatDao, messageDao)
}