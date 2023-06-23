package com.example.chatai.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatai.R
import com.example.chatai.data.model.History
import com.example.chatai.data.model.OptionDetails
import com.example.chatai.databinding.ItemHistoryBinding
import com.example.chatai.utils.Extensions.debug
import com.example.chatai.utils.Extensions.getDate
import com.example.chatai.utils.Extensions.getDateTime
import com.example.chatai.utils.Extensions.getTimeFromLong
import com.example.chatai.utils.Extensions.isYesterday

class HistoryAdapter(private  val mycallback:(Int?)->Unit) : ListAdapter<History, HistoryAdapter.ViewHolder>(DiffCallback()) {
    var index = 0
    var str: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem,mycallback)
    }

    inner class ViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: History?, mycallback: (Int?) -> Unit) {
            if (currentItem != null) {
                if(currentItem.chatDate.getDate() == System.currentTimeMillis().getDate()){
                    binding.header.text = "Today"
                }
                else if (isYesterday(currentItem.chatDate)){
                    binding.header.text = "Yesterday"
                }
                else{
                    binding.header.text = currentItem.chatDate.getDate()
                }
                binding.historyBody.text = currentItem.FirstMessageText
                binding.dataTime.text = currentItem.chatDate.getDateTime()

                binding.card.setOnClickListener {
                    mycallback(currentItem.chatId)
                }
                if (index == 0){
                    str = binding.header.text.toString()
                }
                else{
                    if(binding.header.text.toString() == str){
                        binding.header.visibility = View.GONE
                    }
                    else{
                        binding.header.visibility = View.VISIBLE
                        str = binding.header.text.toString()
                    }
                }
                index += 1
            }
        } }

    class DiffCallback : DiffUtil.ItemCallback<History>() {
        override fun areItemsTheSame(oldItem: History, newItem: History) =
            oldItem.chatId == newItem.chatId

        override fun areContentsTheSame(oldItem: History, newItem: History) =
            oldItem == newItem
    }
}