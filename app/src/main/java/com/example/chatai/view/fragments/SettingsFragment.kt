package com.example.chatai.view.fragments

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.chatai.R
import com.example.chatai.databinding.DialogRateUsBinding
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
//            findNavController().navigate(R.id.action_settingsFragment_to_mainFragment)
        }

        binding.historyNext.setOnClickListener {
//            findNavController().navigate(R.id.action_settingsFragment_to_historyFragment)
            mainViewModel.updateScreenDisplayed("history")
        }

        binding.feedbackOptNext.setOnClickListener {
            mainViewModel.updateScreenDisplayed("feedback")
        }

        binding.rateUsNext.setOnClickListener {
            showRateUsDialog()
        }

        binding.appLanguageNext.setOnClickListener {
            mainViewModel.updateScreenDisplayed("languages")
        }

    }

    private fun showRateUsDialog() {
        val dialogBinding = DialogRateUsBinding.inflate(layoutInflater)
        val themDialog = AlertDialog.Builder(requireContext()).create()
        themDialog.setView(dialogBinding.root)

        dialogBinding.ivClose.setOnClickListener {
            themDialog.dismiss()
        }
        dialogBinding.rbRateUs.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _: RatingBar?, rating: Float, fromUser: Boolean ->

            if (fromUser) {
                if (rating >= 3.5) {
                    try {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${requireContext().packageName}")))
                        themDialog.dismiss()
                    } catch (e: Exception) {
                        //TODO("Update Link")
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=${requireContext().packageName}")))
                        themDialog.dismiss()
                    }
                } else {
                    themDialog.dismiss()
//                    findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToFeedbackFragment())
                }
            }
        }



        themDialog.show()
    }

}