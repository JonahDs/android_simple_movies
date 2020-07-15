package com.example.simplemovies.moviepicker


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
import com.example.simplemovies.MovieApplication
import com.example.simplemovies.R
import com.example.simplemovies.databinding.FragmentMoviePickerBinding
import com.example.simplemovies.domain.GenresWrapper
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MoviePickerFragment : Fragment() {

    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory

    private val moviePickerViewModel by viewModels<MoviePickerViewModel> { viewmodelFactory }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MovieApplication).graph.moviepickerComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMoviePickerBinding.inflate(inflater)
        val text = binding.genresField

        moviePickerViewModel.movieGenres.observe(viewLifecycleOwner, Observer {
            val adapter = GenreAdapter(requireContext(), R.layout.list_item, it.genres)
            (text.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        })

        (text.editText as? AutoCompleteTextView)?.setOnItemClickListener { parent, _, position, _ ->
            Log.i("CLICKED", (parent.adapter as GenreAdapter).getItem(position)?.id.toString())
            moviePickerViewModel.fetchMovieById((parent.adapter as GenreAdapter).getItem(position)?.id)
        }

        return binding.root
    }


}
