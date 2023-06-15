package com.example.chatai.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatai.data.model.ExploreOptions
import com.example.chatai.databinding.ExploreChildLayoutBinding


class ExploreParentAdapter(private val list: List<ExploreOptions>,
                           private val Callback: (String) -> Unit,
                            private val vmCall: (String) -> Unit) : RecyclerView.Adapter<ExploreParentAdapter.MyViewHolder>() {



    class MyViewHolder(private val binding: ExploreChildLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

        fun bind(exploreOptions: ExploreOptions, position: Int, Callback: (String) -> Unit, vmCall: (String) -> Unit) {

            val childadapter = InnerChildAdapter(exploreOptions.optionDetails, vmCall = vmCall)
            binding.childRecyclerView.adapter = childadapter
            binding.include.optionName.text = exploreOptions.title

            binding.include.seeAllBtn.setOnClickListener {
                if (position != RecyclerView.NO_POSITION) {
                    Callback(exploreOptions.title)
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ExploreChildLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = 6

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      holder.bind(list[position], position, Callback, vmCall)
    }
}
