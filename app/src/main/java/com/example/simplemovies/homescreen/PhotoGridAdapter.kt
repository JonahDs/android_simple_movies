package com.example.simplemovies.homescreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplemovies.databinding.GridViewItemBinding
import com.example.simplemovies.domain.Result

class PhotoGridAdapter(val click: OnClickListener) : ListAdapter<Result, PhotoGridAdapter.MovieViewHolder>(DiffCallback) {
    class MovieViewHolder(private var binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            movieProp: Result
        ) {
            binding.movieProp = movieProp
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id === newItem.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        return MovieViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
        holder.itemView.setOnClickListener {
            click.onClick(getItem(position).id)
        }
    }
}

//Listen for clicks on the movies
class OnClickListener(val click: (movieId: Int) -> Unit) {
    fun onClick(movieId: Int) = click(movieId)
}