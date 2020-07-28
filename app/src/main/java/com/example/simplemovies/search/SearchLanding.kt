package com.example.simplemovies.search


import android.app.Activity
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.simplemovies.MovieApplication
import com.example.simplemovies.R
import com.example.simplemovies.databinding.FragmentSearchLandingBinding
import com.example.simplemovies.domain.GenreNetwork
import com.example.simplemovies.homescreen.OnClickListener
import com.example.simplemovies.homescreen.PhotoGridAdapter
import com.google.android.material.chip.Chip
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class SearchLanding : Fragment() {

    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory

    private lateinit var advancedAnimation: AnimationDrawable

    private val searchLandingViewModel by viewModels<SearchLandingViewModel> { viewmodelFactory }

    private val args: SearchLandingArgs by navArgs()

    private var openState: Boolean = false

    private val includingStates: List<String> = listOf("Include", "Exclude")

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
                photoGrid.adapter = PhotoGridAdapter(OnClickListener {
                    //Set the movie id inside of viewmodel
                })
            }


        searchLandingViewModel.search(args.query ?: "")

        binding.lifecycleOwner = this
        return binding.root
    }

}
