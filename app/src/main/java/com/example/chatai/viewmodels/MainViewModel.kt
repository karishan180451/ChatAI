package com.example.chatai.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    var screenDisplayed:MutableStateFlow<String?> = MutableStateFlow(null)
        private set


    fun updateScreenDisplayed(string: String){
        viewModelScope.launch {
            screenDisplayed.value = string
        }
    }


}