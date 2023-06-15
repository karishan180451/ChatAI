package com.example.chatai.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.chatai.R
import com.example.chatai.databinding.FragmentSplashBinding
import com.example.chatai.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment @Inject constructor() : Fragment() {
    lateinit var binding: FragmentSplashBinding
    private val mainViewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            binding.getStartedButton.setOnClickListener {
                mainViewModel.updateScreenDisplayed("home")
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            }
    }
}