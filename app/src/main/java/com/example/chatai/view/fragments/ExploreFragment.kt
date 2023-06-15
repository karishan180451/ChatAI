package com.example.chatai.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chatai.R
import com.example.chatai.data.model.ExploreData
import com.example.chatai.data.model.ExploreOptions
import com.example.chatai.databinding.FragmentExploreBinding
import com.example.chatai.view.adapters.ExploreParentAdapter
import com.example.chatai.view.adapters.ExploreSingleOptionAdapter
import com.example.chatai.viewmodels.ExploreViewModel
import com.example.chatai.viewmodels.MainViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ExploreFragment @Inject constructor() : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    private val exploreViewModel: ExploreViewModel by activityViewModels()

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    private var _adapter: ExploreSingleOptionAdapter? = null
    private val single_option_adapter get() = _adapter!!

    private var _parentAdapter: ExploreParentAdapter? = null
    private val parentAdapter get() = _parentAdapter!!

    private var allChidId: Int? = null

    private val chipItemsName: List<String> = listOf("ALL", "Social Media", "Coding", "Health", "Interview", "History", "Writing")
    private val chipItems : List<ExploreOptions> = listOf(
        ExploreData.Social,
        ExploreData.coding,
        ExploreData.health,
        ExploreData.interview,
        ExploreData.history,
        ExploreData.writing
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        activity?.let {
            val callback: OnBackPressedCallback =
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        exploreViewModel.updateOptionTitle(null)
                        exploreViewModel.updateOptionQuestion(null)
                        exploreViewModel.updateSelectedChipText(null)
                        mainViewModel.updateScreenDisplayed("home")
                        findNavController().navigate(R.id.action_exploreFragment_to_mainFragment)
                    }
                }
            it.onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        }

        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backArrow.setOnClickListener {
            exploreViewModel.updateOptionTitle(null)
            exploreViewModel.updateOptionQuestion(null)
            exploreViewModel.updateSelectedChipText(null)
            mainViewModel.updateScreenDisplayed("home")
            findNavController().navigate(R.id.action_exploreFragment_to_mainFragment)
        }

        populateChips()
        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedId ->
            if (checkedId.size > 0)
            {
                binding.singleOptionAdapter.visibility = View.GONE
                val text = view.findViewById<Chip>(checkedId.first())?.text
                exploreViewModel.updateSelectedChipText(text.toString())
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {
                    exploreViewModel.selectedChipText.collect{
                        if (it != "ALL" && it != null) {
                            binding.parentRecyclerview.visibility = View.GONE
                            binding.singleOptionAdapter.visibility = View.VISIBLE
                            manageSingleOptionRecyclerView(it)
                        }
                        else{
                            binding.parentRecyclerview.visibility = View.VISIBLE
                            binding.singleOptionAdapter.visibility = View.GONE
                            allChidId?.let { it1 -> binding.chipGroup.check(it1) }
                            allChidId?.let { it1 -> setToTransparent(binding.chipGroup, it1) }
                            manageALLRecyclerView()
                        }
                    }
                }
            }
        }


    }

    private fun populateChips() {
        var i = 0
        for (item in chipItemsName){
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
                allChidId = chip.id
            }
            i++
        }
    }


    private fun manageALLRecyclerView() {
        _parentAdapter = ExploreParentAdapter(chipItems,
            vmCall = {
            exploreViewModel.updateOptionTitle(it)
            findNavController().navigate(R.id.action_exploreFragment_to_mainFragment)
        },
            Callback = {
            for (i in 0 until  binding.chipGroup.childCount) {
                val childView = binding.chipGroup.getChildAt(i)
                if (childView is Chip) {
                    if(childView.text == it){
                        binding.chipGroup.check(childView.id)
                    }
                }
            }
        })
        binding.parentRecyclerview.adapter = parentAdapter
    }


    private fun manageSingleOptionRecyclerView(text: CharSequence?) {

        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.singleOptionAdapter.layoutManager = layoutManager
        _adapter = ExploreSingleOptionAdapter(::mycallback)
        binding.singleOptionAdapter.adapter = single_option_adapter

        for (i in 0 until binding.chipGroup.childCount){
            val childView = binding.chipGroup.getChildAt(i)
            if(childView is Chip && childView.text == text){
                setToTransparent(binding.chipGroup, childView.id)
            }
        }

        for(i in chipItems){
          if(i.title == text) {
              single_option_adapter.submitList(i.optionDetails)
          }
        }
    }



    fun mycallback(title: String){
        exploreViewModel.updateOptionTitle(title)
        findNavController().navigate(R.id.action_exploreFragment_to_mainFragment)
    }

    private fun setToTransparent(group: ChipGroup, position: Int) {
        group.children.forEachIndexed { index, view ->
            if (view.id == position)
                (view as Chip).chipBackgroundColor = ContextCompat.getColorStateList(requireContext(), R.color.Primary)
            else         (view as Chip).chipBackgroundColor = ContextCompat.getColorStateList(requireContext(),android.R.color.transparent)
        }
    }
}


