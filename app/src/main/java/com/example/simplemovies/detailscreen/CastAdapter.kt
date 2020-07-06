package com.example.simplemovies.detailscreen


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplemovies.databinding.CastViewItemBinding
import com.example.simplemovies.domain.CastMember
import com.example.simplemovies.homescreen.OnClickListener

class CastAdapter(val click: OnClickListener) : ListAdapter<CastMember, CastAdapter.CastViewHolder>(DiffCallback) {
    class CastViewHolder(private var binding: CastViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
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
        return CastViewHolder(CastViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            click.onClick(getItem(position).id)
        }
    }
}