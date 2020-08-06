package com.example.simplemovies.moviepicker


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.example.simplemovies.MovieApplication
import com.example.simplemovies.databinding.FragmentMoviePickerBinding
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
        (requireActivity().application as MovieApplication).graph.moviepickerComponent().create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMoviePickerBinding.inflate(inflater)

        binding.viewmodel = moviePickerViewModel

        binding.findOutMore.setOnClickListener {
           moviePickerViewModel.navigationProperty?.let {
               findNavController().navigate(MoviePickerFragmentDirections.actionPickAMovieToMovieDetails(it))
               moviePickerViewModel.navigationCompleted()
           }
        }

        binding.lifecycleOwner = this

        return binding.root
    }
}
