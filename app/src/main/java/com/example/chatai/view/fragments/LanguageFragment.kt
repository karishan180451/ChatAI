package com.example.chatai.view.fragments

import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.LocaleManagerCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.chatai.R
import com.example.chatai.databinding.FragmentLanguageBinding
import com.example.chatai.utils.languagesList
import com.example.chatai.view.adapters.LanguagesAdapter
import com.example.chatai.viewmodels.MainViewModel
import java.util.Locale


class LanguageFragment : Fragment() {

    private lateinit var binding: FragmentLanguageBinding

    private val mainViewModel: MainViewModel by activityViewModels()

    private var _adapter: LanguagesAdapter? = null
    private val languageAdapter get() = _adapter!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRadio.text = getLanguageName(context?.let { LocaleManagerCompat.getSystemLocales(it).toLanguageTags() })
        binding.imageView2.setImageDrawable(getDrawableFromCode(context?.let { LocaleManagerCompat.getSystemLocales(it).toLanguageTags() }))
//        binding.btnRadio.isChecked = false


        binding.btnBack.setOnClickListener {
            mainViewModel.updateScreenDisplayed("settings")
        }

        binding.btnRadio.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                languageAdapter.selectedPosition = -1
                languageAdapter.notifyDataSetChanged()
            }
        }

        binding.btnNext.setOnClickListener {
            if (languageAdapter.selectedPosition == -1){
                if(binding.btnRadio.isChecked){
                    changeLanguage("en")
                    mainViewModel.updateScreenDisplayed("home")

                }
            }
            else{
                changeLanguage(languageAdapter.currentList[languageAdapter.selectedPosition].code)
                mainViewModel.updateScreenDisplayed("home")
            }
        }

        _adapter = LanguagesAdapter(::mycallback)
        binding.recyclerViewLang.adapter = languageAdapter
        languageAdapter.submitList(context?.let { languagesList(it) })
    }

    private fun mycallback() {
        binding.btnRadio.isChecked = false
    }

    private fun changeLanguage(string: String){
        val desiredLocale = Locale(string)
        val config: Configuration = resources.configuration
        config.setLocale(desiredLocale)
        activity?.let {
            it.baseContext?.resources?.updateConfiguration(config, it.baseContext.resources.displayMetrics)
        }
    }

    private fun getDrawableFromCode(code: String?): Drawable? {
        var ret : Drawable? = null
        var flag = false
        for (i in context?.let { languagesList(it) }!!){
            if (i.code == code){
                ret = i.drawable
                flag = true
                break
            }
        }
        if (!flag)
        {
            return ContextCompat.getDrawable(requireContext(), R.drawable.flag_english)
        }
        return ret
    }

    private fun getLanguageName(code: String?): String {
        var ret = ""
        var flag = false
        for (i in context?.let { languagesList(it) }!!){
            if(i.code == code)
            {
                ret = i.name
                flag = true
                break
            }
        }
        if (!flag)
        {
            return "English"
        }
        return ret
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLanguageBinding.inflate(inflater, container, false)
        return binding.root
    }

}