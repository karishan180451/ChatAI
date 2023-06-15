package com.example.chatai.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel  @Inject constructor() : ViewModel(){
    var selectedChipText: MutableStateFlow<String?> = MutableStateFlow(null)
        private set

    var optionTitle: MutableStateFlow<String?> = MutableStateFlow(null)
        private set

    var optionQuestion: MutableStateFlow<String?> = MutableStateFlow(null)
        private set


    fun updateSelectedChipText(text: String?){
        viewModelScope.launch {
            selectedChipText.value = text
        }
    }

    fun updateOptionQuestion(string: String?){
        viewModelScope.launch {
            optionQuestion.value = string
        }
    }

    fun updateOptionTitle(string: String?){
        viewModelScope.launch {
            optionTitle.value = string
        }
    }





}