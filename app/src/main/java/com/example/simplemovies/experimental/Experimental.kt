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
import androidx.lifecycle.Observer
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
                val chipGroup = binding.chipsGroup
                it.genres.forEach { genre ->
                    chipGroup.addView(chipFactory(genre))
                }
            }
        })

        binding.discoveredMovies.setOnClickListener {
            experimentalViewmodel.fetchDiscover(
                binding.includeStates.selectedItem.toString(),
                binding.type.selectedItem.toString(),
                binding.userScore.selectedItem.toString(), requireContext().resources
            )
        }


        binding.discoveredMovies.adapter = PhotoGridAdapter(OnClickListener {
            experimentalViewmodel.navSelected(it)
        })

        experimentalViewmodel.navProperty.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                findNavController().navigate(
                    ExperimentalDirections.actionExperimentalToMovieDetails(
                        binding.type.selectedItem.toString(),
                        it
                    )
                )
                experimentalViewmodel.navCompleted()
            }
        })

        binding.includeStates.adapter = createArrayAdapter(R.array.include_states)

        binding.userScore.adapter = createArrayAdapter(R.array.user_scores)

        binding.type.adapter = createArrayAdapter(R.array.types)



        binding.retryButton.setOnClickListener {
            binding.toolbar.visibility = VISIBLE
            binding.retryButton.visibility = GONE
        }

        binding.lifecycleOwner = this
        return binding.root
    }

    private fun createArrayAdapter(arrayRes: Int): ArrayAdapter<CharSequence> {
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
            .inflate(R.layout.genre_chip, null) as Chip).also {
            it.setOnCheckedChangeListener { _, isChecked ->
                experimentalViewmodel.manageChips(genre, isChecked)
            }
            it.text = genre.name
        }
    }

}
