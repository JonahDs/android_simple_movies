package com.example.simplemovies.moviepicker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import com.example.simplemovies.databinding.ListItemBinding
import com.example.simplemovies.domain.GenreNetwork

class GenreAdapter(context: Context, resource: Int, list: List<GenreNetwork>) :
    ArrayAdapter<GenreNetwork>(context, resource, list) {

    val allItems = list

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var binding = ListItemBinding.inflate(LayoutInflater.from(context))

        val genreNetwork: GenreNetwork? = getItem(position)
        if (genreNetwork != null) {
            binding.genreName.text = genreNetwork.name
        }
        return binding.root
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = FilterResults()
                results.values = allItems
                results.count = allItems.size
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                clear()
                addAll(results?.values as List<GenreNetwork>)
                notifyDataSetChanged()
            }

            override fun convertResultToString(resultValue: Any?): CharSequence {
                return (resultValue as GenreNetwork).name
            }
        }
    }
}

