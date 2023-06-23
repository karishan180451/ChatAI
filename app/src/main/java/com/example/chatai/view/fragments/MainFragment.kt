package com.example.chatai.view.fragments

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.chatai.R
import com.example.chatai.databinding.FragmentMainBinding
import com.example.chatai.viewmodels.ChatViewModel
import com.example.chatai.viewmodels.ExploreViewModel
import com.example.chatai.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject
import kotlin.system.exitProcess


@AndroidEntryPoint
class MainFragment @Inject constructor() : Fragment() {

    private val viewModel: ChatViewModel by activityViewModels()

    private val mainViewModel: MainViewModel by activityViewModels()

    private val exploreViewModel: ExploreViewModel by activityViewModels()

    private lateinit var binding: FragmentMainBinding

    val VOICE_CODE = 100


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        activity?.let {
            val callback: OnBackPressedCallback =
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        if (mainViewModel.screenDisplayed.value == "chat") {
                            if (ChatFragment.fromScreen == "home") {
                                viewModel.clearCurrentChat()
                                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                                imm.hideSoftInputFromWindow(binding.textView.windowToken, 0)
                                binding.textView.setText("")
                                exploreViewModel.updateOptionQuestion(null)
                                exploreViewModel.updateOptionTitle(null)
                                mainViewModel.updateScreenDisplayed("home")
                                viewModel.updateTopic(null)
                            } else if (ChatFragment.fromScreen == "explore inbox") {
                                viewModel.updateTopic(null)
                                viewModel.clearCurrentChat()
                                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                                imm.hideSoftInputFromWindow(binding.textView.windowToken, 0)
                                binding.textView.setText("")
                                exploreViewModel.updateOptionQuestion(null)
                                mainViewModel.updateScreenDisplayed("explore inbox")
                            }
                        } else if (mainViewModel.screenDisplayed.value == "explore inbox") {
                            findNavController().navigate(R.id.action_mainFragment_to_exploreFragment)
                        } else {
                            exitProcess(0)
                        }
                    }
                }
            it.onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.updateScreenDisplayed("home")

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {
                    mainViewModel.screenDisplayed.collect {
                        if (it != null) {
                            when (it) {
                                "home" -> {
                                    fragSelection(it)
                                }
                                "chat" -> {
                                    fragSelection(it)
                                }
                                "explore inbox" -> {
                                    fragSelection(it)
                                }
                                "settings" -> {
                                    fragSelection(it)
                                }

                                "history" -> {
                                    fragSelection(it)
                                }

                                "feedback" -> {
                                    fragSelection(it)
                                }

                                "languages" -> {
                                    fragSelection(it)
                                }
                            }
                        }
                    }
                }

                launch {
                    exploreViewModel.optionQuestion.collect {
                        if (it != null) {
                            exploreViewModel.selectedChipText.value?.let { it1 -> viewModel.updateTopic(it1) }
                            viewModel.updateMessage(it)
                            mainViewModel.updateScreenDisplayed("chat")
                            viewModel.updateFromScreen("explore inbox")
                        }
                    }
                }

                launch {
                    exploreViewModel.optionTitle.collect {
                        if (it != null) {
//                            it.debug()
                            mainViewModel.updateScreenDisplayed("explore inbox")
                        }
                    }
                }

                launch {
                    viewModel.topic.collect {
                        if (viewModel.chatSession.value == null && it != null) {
                            viewModel.createChatSession(it)
                        }
                    }
                }

                launch {
                    viewModel.combinedFlow.collect{
                        val chatID = it.first
                        val message = it.second
                        if (!message.isNullOrEmpty() && chatID != null){
                                viewModel.addMessageToChat(messageBody = message, generateResponse = true, isAI = false, chat_session_value = chatID)
                        }
                    }
                }

                launch {
                    viewModel.fromScreen.collect{
                        ChatFragment.fromScreen = it
                    }
                }

            }
        }

        binding.textView.setOnClickListener {
            if (mainViewModel.screenDisplayed.value == "home") {
                viewModel.updateFromScreen("home")
                mainViewModel.updateScreenDisplayed("chat")
                robotSelected?.let { it1 -> viewModel.updateTopic(it1) }
            }
        }

        binding.mic.setOnClickListener {
            if (mainViewModel.screenDisplayed.value == "home") {
                viewModel.updateFromScreen("home")
                mainViewModel.updateScreenDisplayed("chat")
                robotSelected?.let { it1 -> viewModel.updateTopic(it1) }
                voiceToText()
            }
        }

        binding.sendText.setOnClickListener {
            if (!TextUtils.isEmpty(binding.textView.text.toString())) {
                viewModel.updateMessage(binding.textView.text.toString())
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.textView.windowToken, 0)
                binding.textView.setText("")
            }
        }

    }


    private fun fragSelection(string: String) {
        val fragmentManager = childFragmentManager
        when (string) {
            "chat" -> {
                binding.askQuestionButton.visibility = View.VISIBLE
                fragmentManager.beginTransaction().replace(
                    binding.fragmentContainerView.id,
                    ChatFragment()
                ).commit()
                binding.textView.isFocusable = true
                binding.textView.isFocusableInTouchMode = true
                binding.textView.requestFocus()
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(binding.textView, InputMethodManager.SHOW_IMPLICIT)
            }

            "home" -> {
                binding.askQuestionButton.visibility = View.VISIBLE
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.textView.windowToken, 0)
                fragmentManager.beginTransaction().replace(
                    binding.fragmentContainerView.id,
                    HomeFragment()
                ).commit()
                binding.textView.setText("")
                binding.textView.isFocusable = false
                binding.textView.isFocusableInTouchMode = false
            }

            "explore inbox" -> {
                binding.askQuestionButton.visibility = View.VISIBLE
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.textView.windowToken, 0)
                fragmentManager.beginTransaction().replace(
                    binding.fragmentContainerView.id,
                    ExploreInboxFragment()
                ).commit()
                binding.textView.setText("")
                binding.textView.isFocusable = false
                binding.textView.isFocusableInTouchMode = false
            }

            "settings" -> {
//                binding.askQuestionButton.visibility = View.VISIBLE
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.textView.windowToken, 0)
                fragmentManager.beginTransaction().replace(
                    binding.fragmentContainerView.id,
                    SettingsFragment()
                ).commit()
                binding.askQuestionButton.visibility = View.GONE

            }

            "history" -> {
//                binding.askQuestionButton.visibility = View.VISIBLE
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.textView.windowToken, 0)
                fragmentManager.beginTransaction().replace(
                    binding.fragmentContainerView.id,
                    HistoryFragment()
                ).commit()
                binding.askQuestionButton.visibility = View.GONE
            }

            "feedback" -> {
//                binding.askQuestionButton.visibility = View.VISIBLE
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.textView.windowToken, 0)
                fragmentManager.beginTransaction().replace(
                    binding.fragmentContainerView.id,
                    FeedBackFragment()
                ).commit()
                binding.askQuestionButton.visibility = View.GONE
            }

            "languages" -> {
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.textView.windowToken, 0)
                fragmentManager.beginTransaction().replace(
                    binding.fragmentContainerView.id,
                    LanguageFragment()
                ).commit()
                binding.askQuestionButton.visibility = View.GONE
            }
        }
    }

    private fun voiceToText() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(
            RecognizerIntent.EXTRA_PROMPT,
            "I am listening..."
        )
        try {
            startActivityForResult(intent, VOICE_CODE)
        }
        catch (e: ActivityNotFoundException) {
        TODO("Error handling")
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            VOICE_CODE -> {
                if (resultCode == RESULT_OK && null != data) {
                    val result = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    binding.textView.setText(result?.first())
                }
            }
        }
    }

    companion object {
        var robotSelected: String? = null
    }
}