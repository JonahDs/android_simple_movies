package com.example.simplemovies

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.simplemovies.domain.CastMember
import com.example.simplemovies.domain.CastWrapper
import com.example.simplemovies.domain.CrewMember
import com.example.simplemovies.domain.GenreNetwork
import com.example.simplemovies.domain.MovieNetwork
import com.example.simplemovies.network.APIStatus
import com.example.simplemovies.utils.CastAdapter
import com.example.simplemovies.utils.CrewExtendedAdapter
import com.example.simplemovies.utils.MovieAdapter
import kotlinx.android.synthetic.main.fragment_homescreen.view.*
import java.text.NumberFormat

private val IMAGEBASE: String = "https://image.tmdb.org/t/p/w500"

/**
 * Binds imageview with a movie poster, if available
 * */
@BindingAdapter("poster_path")
fun bindImage(imgView: ImageView, poster_path: String?) {
    val stringBuilder = StringBuilder(IMAGEBASE)
    var loadingContext: Int? = null
    if (poster_path == null) {
        loadingContext = R.drawable.ic_no_movies_available
    }

    val imgUri = stringBuilder.append(poster_path).toString().toUri().buildUpon().scheme("https").build()
    Glide.with(imgView.context).load(loadingContext ?: imgUri).into(imgView)
}

/**
 * Binds imageview with backdrop and centre crops it
 * */
@BindingAdapter("backdrop_path")
fun bindBackdrop(imgView: ImageView, backdrop_path: String?) {
    val stringBuilder = StringBuilder(IMAGEBASE)
    backdrop_path?.let {
        val imgUri = stringBuilder.append(it).toString().toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context).load(imgUri).centerCrop().into(imgView)
    }
}

/**
 * Binds imageview with movie poster but give it rounded edges, if available
 * */
@BindingAdapter("poster_avatar")
fun bindAvatar(imgView: ImageView, poster_avatar: String?) {
    val stringBuilder = StringBuilder(IMAGEBASE)
    var loadingContext: Int? = null
    if (poster_avatar == null) {
        loadingContext = R.drawable.ic_no_movies_available
    }

    val imgUri = stringBuilder.append(poster_avatar).toString().toUri().buildUpon().scheme("https").build()
    Glide.with(imgView.context).load(loadingContext ?: imgUri)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(50))).into(imgView)
}

/**
 * Multi bind castprofile, rounded = false means no rounded edges, else add rounded edges
 * */
@BindingAdapter(value = ["castProfile", "rounded"], requireAll = false)
fun bindCastPicture(imgView: ImageView, cast_profile: String?, rounded: Boolean?) {
    val stringBuilder = StringBuilder(IMAGEBASE)
    var loadingContext: Int? = null
    if (cast_profile == null) {
        loadingContext = R.drawable.ic_no_image_avatar
    }
    val imgUri =
        stringBuilder.append(cast_profile).toString().toUri().buildUpon().scheme("https").build()
    if (rounded == true) {
        Glide.with(imgView.context).load(loadingContext ?: imgUri).centerCrop()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(50))).into(imgView)
    } else {
        Glide.with(imgView.context).load(loadingContext ?: imgUri).centerCrop().into(imgView)
    }
}

/**
 * Binds a list of movies to the recyclerview
 * */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MovieNetwork>?) {
    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
}

/**
 * Binds the first 8 castmembers to the recycerview
 * */
@BindingAdapter("castData")
fun bindCastRecycleView(recyclerView: RecyclerView, data: CastWrapper?) {
    val adapter = recyclerView.adapter as CastAdapter
    adapter.submitList(data?.cast?.take(8))
}

/**
 * Binds the full cast list to the recyclerview
 * */
@BindingAdapter("castExtendedData")
fun bindCastExtended(recyclerView: RecyclerView, data: List<CastMember>?) {
    val adapter = recyclerView.adapter as com.example.simplemovies.utils.CastExtendedAdapter
    adapter.submitList(data)
}

/**
 * Binds the full crew list to the recyclerview
 * */
@BindingAdapter("crewExtendedData")
fun bindCrewExtended(recyclerView: RecyclerView, data: List<CrewMember>?) {
    val adapter = recyclerView.adapter as CrewExtendedAdapter
    adapter.submitList(data)
}

/**
 * Binds a list of genres to a textview, added ',' between all except the last
 * */
@BindingAdapter("listGenres")
fun bindGenres(view: TextView, list: List<GenreNetwork>?) {
    val builder = StringBuilder()
    list?.let {
        it.forEachIndexed { index, genre ->
            if (index < list.size - 1) {
                builder.append(genre.name + ", ")
            } else {
                builder.append(genre.name)
            }
        }
        view.text = builder.toString()
    }
}

/**
 * Format a string of 'ints' into x.xxx.xxx, if available
 * */
@BindingAdapter("numberFormatter")
fun bindNumber(view: TextView, number: Int?) {
    if (number == null) {
        view.text = view.resources.getString(R.string.unknown)
    } else {
        val formatted = NumberFormat.getIntegerInstance().format(number)
        view.text = "$ $formatted"
    }
}

/**
 * Multi bind movie title or tv title, check which one is available
 * */
@BindingAdapter(value = ["movieTitle", "tvTitle"], requireAll = false)
fun bindTitle(view: TextView, movie: String?, tv: String?) {
    if (movie == null) {
        view.text = tv
    } else {
        view.text = movie
    }
}

/**
 * Multi bind the <type> together with the amount
 * */
@BindingAdapter("castType", "castAmount")
fun bindAmount(view: TextView, type: String, amount: Int?) {
    view.text = view.resources.getString(R.string.cast, type, amount)
}

/**
 * Converts minutes into a readable format 120 -> 2h 0m
 * */
@BindingAdapter("minute_converter")
fun bindMinutes(view: TextView, time: Int?) {
    if (time == null) {
        view.text = view.resources.getString(R.string.unknown_runtime)
    }
    time?.let {
        val hours = it / 60
        val min = it % 60
        view.text = view.resources.getString(R.string.runtime, hours.toString(), min.toString())
    }
}

/**
 * Display and API status
 * */
@BindingAdapter("apiStatus")
fun bindApiState(view: View, status: APIStatus?) {
    when (status) {
        APIStatus.LOADING -> {
            view.visibility = VISIBLE
            view.imageview_apistatus.setImageResource(R.drawable.loading_animation)
            view.textview_apistatus_status.text = view.resources.getString(R.string.api_status_fetching)
        }
        APIStatus.INTERMEDIATE -> {
            view.visibility = VISIBLE
            view.imageview_apistatus.setImageResource(R.drawable.loading_animation)
            view.textview_apistatus_status.text = view.resources.getString(R.string.api_status_almost)
        }
        APIStatus.ERROR -> {
            view.visibility = VISIBLE
            view.imageview_apistatus.setImageResource(R.drawable.ic_connection_error)
            view.textview_apistatus_status.text = view.resources.getString(R.string.api_status_error)
        }
        APIStatus.DONE -> {
            view.visibility = GONE
        }
    }
}
