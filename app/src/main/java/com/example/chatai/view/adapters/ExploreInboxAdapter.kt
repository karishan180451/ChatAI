package com.example.chatai.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatai.databinding.ExploreInboxItemBinding

class ExploreInboxAdapter(private  val mycallback:(String)->Unit) : ListAdapter<String, ExploreInboxAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ExploreInboxItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem, mycallback)
    }

    inner class ViewHolder(private val binding: ExploreInboxItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: String?, mycallback: (String) -> Unit) {

            if (currentItem != null) {
                binding.question.text = currentItem

                binding.card.setOnClickListener {
                    mycallback(binding.question.text.toString())
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) =
            oldItem.length == newItem.length
    }
}