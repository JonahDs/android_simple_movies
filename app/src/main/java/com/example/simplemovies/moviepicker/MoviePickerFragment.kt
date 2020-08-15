package com.example.simplemovies.moviepicker


import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
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

    /**
     * First method that gets called when a fragment is associated with its activity
     * inside here we setup the dagger component that will handle this fragment and viewmodel
     * */
    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Allow dagger to inject object annotated with @inject inside this fragment
        (requireActivity().application as MovieApplication).graph.moviepickerComponent().create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMoviePickerBinding.inflate(inflater).apply {
            viewmodel = moviePickerViewModel
        }

        //Findout more click listener (navigates)
        binding.buttonMoviepickerFindout.setOnClickListener {
           moviePickerViewModel.navigationProperty?.let {
               findNavController().navigate(MoviePickerFragmentDirections.actionPickAMovieToMovieDetails(it))
               moviePickerViewModel.navigationCompleted()
           }
        }

        //Swipe refresh will display a dialog asking if the user wants a fetch a new movie
        binding.swiperefreshlayoutMoviepicker.setOnRefreshListener {
            val builder = AlertDialog.Builder(requireActivity())
            builder.setMessage("Sure you want to refresh?")
                .setPositiveButton("Yes"
                ) { _, _ ->
                    moviePickerViewModel.fetchRandomMovie()
                }
                .setNegativeButton("Cancel"
                ) { _, _ ->

                }
            //Create the AlertDialog object and return it
            builder.create().show()
            binding.swiperefreshlayoutMoviepicker.isRefreshing = false
        }

        binding.lifecycleOwner = this

        return binding.root
    }
}
