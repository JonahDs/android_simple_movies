package com.example.simplemovies.homescreen


import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.example.simplemovies.database.MovieDao
import com.example.simplemovies.database.SimpleMovieDatabase
import com.example.simplemovies.databinding.FragmentHomescreenBinding
import javax.inject.Inject
import kotlin.math.log

/**
 * A simple [Fragment] subclass.
 */
class HomescreenFragment : Fragment() {

    //Inject factory
    @Inject
    lateinit var viewModelfactory: ViewModelProvider.Factory

    //create viewmodel using injected property
    private val homescreenViewModel by viewModels<HomescreenViewModel> { viewModelfactory }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        //Initialize injector
        (requireActivity().application as MovieApplication).graph.homescreenComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Create databinding object, inflate layout and set the viewmodel
        val binding = FragmentHomescreenBinding.inflate(inflater).apply {
            viewmodel = homescreenViewModel
        }

        binding.swiperefreshlayoutHomescreen.setOnRefreshListener {
            homescreenViewModel.clearMovies()
            homescreenViewModel.getMoviesStatic()
            binding.swiperefreshlayoutHomescreen.isRefreshing = false
        }

        //Set recyclerview adapter
        binding.recyclerviewHomeMovies.adapter = PhotoGridAdapter(OnClickListener {
            //Set the movie id inside of viewmodel
            homescreenViewModel.displayMovieDetails(it)
        })

        //Observe nav property to start navigating when changed
        this.homescreenViewModel.navSelected.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                //Navigate with previously set movie id
                findNavController()
                    .navigate(HomescreenFragmentDirections.actionHomescreenToDetailscreen(it))
                //Reset to null to prevent unwanted navigation
                homescreenViewModel.displayMovieCompleted()
            }
        })

        //Enable live data updates
        binding.lifecycleOwner = this

        return binding.root
    }

}
