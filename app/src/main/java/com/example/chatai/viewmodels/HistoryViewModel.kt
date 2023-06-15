package com.example.chatai.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatai.data.model.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor() : ViewModel(){

    var selectedChipText: MutableStateFlow<String?> = MutableStateFlow(null)
        private set

    var shownHistory: MutableStateFlow<List<Message>?> = MutableStateFlow(emptyList())
        private set

    fun updateSelectedChipText(text: String?){
        viewModelScope.launch {
            selectedChipText.value = text
        }
    }

    fun clearHistory(){
        viewModelScope.launch {
            shownHistory.value = emptyList()
        }
    }

    fun retriveHistory(topic: String){
        TODO("retrieve history from database according to topic")
    }

}
