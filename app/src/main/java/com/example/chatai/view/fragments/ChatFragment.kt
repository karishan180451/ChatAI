package com.example.chatai.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.chatai.R
import com.example.chatai.databinding.FragmentChatBinding
import com.example.chatai.utils.Extensions.debug
import com.example.chatai.view.adapters.MessageListAdapter
import com.example.chatai.viewmodels.ChatViewModel
import com.example.chatai.viewmodels.ExploreViewModel
import com.example.chatai.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class ChatFragment @Inject constructor() : Fragment() {

    private val exploreViewModel: ExploreViewModel by activityViewModels()

    private val viewModel: ChatViewModel by activityViewModels()

    private val mainViewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private var _adapter: MessageListAdapter? = null
    private val msgAdapter get() = _adapter!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _adapter = MessageListAdapter()
        binding.recyclerGchat.adapter = msgAdapter
        val chat_ai = resources.getString(R.string.chat_ai)
        if(fromHomeScreen == false){
            binding.chatAi.text = "${exploreViewModel.optionTitle.value} $chat_ai"
        }
        else if (fromHomeScreen == true){
            binding.chatAi.text = chat_ai
        }


        binding.backArrow.setOnClickListener {
            if (fromHomeScreen == true){
                viewModel.clearCurrentChat()
//                exploreViewModel.updateOptionQuestion(null)
//                exploreViewModel.updateOptionTitle(null)
                viewModel.updateTopic(null)
                mainViewModel.updateScreenDisplayed("home")
            }
            else if(fromHomeScreen == false){
                viewModel.updateTopic(null)
                viewModel.clearCurrentChat()
                exploreViewModel.updateOptionQuestion(null)
                mainViewModel.updateScreenDisplayed("explore inbox")
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.RESUMED){

            launch {
                viewModel.currentChat.collect {
                    if (it != null){
                        msgAdapter.submitList(it)
                        binding.recyclerGchat.scrollToPosition(it.size - 1)
                    }
                }
            }
            }

        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object{
        var fromHomeScreen: Boolean? = null
    }

}