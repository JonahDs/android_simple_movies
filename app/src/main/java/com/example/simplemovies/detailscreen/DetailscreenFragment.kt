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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplemovies.MovieApplication
import com.example.simplemovies.R.color.detailBackground
import com.example.simplemovies.databinding.FragmentDetailScreenBinding
import com.example.simplemovies.homescreen.OnClickListener
import java.util.*
import java.util.Locale.GERMAN
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class DetailscreenFragment : Fragment() {

    @Inject
    lateinit var viewModelfactory: ViewModelProvider.Factory

    private val detailViewModel by viewModels<DetailscreenViewModel> { viewModelfactory }

    private val args: DetailscreenFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as MovieApplication).graph.detailscreenComponent().create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentDetailScreenBinding =
            FragmentDetailScreenBinding.inflate(inflater).apply {
                viewmodel = detailViewModel
            }

        binding.lifecycleOwner = this

        binding.movieCast.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.movieCast.adapter = CastAdapter(OnClickListener {
            detailViewModel.displayCastDetails(it)
        })

        binding.root.setBackgroundColor(
            ContextCompat.getColor(
                this!!.requireContext()!!,
                detailBackground
            )
        )

        detailViewModel.setState(args.type.toLowerCase(Locale.ROOT), args.id)

        return binding.root
    }


}
