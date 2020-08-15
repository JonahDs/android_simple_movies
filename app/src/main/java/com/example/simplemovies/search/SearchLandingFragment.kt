package com.example.simplemovies.search

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
import androidx.navigation.fragment.navArgs
import com.example.simplemovies.MovieApplication
import com.example.simplemovies.databinding.FragmentSearchLandingBinding
import com.example.simplemovies.utils.MovieAdapter
import com.example.simplemovies.utils.OnClickListener
import javax.inject.Inject


class SearchLandingFragment : Fragment() {

    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory

    private val searchLandingViewModel by viewModels<SearchLandingViewModel> { viewmodelFactory }

    private val args: SearchLandingFragmentArgs by navArgs()

    /**
     * First method that gets called when a fragment is associated with its activity
     * inside here we setup the dagger component that will handle this fragment and viewmodel
     *
     * @param context Interface to global information about an application environment
     * */
    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Allow dagger to inject object annotated with @inject inside this fragment
        (requireActivity().application as MovieApplication).graph.searchscreenComponent().create()
            .inject(this)
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

        val binding: FragmentSearchLandingBinding =
            FragmentSearchLandingBinding.inflate(inflater).apply {
                viewmodel = searchLandingViewModel
            }

        // Bind the recyclerview adapter
        binding.recyclerviewSearchMovies.adapter = MovieAdapter(
            OnClickListener {
                searchLandingViewModel.navigateToDetail(it)
            }
        )

        // Trigger when navigation is set
        searchLandingViewModel.navigation.observe(
            viewLifecycleOwner,
            Observer {
                if (it != null) {
                    findNavController().navigate(
                        SearchLandingFragmentDirections.actionSearchLandingToMovieDetails(
                            it
                        )
                    )
                    searchLandingViewModel.navigationCompleted()
                }
            }
        )

        // Give viewmodel the information so it can start fetching
        searchLandingViewModel.setQuery(args.query ?: "undefined")

        binding.lifecycleOwner = this
        return binding.root
    }
}
