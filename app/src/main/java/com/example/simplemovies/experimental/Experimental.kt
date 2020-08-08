package com.example.simplemovies.experimental

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.simplemovies.MovieApplication
import com.example.simplemovies.R
import com.example.simplemovies.databinding.FragmentExperimentalBinding
import com.example.simplemovies.domain.GenreNetwork
import com.example.simplemovies.homescreen.OnClickListener
import com.example.simplemovies.homescreen.PhotoGridAdapter
import com.google.android.material.chip.Chip
import javax.inject.Inject


class Experimental : Fragment() {

    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory

    private val experimentalViewmodel by viewModels<ExperimentalViewModel> { viewmodelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MovieApplication).graph.experimentalComponent().create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentExperimentalBinding =
            FragmentExperimentalBinding.inflate(inflater).apply {
                viewmodel = experimentalViewmodel
            }

        experimentalViewmodel.genres.observe(viewLifecycleOwner, Observer {
            if (it.genres.isNotEmpty()) {
                val chipGroup = binding.chipgroupExperimentalGenres
                it.genres.forEach { genre ->
                    chipGroup.addView(chipFactory(genre))
                }
            }
        })

        experimentalViewmodel.navProperty.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                findNavController().navigate(
                    ExperimentalDirections.actionExperimentalToMovieDetails(
                        binding.spinnerExperimentalType.selectedItem.toString(),
                        it
                    )
                )
                experimentalViewmodel.navCompleted()
            }
        })

        bindAdapters(binding)
        observerConfigChange(binding)
        observerListeners(binding)

        binding.lifecycleOwner = this

        return binding.root
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observerConfigChange(binding: FragmentExperimentalBinding) {
        if(experimentalViewmodel.discover.value != null) {
            binding.linearlayoutExperimentalToolbar.visibility = GONE
            binding.buttonExperimentalRetry.visibility = VISIBLE
        }
    }

    private fun observerListeners(binding: FragmentExperimentalBinding) {
        binding.buttonExperimentalRetry.setOnClickListener {
            binding.linearlayoutExperimentalToolbar.visibility = VISIBLE
            binding.buttonExperimentalRetry.visibility = GONE
        }
        binding.buttonExperimentalSearch.setOnClickListener {
            experimentalViewmodel.fetchDiscover(
                binding.spinnerExperimentalStates.selectedItem.toString(),
                binding.spinnerExperimentalType.selectedItem.toString(),
                binding.spinnerExperimentalUserscore.selectedItem.toString(), requireContext().resources
            )
            binding.linearlayoutExperimentalToolbar.visibility = GONE
            binding.buttonExperimentalRetry.visibility = VISIBLE
        }

    }

    private fun bindAdapters(binding: FragmentExperimentalBinding) {
        binding.recyclerviewExperimentalMovies.adapter = PhotoGridAdapter(OnClickListener {
            experimentalViewmodel.navSelected(it)
        })
        binding.spinnerExperimentalStates.adapter = arrayAdapterFactory(R.array.include_states)
        binding.spinnerExperimentalUserscore.adapter = arrayAdapterFactory(R.array.user_scores)
        binding.spinnerExperimentalType.adapter = arrayAdapterFactory(R.array.types)
    }

    private fun arrayAdapterFactory(arrayRes: Int): ArrayAdapter<CharSequence> {
        return ArrayAdapter.createFromResource(
            requireContext(),
            arrayRes,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
    }

    private fun chipFactory(genre: GenreNetwork): Chip {
        return (LayoutInflater.from(requireContext())
            .inflate(R.layout.view_genre_chip, null) as Chip).also {
            it.setOnCheckedChangeListener { _, isChecked ->
                experimentalViewmodel.manageChips(genre, isChecked)
            }
            it.text = genre.name
        }
    }

}
