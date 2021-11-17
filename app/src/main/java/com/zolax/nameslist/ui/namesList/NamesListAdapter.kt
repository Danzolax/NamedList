package com.zolax.nameslist.ui.namesList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zolax.nameslist.databinding.ItemNamesListRvBinding

class NamesListAdapter() : RecyclerView.Adapter<NamesListAdapter.NamesListViewHolder>() {
    inner class NamesListViewHolder(private val binding: ItemNamesListRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItemView(item: String) {
            binding.name.text = item
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, differCallback)

    var names: List<String>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NamesListViewHolder(
        ItemNamesListRvBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )


    override fun onBindViewHolder(holder: NamesListViewHolder, position: Int) =
        holder.bindItemView(names[position])


    override fun getItemCount() = names.size
}