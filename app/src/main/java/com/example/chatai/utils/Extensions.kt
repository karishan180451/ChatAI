package com.example.chatai.utils

import android.R
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.RecognizerIntent
import android.util.Log
import android.view.View
import java.util.*


object Extensions
{
    fun View.show(){
        visibility  = View.VISIBLE
    }

    fun View.hide(){
        visibility = View.GONE
    }

    fun Any?.debug() = Log.d("funsol", "$this")

//    fun promptSpeechInput(activity: Activity, requestCode: Int, parentView: View?, promtMsg: String?) {
//        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
//        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, promtMsg)
//        try {
//            activity.startActivityForResult(intent, requestCode)
//        } catch (a: ActivityNotFoundException) {
//            //activity not found error handling
////            SnackBarHandler.show(parentView, activity.getString(R.string.speech_not_supported))
//        }
//    }

}