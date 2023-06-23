package com.example.chatai.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatai.data.model.History
import com.example.chatai.data.model.Message
import com.example.chatai.data.repository.ChatRepository
import com.example.chatai.data.repository.MessageRepository
import com.example.chatai.utils.Extensions.debug
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val messageRepo: MessageRepository,
    private val chatRepo: ChatRepository
) : ViewModel() {

    var MessagesofId: MutableStateFlow<List<Message>?>  = MutableStateFlow(emptyList())
        private set


    var selectedChipText: MutableStateFlow<String?> = MutableStateFlow(null)
        private set

    var shownHistory: MutableStateFlow<List<History?>>  = MutableStateFlow(emptyList())
        private set

    fun updateSelectedChipText(text: String?) {
        viewModelScope.launch {
            selectedChipText.value = text
        }
    }

    fun clearHistory() {
//            // query to delete all chats
        viewModelScope.launch {
            chatRepo.clearAllHistory()
        }
    }

    fun getMessagesOfChatId(id: Int){
        viewModelScope.launch {
            messageRepo.getMessagesOfChatID(id).collect { messageList ->
                if (messageList != null) {
                    updateMessagesofId(messageList)
                }
            }
        }
    }

    private fun updateMessagesofId(messageList: List<Message>) {
        viewModelScope.launch {
            MessagesofId.value = messageList
        }
    }


    fun retriveHistory(topic: String?){
        viewModelScope.launch {
            if(topic == "ALL"){
                topic?.let { messageRepo.getFirstMessageForAll() }?.map{ messageList ->
                    val historyList = messageList.map{ message ->
                        message.message?.let {
                            message.date?.let { it1 ->
                                History(
                                    chatId = message.chatId,
                                    FirstMessageText = it,
                                    chatDate = it1
                                )
                            }
                        }
                    }
                    historyList
                }?.collect {historyList->
                    updateShwonHistory(historyList)
                }
            }else{
                topic?.let { messageRepo.getFirstMessage(it) }?.map{ messageList ->
                    val historyList = messageList.map{ message ->
                        message.message?.let {
                            message.date?.let { it1 ->
                                History(
                                    chatId = message.chatId,
                                    FirstMessageText = it,
                                    chatDate = it1
                                )
                            }
                        }
                    }
                    historyList
                }?.collect {historyList->
                    updateShwonHistory(historyList)
                }
            }
        }

    }

    fun updateShwonHistory(list: List<History?>){
        viewModelScope.launch{
            shownHistory.value = list
        }
    }

    fun backtoHistory() {
        viewModelScope.launch {
            updateMessagesofId(emptyList())
            updateShwonHistory(emptyList())
            updateSelectedChipText("ALL")
        }
    }


}
