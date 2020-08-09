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
import com.example.simplemovies.homescreen.OnClickListener
import com.example.simplemovies.homescreen.PhotoGridAdapter
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class SearchLanding : Fragment() {

    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory

    private val searchLandingViewModel by viewModels<SearchLandingViewModel> { viewmodelFactory }

    private val args: SearchLandingArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as MovieApplication).graph.searchscreenComponent().create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentSearchLandingBinding =
            FragmentSearchLandingBinding.inflate(inflater).apply {
                viewmodel = searchLandingViewModel
                recyclerviewSearchMovies.adapter = PhotoGridAdapter(OnClickListener {
                    searchLandingViewModel.navigateToDetail(it)
                })
            }

        searchLandingViewModel.navigation.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                findNavController().navigate(SearchLandingDirections.actionSearchLandingToMovieDetails(it))
                searchLandingViewModel.navigationCompleted()
            }
        })

        searchLandingViewModel.setQuery(args.query?: "undefined")

        binding.lifecycleOwner = this
        return binding.root
    }

}
