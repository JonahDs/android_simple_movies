package com.example.simplemovies.search


import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.simplemovies.MovieApplication
import com.example.simplemovies.R
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

    private lateinit var advancedAnimation: AnimationDrawable

    private val searchLandingViewModel by viewModels<SearchLandingViewModel> { viewmodelFactory }

    private val args: SearchLandingArgs by navArgs()

    private var openState: Boolean = false

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

        binding.button.setOnClickListener {
            if (openState) {
                binding.button.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_font_awesome_plus_box,
                    0,
                    0,
                    0
                )
                binding.advancedPanel.visibility = View.GONE
                openState = false
            } else {
                binding.button.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.anim_plus_to_minus, 0 ,0 ,0
                )
                advancedAnimation = binding.button.compoundDrawables[0] as AnimationDrawable
                binding.advancedPanel.visibility = View.VISIBLE
                advancedAnimation.start()
                openState = true
            }
        }

        binding.lifecycleOwner = this
        return binding.root
    }


}
