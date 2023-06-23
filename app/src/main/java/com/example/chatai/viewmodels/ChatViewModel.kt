package com.example.chatai.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatai.ChatAI
import com.example.chatai.data.API.Request
import com.example.chatai.data.model.Chat
import com.example.chatai.data.model.Message
import com.example.chatai.data.repository.ChatRepository
import com.example.chatai.data.repository.MessageRepository
import com.example.chatai.utils.Extensions.debug
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val messageRepo: MessageRepository,
                    private val chatRepo: ChatRepository
): ViewModel(){

    var fromScreen: MutableStateFlow<String?> = MutableStateFlow(null)

    var topic: MutableStateFlow<String?> = MutableStateFlow(null)
        private set

    var currentChat: MutableStateFlow<List<Message>?> = MutableStateFlow(emptyList())
        private set

    var chatSession: MutableStateFlow<Int?> = MutableStateFlow(null)
        private set

    var messageBody : MutableStateFlow<String?> = MutableStateFlow(null)
        private set

    var combinedFlow: Flow<Pair<Int?, String?>> = chatSession.combine(messageBody) { value1, value2 ->
        value1 to value2
    }
        private set


    fun updateChatSession(value: Int){
        chatSession.value = value
    }

    fun updateTopic(it: String?){
        viewModelScope.launch {
            topic.value = it
        }
    }

    fun updateMessage(msgBody: String){
        viewModelScope.launch {
            messageBody.value = msgBody
        }
    }

    fun clearCurrentChat(){
            viewModelScope.launch {
                currentChat.value = emptyList()
                messageBody.value = null
                chatSession.value = null
            }
    }


    fun addMessageToChat(messageBody: String, generateResponse: Boolean, isAI: Boolean, chat_session_value: Int){

        val tempMessage  =  Message(0, chat_session_value, messageBody, System.currentTimeMillis(), isAI)
        "tempMessage = ${tempMessage.message}".debug()
        viewModelScope.launch {
            val currentList = currentChat.value
            val newList = currentList?.toMutableList()?.apply {
                if (tempMessage != null) {
                    add(tempMessage)
                }
            }
            currentChat.value = newList?.toList()
            if (tempMessage != null) {
                addMessageToDataBase(tempMessage)
            }
            if (generateResponse){
                if (tempMessage != null) {
                    createResponse(tempMessage)
                }
            }
        }
    }

    private suspend fun addMessageToDataBase(message: Message){
        viewModelScope.launch {
            messageRepo.insertMessage(message)
        }
    }

    private fun createResponse(message: Message){
        // message parameter to be used in API call
        viewModelScope.launch {
            val response = message.message?.let { Request(it) }?.let { ChatAI.api.getCompletion(it) }
            response.debug()
            if (response != null) {
                (response.body()?.choices?.get(0)?.text ).let { chatSession.value?.let { it1 ->
                    if (it != null) {
                        addMessageToChat(it, false, true, it1)
                    }
                } }
            }
        }


    }

    fun createChatSession(topic: String?) {
        viewModelScope.launch {
                chatRepo.insertChat(Chat(date = System.currentTimeMillis(),topic = topic))
                updateChatSession( chatRepo.getLastChat().ChatId)
        }
    }

    fun updateFromScreen(s: String) {
        viewModelScope.launch {
            fromScreen.value = s
        }
    }

}