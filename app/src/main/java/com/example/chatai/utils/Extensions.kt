package com.example.chatai.utils

import android.content.Context
import android.util.Log
import android.view.View
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale


object Extensions
{

    object LocaleHelper {

        fun setLocale(context: Context, language: String) {
            val configuration = context.resources.configuration
            val locale = Locale(language)
            Locale.setDefault(locale)
            configuration.setLocale(locale)
            context.createConfigurationContext(configuration)
            updateResources(context, language)
        }

        @Suppress("DEPRECATION")
        private fun updateResources(context: Context, language: String) {
            val resources = context.resources
            val configuration = resources.configuration
            val locale = Locale(language)
            configuration.setLocale(locale)
            resources.updateConfiguration(configuration, resources.displayMetrics)
        }
    }


    fun View.show(){
        visibility  = View.VISIBLE
    }

    fun View.hide(){
        visibility = View.GONE
    }

    fun Any?.debug() = Log.d("funsol", "$this")


    fun getTimeFromLong(timeInMilliseconds: Long): String? {
        var mytime = ""
        val minute = timeInMilliseconds / (1000 * 60) % 60
        var hour = timeInMilliseconds / (1000 * 60 * 60) % 24
        var am_pm = ""
        if (hour < 12){
            am_pm = "AM"
        }
        else{
            am_pm = "PM"
            if (hour > 12){
                hour -= 12
            }
        }
        mytime = String.format("%02d:%02d", hour, minute)
        mytime = "$mytime $am_pm"
        return mytime
    }



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

    fun isYesterday(timeInMillis: Long): Boolean {
        val currentTime = System.currentTimeMillis()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = currentTime

        val currentDay = calendar.get(Calendar.DAY_OF_YEAR)
        val currentYear = calendar.get(Calendar.YEAR)

        calendar.timeInMillis = timeInMillis
        val day = calendar.get(Calendar.DAY_OF_YEAR)
        val year = calendar.get(Calendar.YEAR)

        return currentYear == year && currentDay - day == 1
    }

    fun Long.getDate(): String = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        .format(LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault()))

    fun Long.getDateTime(): String = DateTimeFormatter.ofPattern("dd MMMM yyyy | hh:mm a")
            .format(LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault()))
}
