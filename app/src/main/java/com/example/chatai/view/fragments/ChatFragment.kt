package com.example.chatai.view.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.chatai.R
import com.example.chatai.databinding.FragmentChatBinding
import com.example.chatai.view.adapters.MessageListAdapter
import com.example.chatai.viewmodels.ChatViewModel
import com.example.chatai.viewmodels.ExploreViewModel
import com.example.chatai.viewmodels.HistoryViewModel
import com.example.chatai.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class ChatFragment @Inject constructor() : Fragment() {

    private val exploreViewModel: ExploreViewModel by activityViewModels()

    private val historyViewModel: HistoryViewModel by activityViewModels()

    private val viewModel: ChatViewModel by activityViewModels()

    private val mainViewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private var _adapter: MessageListAdapter? = null
    private val msgAdapter get() = _adapter!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _adapter = MessageListAdapter(::callback, ::callbackShare)
        binding.recyclerGchat.adapter = msgAdapter
        val chat_ai = resources.getString(R.string.chat_ai)
        if(fromScreen == "explore inbox" ){
            binding.chatAi.text = "${exploreViewModel.optionTitle.value} $chat_ai"
        }
        else if (fromScreen == "home"){
            binding.chatAi.text = chat_ai
        }
        else if (fromScreen == "history"){
            binding.chatAi.text = chat_ai
        }


        binding.backArrow.setOnClickListener {
            if (fromScreen == "home"){
                viewModel.clearCurrentChat()
                viewModel.updateTopic(null)
                mainViewModel.updateScreenDisplayed("home")
            }
            else if(fromScreen == "history"){
                viewModel.clearCurrentChat()
                viewModel.updateTopic(null)
                historyViewModel.backtoHistory()
                mainViewModel.updateScreenDisplayed("history")
            }
            else{
                viewModel.updateTopic(null)
                viewModel.clearCurrentChat()
                exploreViewModel.updateOptionQuestion(null)
                mainViewModel.updateScreenDisplayed("explore inbox")
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.RESUMED) {

                launch {
                    viewModel.currentChat.collect {
                        if (it != null) {
                            msgAdapter.submitList(it)
                            binding.recyclerGchat.scrollToPosition(it.size - 1)
                        }
                    }
                }
            }
        }

    }

    private fun callback(text: String?){
        val clipboard = context?.let { ContextCompat.getSystemService(it,ClipboardManager::class.java) }
        clipboard?.setPrimaryClip(ClipData.newPlainText("",text))
    }

    private fun callbackShare(text: String?){
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        val responseText = text
        shareIntent.putExtra(Intent.EXTRA_TEXT, responseText)
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Chat AI Response")
        val chooserIntent = Intent.createChooser(shareIntent, "Share Chat AI Response")
        context?.startActivity(chooserIntent)
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
        var fromScreen : String? = null
    }

}