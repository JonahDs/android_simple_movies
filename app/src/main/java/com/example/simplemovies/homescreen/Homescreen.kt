package com.example.simplemovies.homescreen


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.simplemovies.database.MovieDao
import com.example.simplemovies.database.SimpleMovieDatabase
import com.example.simplemovies.databinding.FragmentHomescreenBinding

/**
 * A simple [Fragment] subclass.
 */
class Homescreen : Fragment() {

    private lateinit var homescreenViewModel: HomescreenViewModel
    private lateinit var homescreenViewModelFactory: HomescreenViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Create databinding object and inflate the layout
        val binding = FragmentHomescreenBinding.inflate(inflater)
        val movieDao: MovieDao = SimpleMovieDatabase.getInstance(requireContext()).MovieDao
        homescreenViewModelFactory = HomescreenViewModelFactory(movieDao)
        //Create instance of homescreenviewmodel
        homescreenViewModel = ViewModelProvider(this, homescreenViewModelFactory).get(HomescreenViewModel::class.java)

        //Add viewmodel to databinding
        binding.viewmodel = homescreenViewModel

        //Set recyclerview adapter
        binding.photoGrid.adapter = PhotoGridAdapter(OnClickListener {
            //Set the movie id inside of viewmodel
            homescreenViewModel.displayMovieDetails(it)
        })

        //Observe nav property to start navigating when changed
        homescreenViewModel.navSelected.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                //Navigate with previously set movie id
                this.findNavController()
                    .navigate(HomescreenDirections.actionHomescreenToDetailscreen(it))
                //Reset to null to prevent unwanted navigation
                homescreenViewModel.displayMovieCompleted()
            }
        })


        //Enable live data updates
        binding.lifecycleOwner = this

        return binding.root
    }


}
