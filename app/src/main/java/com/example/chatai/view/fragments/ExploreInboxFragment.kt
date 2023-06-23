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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.chatai.R
import com.example.chatai.data.model.ExploreInboxOptions
import com.example.chatai.databinding.FragmentExploreInboxBinding
import com.example.chatai.utils.Extensions.debug
import com.example.chatai.view.adapters.ExploreInboxAdapter
import com.example.chatai.viewmodels.ChatViewModel
import com.example.chatai.viewmodels.ExploreViewModel
import com.example.chatai.viewmodels.MainViewModel
import kotlinx.coroutines.launch


class ExploreInboxFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    private val chatViewModel: ChatViewModel by activityViewModels()


    private val exploreViewModel: ExploreViewModel by activityViewModels()

    private var _binding: FragmentExploreInboxBinding? = null
    private val binding get() = _binding!!

    private var _exploreInboxAdapter: ExploreInboxAdapter? = null
    private val exploreInboxAdapter get() = _exploreInboxAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreInboxBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backArrow.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_exploreFragment)
        }

        exploreViewModel.optionTitle.value?.let { manageExploreInboxRecyclerView(it) }

    }

    private fun manageExploreInboxRecyclerView(option: String){
        var list: List<String?>? = null
        for(i in ExploreInboxOptions.OptionList){
            if (i.title == option){
                val temp = binding.chatAi.text
                binding.chatAi.text = "$option $temp"
               list = i.InboxOptionList
            }
        }
        val layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerviewQuestions.layoutManager = layoutManager
        _exploreInboxAdapter = ExploreInboxAdapter{
            chatViewModel.updateFromScreen("explore inbox")
            exploreViewModel.updateOptionQuestion(it)
        }
        binding.recyclerviewQuestions.adapter = exploreInboxAdapter
        exploreInboxAdapter?.submitList(list)
    }
}