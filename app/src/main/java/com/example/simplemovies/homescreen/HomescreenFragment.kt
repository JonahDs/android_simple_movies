package com.example.simplemovies.homescreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.simplemovies.MovieApplication
import com.example.simplemovies.databinding.FragmentHomescreenBinding
import com.example.simplemovies.utils.MovieAdapter
import com.example.simplemovies.utils.OnClickListener
import javax.inject.Inject


class HomescreenFragment : Fragment() {

    // Inject factory
    @Inject
    lateinit var viewModelfactory: ViewModelProvider.Factory

    // create viewmodel using injected property
    private val homescreenViewModel by viewModels<HomescreenViewModel> { viewModelfactory }

    /**
     * First method that gets called when a fragment is associated with its activity
     * inside here we setup the dagger component that will handle this fragment and viewmodel
     *
     * @param context Interface to global information about an application environment
     * */
    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Allow dagger to inject object annotated with @inject inside this fragment
        (requireActivity().application as MovieApplication).graph.homescreenComponent().create().inject(this)
    }

    /**
     * Creates and returns the view hierarchy associated with the fragment.
     *
     * @param inflater LayoutInflater object that can be used to inflate any views in the fragment
     * @param container of non-null, this is the parent view that the fragment's UI should be attached to. The fragment should not add the view itself, but this can be used to generate the LayoutParams of the view. This value may be null.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return the View for the fragment's UI, or null.
     * */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Create databinding object, inflate layout and set the viewmodel
        val binding = FragmentHomescreenBinding.inflate(inflater).apply {
            viewmodel = homescreenViewModel
        }

        // Onrefresh listener
        binding.swiperefreshlayoutHomescreen.setOnRefreshListener {
            homescreenViewModel.clearMovies()
            homescreenViewModel.fetchMovies()
            binding.swiperefreshlayoutHomescreen.isRefreshing = false
        }

        // Set recyclerview adapter
        binding.recyclerviewHomeMovies.adapter =
            MovieAdapter(
                OnClickListener {
                    // Set the movie id inside of viewmodel
                    homescreenViewModel.displayMovieDetails(it)
                }
            )

        // Observe nav property to start navigating when changed
        this.homescreenViewModel.navSelected.observe(
            viewLifecycleOwner,
            Observer {
                if (null != it) {
                    // Navigate with previously set movie id
                    findNavController()
                        .navigate(HomescreenFragmentDirections.actionHomescreenToDetailscreen(it))
                    // Reset to null to prevent unwanted navigation
                    homescreenViewModel.displayMovieCompleted()
                }
            }
        )

        // Enable live data updates
        binding.lifecycleOwner = this

        return binding.root
    }
}
