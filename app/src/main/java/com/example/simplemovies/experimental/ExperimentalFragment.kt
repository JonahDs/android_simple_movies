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
import com.example.simplemovies.utils.MovieAdapter
import com.example.simplemovies.utils.OnClickListener
import com.google.android.material.chip.Chip
import javax.inject.Inject

class ExperimentalFragment : Fragment() {

    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory

    private val experimentalViewmodel by viewModels<ExperimentalViewModel> { viewmodelFactory }

    /**
     * First method that gets called when a fragment is associated with its activity
     * inside here we setup the dagger component that will handle this fragment and viewmodel
     *
     * @param context Interface to global information about an application environment
     * */
    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Allow dagger to inject object annotated with @inject inside this fragment
        (requireActivity().application as MovieApplication).graph.experimentalComponent().create()
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
        val binding: FragmentExperimentalBinding =
            FragmentExperimentalBinding.inflate(inflater).apply {
                viewmodel = experimentalViewmodel
            }

        // Setup the genre chips
        experimentalViewmodel.genres.observe(
            viewLifecycleOwner,
            Observer {
                if (it.genres.isNotEmpty()) {
                    val chipGroup = binding.chipgroupExperimentalGenres
                    it.genres.forEach { genre ->
                        chipGroup.addView(chipFactory(genre))
                    }
                }
            }
        )

        // Triggerd when navigation is set
        experimentalViewmodel.navProperty.observe(
            viewLifecycleOwner,
            Observer {
                if (it != null) {
                    findNavController().navigate(
                        ExperimentalFragmentDirections.actionExperimentalToMovieDetails(
                            binding.spinnerExperimentalType.selectedItem.toString(),
                            it
                        )
                    )
                    experimentalViewmodel.navCompleted()
                }
            }
        )

        bindAdapters(binding)
        observerConfigChange(binding)
        observerListeners(binding)

        binding.lifecycleOwner = this

        return binding.root
    }

    /**
     * If a configuration change occurs and the user already searched for movies then set
     * his UI back. Since linearlayoutExperimentalToolbar is visible by default it will become visible
     * again when it shouldn't
     * */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observerConfigChange(binding: FragmentExperimentalBinding) {
        if (experimentalViewmodel.discover.value != null) {
            binding.linearlayoutExperimentalToolbar.visibility = GONE
            binding.buttonExperimentalRetry.visibility = VISIBLE
        }
    }

    /**
     * Set listeners
     *
     * @param binding fragmentBinding
     * */
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

    /**
     * Bind various adapters
     *
     * @param binding fragmentBinding
     * */
    private fun bindAdapters(binding: FragmentExperimentalBinding) {
        binding.recyclerviewExperimentalMovies.adapter =
            MovieAdapter(
                OnClickListener {
                    experimentalViewmodel.navSelected(it)
                }
            )
        binding.spinnerExperimentalStates.adapter = arrayAdapterFactory(R.array.include_states)
        binding.spinnerExperimentalUserscore.adapter = arrayAdapterFactory(R.array.user_scores)
        binding.spinnerExperimentalType.adapter = arrayAdapterFactory(R.array.types)
    }

    /**
     * Factory method that creates array adapters based of a given array of possible selections
     *
     * @param arrayRes reference to string array
     * @return ArrayAdapter<CharSequence>
     * */
    private fun arrayAdapterFactory(arrayRes: Int): ArrayAdapter<CharSequence> {
        return ArrayAdapter.createFromResource(
            requireContext(),
            arrayRes,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
    }

    /**
     * Factory method that creates chips based on the genre
     *
     * @param genre GenreNetwork
     * @return Chip
     * */
    private fun chipFactory(genre: GenreNetwork): Chip {
        return (
            LayoutInflater.from(requireContext())
                .inflate(R.layout.view_genre_chip, null) as Chip
            ).also {
            it.setOnCheckedChangeListener { _, isChecked ->
                experimentalViewmodel.manageChips(genre, isChecked)
            }
            it.text = genre.name
        }
    }
}
