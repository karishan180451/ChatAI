package com.example.chatai.di

import android.content.Context
import androidx.room.Room
import com.example.chatai.data.dao.ChatDao
import com.example.chatai.data.dao.MessageDao
import com.example.chatai.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) :AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "ai_database").build()

    @Provides
    @Singleton
    fun provideMessageDao(appDatabase: AppDatabase): MessageDao = appDatabase.messageDao

    @Provides
    @Singleton
    fun provideResponseDao(appDatabase: AppDatabase): ChatDao = appDatabase.chatDao
}