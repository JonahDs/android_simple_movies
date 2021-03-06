package com.example.simplemovies.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplemovies.databinding.ItemMovieBinding
import com.example.simplemovies.domain.MovieNetwork

/**
 * Movie adapter
 * */
class MovieAdapter(val click: OnClickListener) : ListAdapter<MovieNetwork, MovieAdapter.MovieViewHolder>(
    DiffCallback
) {
    class MovieViewHolder(private var binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            movieProp: MovieNetwork
        ) {
            binding.movieProp = movieProp
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MovieNetwork>() {
        override fun areItemsTheSame(oldItem: MovieNetwork, newItem: MovieNetwork): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieNetwork, newItem: MovieNetwork): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
        holder.itemView.setOnClickListener {
            click.onClick(getItem(position).id)
        }
    }
}

/**
 * Clicklistener for recyclerview
 *
 * @param click lambda that handles click
 * */
class OnClickListener(val click: (movieId: Int) -> Unit) {
    fun onClick(movieId: Int) = click(movieId)
}
