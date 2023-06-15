package com.example.chatai.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.example.chatai.R
import com.example.chatai.data.model.ExploreData
import com.example.chatai.data.model.ExploreOptions
import com.example.chatai.databinding.FragmentHistoryBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding

    private val chipItemsName: List<String> = listOf("ALL", "Social Media", "Coding", "Health", "Interview", "History", "Writing")
    private val chipItems : List<ExploreOptions> = listOf(
        ExploreData.Social,
        ExploreData.coding,
        ExploreData.health,
        ExploreData.interview,
        ExploreData.history,
        ExploreData.writing
    )

    private var allChidId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        populateChips()


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

    private fun setToTransparent(group: ChipGroup, position: Int) {
        group.children.forEachIndexed { index, view ->
            if (view.id == position)
                (view as Chip).chipBackgroundColor = ContextCompat.getColorStateList(requireContext(), R.color.Primary)
            else         (view as Chip).chipBackgroundColor = ContextCompat.getColorStateList(requireContext(),android.R.color.transparent)
        }
    }
}