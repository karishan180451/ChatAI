package com.example.chatai.utils

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.LocaleManagerCompat
import androidx.core.content.ContextCompat
import com.example.chatai.R


object LanguageSettings {

    fun Context.getSystemDefaultLanguage() = LocaleManagerCompat.getSystemLocales(this)

    private fun userSelectAppLanguage() = AppCompatDelegate.getApplicationLocales()

    fun Context.getSystemSelectedOrEnglishOrUserSelected(): (Context) -> LanguagesModel = {
        val userSelectedLanguage = userSelectAppLanguage()
        val systemDefaultLanguage = getSystemDefaultLanguage().toLanguageTags()

        languagesList(it).find { it1-> it1.code == userSelectedLanguage.toLanguageTags() }
            ?: languagesList(it).find { it1-> it1.code == systemDefaultLanguage }
            ?: languagesList(it).first { it1-> it1.name == "English (UK)" }
    }

}



fun languagesList(context: Context): ArrayList<LanguagesModel> {
    val arrayList = ArrayList<LanguagesModel>()
    arrayList.add(LanguagesModel("English", "en", ContextCompat.getDrawable(context, R.drawable.flag_english)!!))
    arrayList.add(LanguagesModel("Arabic  (عربي)", "ar", ContextCompat.getDrawable(context,R.drawable.flag_saudia)!!))
    arrayList.add(LanguagesModel("Russian  (Русский)", "ru", ContextCompat.getDrawable(context,R.drawable.flag_russia)!!))
    arrayList.add(LanguagesModel("Indonesian  (bahasa Indonesia)", "in", ContextCompat.getDrawable(context,R.drawable.flag_indonesia)!!))
    arrayList.add(LanguagesModel("Bengali  (বাংলা)", "bn", ContextCompat.getDrawable(context,R.drawable.flag_bangli)!!))
    arrayList.add(LanguagesModel("Hindi  (हिंदी)", "hi", ContextCompat.getDrawable(context,R.drawable.flag_india)!!))
    arrayList.add(LanguagesModel("Ukrainian  (українська)", "uk", ContextCompat.getDrawable(context,R.drawable.flag_ukrain)!!))
    arrayList.add(LanguagesModel("Vietnamese  (Tiếng Việt)", "vi", ContextCompat.getDrawable(context,R.drawable.flag_vietnam)!!))
    arrayList.add(LanguagesModel("Korean  (한국인)", "ko", ContextCompat.getDrawable(context,R.drawable.flag_korea)!!))
    arrayList.add(LanguagesModel("Japanese  (日本)", "ja", ContextCompat.getDrawable(context,R.drawable.flag_japan)!!))
    arrayList.add(LanguagesModel("Chinese  (中国人)", "zh", ContextCompat.getDrawable(context,R.drawable.flag_china)!!))
    arrayList.add(LanguagesModel("Swedish  (svenska)", "sv", ContextCompat.getDrawable(context,R.drawable.ic_swedish)!!))
    arrayList.add(LanguagesModel("Polish  (Polski)", "pl", ContextCompat.getDrawable(context,R.drawable.flag_polish)!!))
    arrayList.add(LanguagesModel("Malay  (Melayu)", "ms", ContextCompat.getDrawable(context,R.drawable.flag_malay)!!))
    arrayList.add(LanguagesModel("French  (Français)", "fr", ContextCompat.getDrawable(context,R.drawable.flag_french)!!))
    arrayList.add(LanguagesModel("Italian  (Italiano)", "it", ContextCompat.getDrawable(context,R.drawable.flog_itlay)!!))
    arrayList.add(LanguagesModel("Persian  (فارسی)", "fa", ContextCompat.getDrawable(context,R.drawable.flag_persian)!!))
    arrayList.add(LanguagesModel("Turkish  (Türkçe)", "tr", ContextCompat.getDrawable(context,R.drawable.flag_turkey)!!))
    arrayList.add(LanguagesModel("Thai  (แบบไทย)", "th", ContextCompat.getDrawable(context,R.drawable.flag_thai)!!))
    arrayList.add(LanguagesModel("Portuguese  (Português)", "pt", ContextCompat.getDrawable(context,R.drawable.flag_portgues)!!))
    arrayList.add(LanguagesModel("Spanish  (Español)", "es", ContextCompat.getDrawable(context,R.drawable.flag_spain)!!))
    arrayList.add(LanguagesModel("German  (Deutsch)", "de", ContextCompat.getDrawable(context,R.drawable.flag_german)!!))
    arrayList.add(LanguagesModel("Netherlands Dutch  (Nederlands)", "nl", ContextCompat.getDrawable(context,R.drawable.flag_dutch)!!))
    arrayList.add(LanguagesModel("Tamil  (தமிழ்)", "ta", ContextCompat.getDrawable(context,R.drawable.flag_tamil)!!))
    arrayList.add(LanguagesModel("Czech  (čeština)", "cs", ContextCompat.getDrawable(context,R.drawable.flag_czech)!!))
    arrayList.add(LanguagesModel("Urdu  (اردو)", "ur", ContextCompat.getDrawable(context, R.drawable.flag_pakistan)!!))
    arrayList.sortBy { it.name }
    return arrayList
}