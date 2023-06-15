package com.example.chatai.view.fragments

import android.content.Context
import android.os.Bundle
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
import com.example.chatai.data.model.Chat
import com.example.chatai.databinding.FragmentMainBinding
import com.example.chatai.utils.Extensions.debug
import com.example.chatai.view.fragments.ChatFragment.Companion.fromHomeScreen
import com.example.chatai.viewmodels.ChatViewModel
import com.example.chatai.viewmodels.ExploreViewModel
import com.example.chatai.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.exitProcess

@AndroidEntryPoint
class MainFragment @Inject constructor() : Fragment() {

    private val viewModel: ChatViewModel by activityViewModels()

    private val mainViewModel: MainViewModel by activityViewModels()

    private val exploreViewModel: ExploreViewModel by activityViewModels()

    private lateinit var binding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        activity?.let {
            val callback: OnBackPressedCallback =
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        if (mainViewModel.screenDisplayed.value == "chat"){
                            if(fromHomeScreen == true){
                                viewModel.clearCurrentChat()
                                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                                imm.hideSoftInputFromWindow(binding.textView.windowToken,0)
                                binding.textView.setText("")
                                exploreViewModel.updateOptionQuestion(null)
                                exploreViewModel.updateOptionTitle(null)
                                mainViewModel.updateScreenDisplayed("home")
                                viewModel.updateTopic(null)
                            }
                            else if (fromHomeScreen == false){
                                viewModel.updateTopic(null)
                                viewModel.clearCurrentChat()
                                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                                imm.hideSoftInputFromWindow(binding.textView.windowToken,0)
                                binding.textView.setText("")
                                exploreViewModel.updateOptionQuestion(null)
                                mainViewModel.updateScreenDisplayed("explore inbox")
                            }
                        }
                        else if(mainViewModel.screenDisplayed.value == "explore inbox"){
                            findNavController().navigate(R.id.action_mainFragment_to_exploreFragment)
                        }
                        else{
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
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                launch {
                    mainViewModel.screenDisplayed.collect{
                        if(it != null){
                            if (it == "home"){
                                fragSelection(it)
                            }
                            else if(it == "chat"){
                                fragSelection(it)
                            }
                            else if (it == "explore inbox"){
                                fragSelection(it)
                            }
                        }
                    }
                }

                launch {
                    exploreViewModel.optionQuestion.collect{
                        if (it != null){
                            exploreViewModel.optionTitle.value?.let { it1 -> viewModel.updateTopic(it1) }
                            mainViewModel.updateScreenDisplayed("chat")
                            fromHomeScreen = false
                            viewModel.updateMessage(it)
                        }
                    }
                }

                launch {
                    exploreViewModel.optionTitle.collect{
                        if (it != null) {
                            it.debug()
                            mainViewModel.updateScreenDisplayed("explore inbox")
                        }
                    }
                }

                launch {
                    viewModel.topic.collect{
                        if (viewModel.chatSession.value == null && it != null){
                            viewModel.createChatSession(it)
                        }
                    }
                }

                launch {
                    viewModel.messageBody.collect{ str ->
                        str?.let {
                            viewModel.addMessageToChat(str, true, false)
                        }
                    }
                }

            }
        }

        binding.textView.setOnClickListener {
            if(mainViewModel.screenDisplayed.value == "home"){
                fromHomeScreen = true
                mainViewModel.updateScreenDisplayed("chat")
                robotSelected?.let { it1 -> viewModel.updateTopic(it1) }
            }
        }

        binding.sendText.setOnClickListener {
            if(!TextUtils.isEmpty(binding.textView.text.toString())){
                viewModel.updateMessage(binding.textView.text.toString())
                val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.textView.windowToken,0)
                binding.textView.setText("")
            }
        }

    }


    private fun fragSelection(string: String) {
        val fragmentManager = childFragmentManager
        if (string == "chat"){
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
        else if (string == "home"){
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.textView.windowToken,0)
            fragmentManager.beginTransaction().replace(
                binding.fragmentContainerView.id,
                HomeFragment()
            ).commit()
            binding.textView.setText("")
            binding.textView.isFocusable = false
            binding.textView.isFocusableInTouchMode = false
        }
        else if (string == "explore inbox"){
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.textView.windowToken,0)
            fragmentManager.beginTransaction().replace(
                binding.fragmentContainerView.id,
                ExploreInboxFragment()
            ).commit()
            binding.textView.setText("")
            binding.textView.isFocusable = false
            binding.textView.isFocusableInTouchMode = false
        }
    }

    companion object{
        var robotSelected: String? = null
    }
}