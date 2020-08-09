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
import javax.inject.Inject


class CastFragment : Fragment() {

    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory

    private val castViewmodel by viewModels<CastViewmodel> { viewmodelFactory }

    private val args: CastFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MovieApplication).graph.castComponent().create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCastBinding.inflate(inflater).apply {
            viewmodel = castViewmodel
        }

        castViewmodel.setMovieId(args.id, args.type)

        binding.recyclerviewCastCastmembers.adapter = CastAdapter()

        binding.recyclerviewCastCrewmembers.adapter = CrewAdapter()

        binding.lifecycleOwner = this

        return binding.root
    }
}
