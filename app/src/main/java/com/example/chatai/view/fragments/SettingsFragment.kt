package com.example.chatai.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.chatai.R
import com.example.chatai.databinding.FragmentSettingsBinding
import com.example.chatai.viewmodels.MainViewModel


class SettingsFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        activity?.let {
            val callback: OnBackPressedCallback =
                object : OnBackPressedCallback(true){
                    override fun handleOnBackPressed(){
                        mainViewModel.updateScreenDisplayed("home")
                        findNavController().navigate(R.id.action_settingsFragment_to_mainFragment)
                    }
                }
            it.onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backArrow.setOnClickListener {
            mainViewModel.updateScreenDisplayed("home")
            findNavController().navigate(R.id.action_settingsFragment_to_mainFragment)
        }

    }

}