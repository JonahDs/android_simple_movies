package com.example.simplemovies

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.simplemovies.detailscreen.CastAdapter
import com.example.simplemovies.domain.Cast
import com.example.simplemovies.domain.MovieNetwork
import com.example.simplemovies.homescreen.PhotoGridAdapter
import com.example.simplemovies.network.APIStatus

private val IMAGEBASE: String = "https://image.tmdb.org/t/p/w500"

@BindingAdapter("poster_path")
fun bindImage(imgView: ImageView, poster_path: String?) {
    val stringBuilder = StringBuilder(IMAGEBASE)
    poster_path?.let {
        val imgUri = stringBuilder.append(it).toString().toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context).load(imgUri).into(imgView)
    }
}

@BindingAdapter("backdrop_path")
fun bindBackdrop(imgView: ImageView, backdrop_path: String?) {
    val stringBuilder = StringBuilder(IMAGEBASE)
    backdrop_path?.let {
        val imgUri = stringBuilder.append(it).toString().toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context).load(imgUri).centerCrop().into(imgView)
    }
}

@BindingAdapter("poster_avatar")
fun bindAvatar(imgView: ImageView, poster_avatar: String?) {
    val stringBuilder = StringBuilder(IMAGEBASE)
    poster_avatar?.let {
        val imgUri = stringBuilder.append(it).toString().toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context).load(imgUri).apply(RequestOptions.bitmapTransform(RoundedCorners(50))).into(imgView)
    }
}

@BindingAdapter("cast_profile")
fun BindCastPicture(imgView: ImageView, cast_profile: String?) {
    val stringBuilder = StringBuilder(IMAGEBASE)
    var loadingContext: Int? = null
    if(cast_profile == null) {
        loadingContext = R.drawable.ic_connection_error
    }

    val imgUri = stringBuilder.append(cast_profile).toString().toUri().buildUpon().scheme("https").build()
    Glide.with(imgView.context).load(loadingContext?: imgUri).centerCrop().into(imgView)
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MovieNetwork>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("castData")
fun bindCastRecycleView(recyclerView: RecyclerView, data: Cast?) {
    val adapter = recyclerView.adapter as CastAdapter
    adapter.submitList(data?.cast)
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: APIStatus?) {
    when (status) {
        APIStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        APIStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        APIStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}