package com.example.chatai.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.chatai.data.model.Message
import com.example.chatai.R
import com.example.chatai.databinding.ItemChatLayoutBinding
import com.example.chatai.utils.Extensions.hide
import com.example.chatai.utils.Extensions.show


class MessageListAdapter(private  val mycallback:(String?)->Unit, private val callbackShare:(String?)->Unit) : ListAdapter<Message, MessageListAdapter.ViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemChatLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)

        holder.bind(currentItem, mycallback, callbackShare)
    }


    inner class ViewHolder(private val binding: ItemChatLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: Message, mycallback: (String?) -> Unit, callbackShare: (String?) -> Unit) {
            if (currentItem.isAI)
            {
                binding.aiTextLayout.show()
                binding.myTextLayout.hide()
                binding.textGchatMessageMe.text = currentItem.message
                binding.ellipse128.load(R.drawable.home_bot){
                    transformations(CircleCropTransformation())
                }

                binding.shareMessage.setOnClickListener {
                    callbackShare(currentItem.message)
                }

                binding.copyMessage.setOnClickListener {
                    mycallback(binding.textGchatMessageMe.text.toString())
                }
            }
            else{
                binding.myMessageText.text = currentItem.message
                binding.senderImage.load(R.drawable.home_bot){
                    transformations(CircleCropTransformation())
                }
               binding.aiTextLayout.hide()
               binding.myTextLayout.show()
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message) =
            oldItem.message == newItem.message

        override fun areContentsTheSame(oldItem: Message, newItem: Message) =
            oldItem == newItem
    }
}