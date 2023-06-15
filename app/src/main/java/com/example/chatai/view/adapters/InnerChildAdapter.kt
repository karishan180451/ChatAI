package com.example.chatai.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatai.data.model.OptionDetails
import com.example.chatai.databinding.CardviewExploreBinding

class InnerChildAdapter(private val currentItemlist: List<OptionDetails?>, private val vmCall: (String) -> Unit ) : RecyclerView.Adapter<InnerChildAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CardviewExploreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = currentItemlist[position]
        holder.bind(currentItem, vmCall)
    }

    inner class ViewHolder(private val binding: CardviewExploreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: OptionDetails?, vmCall: (String) -> Unit) {
            if (currentItem != null) {
                binding.card.setOnClickListener{
                    vmCall(binding.cardTitle.text.toString())
                }
                binding.cardTitle.text  =  binding.root.context.getString(currentItem.title)
                binding.cardDetail.text = binding.root.context.getString(currentItem.details)
            }

        }
    }
}