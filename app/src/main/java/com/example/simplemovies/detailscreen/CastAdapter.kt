package com.example.simplemovies.detailscreen


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplemovies.databinding.ItemCastBinding
import com.example.simplemovies.domain.CastMember

class CastAdapter() : ListAdapter<CastMember, CastAdapter.CastViewHolder>(DiffCallback) {
    class CastViewHolder(private var binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(castMember: CastMember) {
            binding.cast = castMember
            binding.executePendingBindings()
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<CastMember>() {
        override fun areItemsTheSame(oldItem: CastMember, newItem: CastMember): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CastMember, newItem: CastMember): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(
            ItemCastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}