package com.example.simplemovies.detailscreen


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplemovies.R.color.colorBackground
import com.example.simplemovies.databinding.FragmentDetailScreenBinding
import com.example.simplemovies.homescreen.OnClickListener

/**
 * A simple [Fragment] subclass.
 */
class Detailscreen : Fragment() {

    private lateinit var detailViewModel: DetailscreenViewModel
    private lateinit var detailViewmodelFactory: DetailscreenViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentDetailScreenBinding = FragmentDetailScreenBinding.inflate(inflater)

        binding.lifecycleOwner = this

        detailViewmodelFactory = DetailscreenViewModelFactory(DetailscreenArgs.fromBundle(requireArguments()).movieId)

        detailViewModel = ViewModelProvider(this, detailViewmodelFactory).get(DetailscreenViewModel::class.java)

        binding.viewmodel = detailViewModel

        binding.cast.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.cast.adapter = CastAdapter(OnClickListener {
            detailViewModel.displayCastDetails(it)
        })




        binding.root.setBackgroundColor(ContextCompat.getColor(this!!.requireContext()!!, colorBackground))

        return binding.root
    }


}
