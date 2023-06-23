package com.example.chatai.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatai.databinding.ItemLanguageBinding
import com.example.chatai.utils.LanguagesModel

class LanguagesAdapter (private  val mycallback:()->Unit): ListAdapter<LanguagesModel, LanguagesAdapter.ViewHolder>(DiffCallback()) {

    public var selectedPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemLanguageBinding.inflate(
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

    inner class ViewHolder(private val binding: ItemLanguageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: LanguagesModel?, mycallback: () -> Unit) {
            binding.btnRadio.setOnClickListener {
                selectedPosition = adapterPosition
                notifyDataSetChanged()
                mycallback()
            }
            if (currentItem != null) {
                binding.imageView2.setImageDrawable(currentItem.drawable)
                binding.btnRadio.text = currentItem.name
                binding.btnRadio.isChecked = (adapterPosition == selectedPosition)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<LanguagesModel>() {
        override fun areItemsTheSame(oldItem: LanguagesModel, newItem: LanguagesModel) =
            oldItem.code == newItem.code

        override fun areContentsTheSame(oldItem: LanguagesModel, newItem: LanguagesModel) =
            oldItem == newItem
    }
}