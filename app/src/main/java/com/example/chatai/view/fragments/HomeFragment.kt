package com.example.chatai.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import coil.load
import coil.transform.CircleCropTransformation
import com.example.chatai.R
import com.example.chatai.databinding.FragmentHomeBinding
import com.example.chatai.view.adapters.HomeViewPagerAdapter
import com.example.chatai.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment @Inject constructor() : Fragment() {

    lateinit var binding: FragmentHomeBinding

    lateinit var mViewPager: ViewPager

    private val mainViewModel: MainViewModel by activityViewModels()

    private var mViewPagerAdapter: HomeViewPagerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ellipse128.load(R.drawable.home_bot){
            transformations(CircleCropTransformation())
        }

        mViewPager = binding.homeImage
        activity?.let {
            mViewPagerAdapter = HomeViewPagerAdapter(it)
        }
        mViewPager.adapter = mViewPagerAdapter


        val startItem = Int.MAX_VALUE / 2
        mViewPager.currentItem = startItem


        binding.explore.setOnClickListener {
            findNavController().navigate(R.id.exploreFragment)
        }

        binding.nextImgBtn.setOnClickListener {
            changeToNextImage()
        }
        binding.prevImgBtn.setOnClickListener {
            changeToPreviousImage()
        }

        binding.settings.setOnClickListener {
//            findNavController().navigate(R.id.settingsFragment)
            mainViewModel.updateScreenDisplayed("settings")
        }

        MainFragment.robotSelected = binding.robotName.text.toString()

    }

    private fun changeToPreviousImage() {
        var currentIndex = mViewPager.currentItem - 1
        if(currentIndex < 0){
            currentIndex = mViewPager.size
            }
        mViewPager.currentItem = currentIndex

    }

    private fun changeToNextImage() {
        mViewPager.currentItem = mViewPager.currentItem + 1
    }



}