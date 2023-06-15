package com.example.chatai.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatai.data.model.Chat
import com.example.chatai.data.model.Message
import com.example.chatai.data.repository.ChatRepository
import com.example.chatai.data.repository.MessageRepository
import com.example.chatai.utils.Extensions.debug
import com.example.chatai.view.fragments.ChatFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val messageRepo: MessageRepository,
                    private val chatRepo: ChatRepository
): ViewModel(){

    var topic: MutableStateFlow<String?> = MutableStateFlow(null)
        private set

    var currentChat: MutableStateFlow<List<Message>?> = MutableStateFlow(emptyList())
        private set

    var chatSession: MutableStateFlow<Int?> = MutableStateFlow(null)
        private set

    var messageBody : MutableStateFlow<String?> = MutableStateFlow(null)
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


    fun addMessageToChat(messageBody: String, generateResponse: Boolean, isAI: Boolean){

        val tempMessage  = chatSession.value?.let { Message(0, it, messageBody, System.currentTimeMillis(), isAI) }
        "tempMessage = ${tempMessage?.message}".debug()
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
        val response = chatSession.value?.let { Message( chatId = it, message =  "Obviously", date =  System.currentTimeMillis(), isAI =  true) } // make an api call instead
        response?.message?.let { addMessageToChat(it, false, true) }
    }

    fun createChatSession(topic: String?) {
        viewModelScope.launch {
                chatRepo.insertChat(Chat(date = System.currentTimeMillis(),topic = topic))
                updateChatSession( chatRepo.getLastChat().ChatId)
        }
    }

}