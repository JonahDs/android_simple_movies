package com.example.simplemovies.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplemovies.databinding.ItemCrewExtendedBinding
import com.example.simplemovies.domain.CrewMember

/**
 * Crew extended adapter
 * */
class CrewExtendedAdapter : ListAdapter<CrewMember, CrewExtendedAdapter.CrewViewHolder>(
    DiffCallback
) {
    class CrewViewHolder(private val binding: ItemCrewExtendedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(crewMember: CrewMember) {
            binding.crewmember = crewMember
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CrewMember>() {
        override fun areItemsTheSame(oldItem: CrewMember, newItem: CrewMember): Boolean {
            return oldItem.credit_id == newItem.credit_id
        }

        override fun areContentsTheSame(oldItem: CrewMember, newItem: CrewMember): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewViewHolder {
        return CrewViewHolder(
            ItemCrewExtendedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CrewViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
