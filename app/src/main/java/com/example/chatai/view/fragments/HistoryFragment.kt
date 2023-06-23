package com.example.chatai.view.fragments

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Message
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatai.R
import com.example.chatai.data.model.History
import com.example.chatai.databinding.DialogDeleteHistoryBinding
import com.example.chatai.databinding.FragmentHistoryBinding
import com.example.chatai.utils.Extensions.debug
import com.example.chatai.view.adapters.HistoryAdapter
import com.example.chatai.viewmodels.ChatViewModel
import com.example.chatai.viewmodels.HistoryViewModel
import com.example.chatai.viewmodels.MainViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HistoryFragment : Fragment() {

    lateinit var dialog: AlertDialog

    private lateinit var binding: FragmentHistoryBinding

    private val chatViewModel: ChatViewModel by activityViewModels()

    private val mainViewModel: MainViewModel by activityViewModels()

    private val historyViewModel: HistoryViewModel by activityViewModels()

    private var _adapter: HistoryAdapter? = null
    private val history_adapter get() = _adapter!!


    private val chipItemsName: List<String> = listOf("ALL", "Social Media", "Coding", "Health", "Interview", "History", "Writing")

    private var allChipId: Int? = null
    private var checkChip: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backArrow.setOnClickListener {
//            findNavController().navigate(R.id.action_historyFragment_to_settingsFragment)
            mainViewModel.updateScreenDisplayed("settings")
            historyViewModel.updateSelectedChipText(null)
        }


        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedId ->
            if (checkedId.size > 0)
            {
                checkChip = checkedId.first()
                val text = view.findViewById<Chip>(checkedId.first())?.text
                historyViewModel.updateSelectedChipText(text.toString())
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {

                    historyViewModel.selectedChipText.collect {
                        if (it != "ALL" && it != null) {
                            checkChip?.let { it1 -> setToTransparent(binding.chipGroup, it1) }
                        } else{
                            for (i in 0 until binding.chipGroup.childCount){
                                val childView = binding.chipGroup.getChildAt(i)
                                if(childView is Chip && childView.text == it){
                                    setToTransparent(binding.chipGroup, childView.id)
                                }
                            }
                        }
                        historyViewModel.retriveHistory(it)
                    }
                }

                launch {
                    historyViewModel.shownHistory.collect{
                            showHistory(it)
                    }
                }

                launch {
                    historyViewModel.MessagesofId.collect{
                        if (it != null && it != emptyList<Message>()){
                            it.first().let { it1 -> chatViewModel.updateChatSession(it1.chatId) }
                            chatViewModel.currentChat.value = it
                            it.debug()
                            chatViewModel.updateFromScreen("history")
                            mainViewModel.updateScreenDisplayed("chat")
                        }
                    }
                }

            }
        }

        populateChips()

        binding.delete.setOnClickListener {
            deleteDialog()
        }

    }


    private fun showHistory(list: List<History?>) {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.historyRecyclerview.layoutManager = layoutManager
        _adapter = HistoryAdapter(::mycallback)
        binding.historyRecyclerview.adapter = history_adapter
        history_adapter.submitList(list)
    }

    private fun mycallback(id: Int?){
        // load chat with chat id = :id
        if (id != null) {
            historyViewModel.getMessagesOfChatId(id)
//            chatViewModel.updateChatSession(id)
        }
    }

    private fun populateChips() {
        var i = 0
        for(item in chipItemsName){
            val chip = Chip(requireContext())
            chip.text = item
            chip.setBackgroundColor(resources.getColor(R.color.Background))
            chip.isClickable = true
            chip.isCheckable = true
            chip.chipCornerRadius = 70f
            chip.setTextColor( ContextCompat.getColorStateList(requireContext(), R.color.white))
            chip.chipStrokeWidth = 4F
            binding.chipGroup.addView(chip)
            if (i == 0){
                allChipId = chip.id
            }
            i++
        }
        allChipId?.let { binding.chipGroup.check(it) }
    }

    private fun setToTransparent(group: ChipGroup, position: Int) {
        group.children.forEachIndexed { index, view ->
            if (view.id == position)
                (view as Chip).chipBackgroundColor = ContextCompat.getColorStateList(requireContext(), R.color.Primary)
            else         (view as Chip).chipBackgroundColor = ContextCompat.getColorStateList(requireContext(),android.R.color.transparent)
        }
    }

    private fun deleteDialog() {
        val binding = DialogDeleteHistoryBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireContext()).setView(binding.root)
        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog.window!!.setGravity(Gravity.CENTER)
        binding.btnCancel.setOnClickListener{
            dialog.dismiss()
        }
        binding.btnDelete.setOnClickListener{
            historyViewModel.clearHistory()
            dialog.dismiss()
        }
        dialog.show()

    }
}