package com.example.simplemovies.detailscreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplemovies.MovieApplication
import com.example.simplemovies.R.color.detailBackground
import com.example.simplemovies.databinding.FragmentDetailScreenBinding
import com.example.simplemovies.utils.CastAdapter
import java.util.Locale
import javax.inject.Inject


class DetailscreenFragment : Fragment() {

    @Inject
    lateinit var viewModelfactory: ViewModelProvider.Factory

    private val detailViewModel by viewModels<DetailscreenViewModel> { viewModelfactory }

    private val args: DetailscreenFragmentArgs by navArgs()

    /**
     * First method that gets called when a fragment is associated with its activity
     * inside here we setup the dagger component that will handle this fragment and viewmodel
     *
     * @param context Interface to global information about an application environment
     * */
    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Allow dagger to inject object annotated with @inject inside this fragment
        (requireActivity().application as MovieApplication).graph.detailscreenComponent().create()
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
        // Inflate the layout for this fragment
        val binding: FragmentDetailScreenBinding =
            FragmentDetailScreenBinding.inflate(inflater).apply {
                viewmodel = detailViewModel
            }

        // Set recyclerview layoutmanager to be horizontal
        binding.recyclerviewDetailMoviecast.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        // bind recycler view adapter
        binding.recyclerviewDetailMoviecast.adapter =
            CastAdapter()

        // Set the root color as detailBackground
        binding.root.setBackgroundColor(
            ContextCompat.getColor(
                this.requireContext(),
                detailBackground
            )
        )

        // If the detail screen needs to display a tv series instead of a movies then hide
        // the "show cast ..." button
        if (args.type.toLowerCase(Locale.ROOT) == "tv") {
            binding.buttonDetailShowcast.visibility = View.GONE
        }

        binding.buttonDetailShowcast.setOnClickListener {
            detailViewModel.displayCastDetails()
        }

        detailViewModel.navSelected.observe(
            viewLifecycleOwner,
            Observer {
                if (it != null) {
                    findNavController().navigate(
                        DetailscreenFragmentDirections.actionMovieDetailsToCastFragment(
                            args.id,
                            args.type
                        )
                    )
                    detailViewModel.displayCastDetailsCompleted()
                }
            }
        )

        // Set information in viewmodel so the viewmodel can start fetching
        detailViewModel.setState(args.type.toLowerCase(Locale.ROOT), args.id)

        binding.lifecycleOwner = this

        return binding.root
    }
}
