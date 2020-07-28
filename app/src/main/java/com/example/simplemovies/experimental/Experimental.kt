package com.example.simplemovies.experimental

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.simplemovies.MovieApplication
import com.example.simplemovies.R
import com.example.simplemovies.databinding.ExperimentalFragmentBinding
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
        val binding: ExperimentalFragmentBinding =
            ExperimentalFragmentBinding.inflate(inflater).apply {
                viewmodel = experimentalViewmodel
            }

        //Setup genres chip
        experimentalViewmodel.fetchedGenres.observe(viewLifecycleOwner, Observer {
            if (it.data != null) {
                val chipGroup = binding.chipsGroup
                it.data.genres.forEach { genre ->
                    val chip = LayoutInflater.from(requireContext())
                        .inflate(R.layout.genre_chip, null) as Chip
                    chip.text = genre.name
                    chipGroup.addView(chip)
                }
            }
        })

        //Setup spinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.include_states,
            android.R.layout.simple_spinner_item
        )
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.includeStates.adapter = adapter
            }

        binding.lifecycleOwner = this
        return binding.root
    }

}
