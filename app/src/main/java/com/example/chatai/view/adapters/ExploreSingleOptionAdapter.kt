package com.example.chatai.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatai.data.model.OptionDetails
import com.example.chatai.databinding.CardviewExploreBinding

class ExploreSingleOptionAdapter(private  val mycallback:(String)->Unit) : ListAdapter<OptionDetails ,ExploreSingleOptionAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CardviewExploreBinding.inflate(
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

    inner class ViewHolder(private val binding: CardviewExploreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: OptionDetails?, mycallback: (String) -> Unit) {
            if (currentItem != null) {
                binding.card.setOnClickListener{
                    mycallback(binding.cardTitle.text.toString())
                }
                binding.cardTitle.text  =  binding.root.context.getString(currentItem.title)
                binding.cardDetail.text = binding.root.context.getString(currentItem.details)
            }

        }
    }

    class DiffCallback : DiffUtil.ItemCallback<OptionDetails>() {
        override fun areItemsTheSame(oldItem: OptionDetails, newItem: OptionDetails) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: OptionDetails, newItem: OptionDetails) =
            oldItem == newItem
    }
}