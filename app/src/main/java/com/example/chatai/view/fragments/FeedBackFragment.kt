package com.example.chatai.view.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.chatai.R
import com.example.chatai.databinding.FragmentFeedBackBinding
import com.example.chatai.viewmodels.MainViewModel
import java.text.SimpleDateFormat
import java.util.Calendar


class FeedBackFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentFeedBackBinding
    private var crashBool = false
    private var aNRBool = false
    private var adsBool = false
    private var funDisableBool = false
    private var pNWBool = false
    private var dHowToUseBool = false
    private var otherBool = false
    private var totalItemSelected = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedBackBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initEvents()
    }

    private fun initEvents() {

        binding.imgBackArrow.setOnClickListener {
            mainViewModel.updateScreenDisplayed("settings")
        }


        binding.txtCrash.setOnClickListener {


            if (crashBool) {
                binding.txtCrash.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.feedback_inactive_background)
                crashBool = false
                totalItemSelected--
            } else {
                crashBool = true
                totalItemSelected++
                binding.txtCrash.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.feedback_active_background)

            }
        }
        binding.txtANR.setOnClickListener {
            if (aNRBool) {
                binding.txtANR.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.feedback_inactive_background)
                aNRBool = false
                totalItemSelected--
            } else {
                aNRBool = true
                totalItemSelected++
                binding.txtANR.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.feedback_active_background)

            }
        }
        binding.txtAds.setOnClickListener {
            if (adsBool) {
                binding.txtAds.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.feedback_inactive_background)
                adsBool = false
                totalItemSelected--
            } else {
                totalItemSelected++
                adsBool = true
                binding.txtAds.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.feedback_active_background)

            }
        }
        binding.txtfunDisable.setOnClickListener {
            if (funDisableBool) {
                binding.txtfunDisable.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.feedback_inactive_background)
                funDisableBool = false
                totalItemSelected--
            } else {
                funDisableBool = true
                totalItemSelected++
                binding.txtfunDisable.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.feedback_active_background)

            }
        }
        binding.txtPNW.setOnClickListener {
            if (pNWBool) {
                binding.txtPNW.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.feedback_inactive_background)
                pNWBool = false
                totalItemSelected--
            } else {
                totalItemSelected++
                pNWBool = true
                binding.txtPNW.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.feedback_active_background)

            }
        }
        binding.txtHowToUse.setOnClickListener {
            if (dHowToUseBool) {
                binding.txtHowToUse.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.feedback_inactive_background)
                dHowToUseBool = false
                totalItemSelected--
            } else {
                totalItemSelected++
                dHowToUseBool = true
                binding.txtHowToUse.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.feedback_active_background)

            }
        }
        binding.txtOther.setOnClickListener {
            if (otherBool) {
                binding.txtOther.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.feedback_inactive_background)
                otherBool = false
                totalItemSelected--
            } else {
                otherBool = true
                totalItemSelected++
                binding.txtOther.background = ContextCompat.getDrawable(requireContext(), R.drawable.feedback_active_background)

            }
        }
        binding.btnSubmit.setOnClickListener {
            sendFeedback()
        }

    }

    private fun getStringOfSelectItems(): String {
        var result = ""

        if (crashBool) result += "\n-${binding.txtCrash.text}"
        if (aNRBool) result += "\n-${binding.txtANR.text}"
        if (adsBool) result += "\n-${binding.txtAds.text}"
        if (funDisableBool) result += "\n-${binding.txtfunDisable.text}"
        if (pNWBool) result += "\n-${binding.txtPNW.text}"
        if (dHowToUseBool) result += "\n-${binding.txtHowToUse.text}"
        if (otherBool) result += "\n-${binding.txtOther.text}"

        return result
    }

    @SuppressLint("SimpleDateFormat")
    private fun sendFeedback() {
        if (totalItemSelected > 0 || !binding.edtDetails.text.isNullOrEmpty()) {
            val c: Calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss")
            val strDate: String = sdf.format(c.time)

            var responseText = ""
            responseText += "Device Info:\n\nDevice:${Build.DEVICE}\nModel:${Build.MODEL}\nbrand:${Build.BRAND}\nmanufacturer :${Build.MANUFACTURER}"
            responseText += "\n\nIssues marked by User:\n"

            Log.i("checkList", "sendFeedback: $totalItemSelected")

            responseText += getStringOfSelectItems()


            if (binding.edtDetails.text.isNotEmpty()) {
                responseText += "\n\nUser Text feedback:\n\n" + binding.edtDetails.text.toString().trim()
            }

            responseText += "\n\n $strDate"

            Log.d("result", "sendFeedback: $responseText")

            try {
                val shareIntent = Intent(Intent.ACTION_SEND_MULTIPLE)
                shareIntent.type = "plain/text/images"
                shareIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("appitact.official@gmail.com"))
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Time Wrap")
                shareIntent.putExtra(Intent.EXTRA_TEXT, responseText)

                shareIntent.setPackage("com.google.android.gm")
                startActivity(shareIntent)
            } catch (ex: Exception) {
                print(ex.toString())
            }
        } else {
            if (totalItemSelected <= 0) {
                binding.errorChip.visibility = View.VISIBLE
            }
        }
    }

}