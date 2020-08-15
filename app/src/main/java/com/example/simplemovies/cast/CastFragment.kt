package com.example.simplemovies.cast

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.simplemovies.MovieApplication
import com.example.simplemovies.databinding.FragmentCastBinding
import com.example.simplemovies.utils.CastExtendedAdapter
import com.example.simplemovies.utils.CrewExtendedAdapter
import javax.inject.Inject

class CastFragment : Fragment() {

    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory

    private val castViewmodel by viewModels<CastViewmodel> { viewmodelFactory }

    private val args: CastFragmentArgs by navArgs()

    /**
     * First method that gets called when a fragment is associated with its activity
     * inside here we setup the dagger component that will handle this fragment and viewmodel
     * */
    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Allow dagger to inject object annotated with @inject inside this fragment
        (requireActivity().application as MovieApplication).graph.castComponent().create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCastBinding.inflate(inflater).apply {
            viewmodel = castViewmodel
        }

        // sets the details so the viewmodel can start fetching
        castViewmodel.setMovieId(args.id, args.type)

        // bind the recyclerview adapters
        binding.recyclerviewCastCastmembers.adapter =
            CastExtendedAdapter()

        // bind the recyclerview adapters
        binding.recyclerviewCastCrewmembers.adapter =
            CrewExtendedAdapter()

        binding.lifecycleOwner = this

        return binding.root
    }
}
