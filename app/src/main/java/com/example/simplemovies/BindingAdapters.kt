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
import com.example.simplemovies.cast.CrewAdapter
import com.example.simplemovies.detailscreen.CastAdapter
import com.example.simplemovies.domain.*
import com.example.simplemovies.homescreen.PhotoGridAdapter
import com.example.simplemovies.network.APIStatus
import kotlinx.android.synthetic.main.fragment_homescreen.view.*
import java.text.NumberFormat

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
        Glide.with(imgView.context).load(imgUri)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(50))).into(imgView)
    }
}


@BindingAdapter(value = ["castProfile", "rounded"], requireAll = false)
fun BindCastPicture(imgView: ImageView, cast_profile: String?, rounded: Boolean?) {
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

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MovieNetwork>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("castData")
fun bindCastRecycleView(recyclerView: RecyclerView, data: Cast?) {
    val adapter = recyclerView.adapter as CastAdapter
    adapter.submitList(data?.cast?.take(8))
}

@BindingAdapter("castExtendedData")
fun bindCastExtended(recyclerView: RecyclerView, data: List<CastMember>?) {
    val adapter = recyclerView.adapter as com.example.simplemovies.cast.CastAdapter
    adapter.submitList(data)
}

@BindingAdapter("crewExtendedData")
fun bindCrewExtended(recyclerView: RecyclerView, data: List<CrewMember>?) {
    val adapter = recyclerView.adapter as CrewAdapter
    adapter.submitList(data)
}

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

@BindingAdapter("numberFormatter")
fun bindNumber(view: TextView, number: Int?) {
    val formatted = NumberFormat.getIntegerInstance().format(number)
    view.text = "$ $formatted"
}

@BindingAdapter("castType", "castAmount")
fun bindAmount(view: TextView, type: String, amount: Int?) {
    view.text = view.resources.getString(R.string.cast, type, amount)
}

@BindingAdapter("minute_converter")
fun bindMinutes(view: TextView, time: Int?) {
    time?.let {
        val hours = it / 60
        val min = it % 60
        view.text = view.resources.getString(R.string.runtime, hours.toString(), min.toString())
    }
}

@BindingAdapter("apiStatus")
fun bindApiState(view: View, status: APIStatus?) {
    when (status) {
        APIStatus.LOADING -> {
            view.visibility = VISIBLE
            view.api_state_image.setImageResource(R.drawable.loading_animation)
            view.api_state_text.text = view.resources.getString(R.string.api_status_fetching)
        }
        APIStatus.INTERMEDIATE -> {
            view.visibility = VISIBLE
            view.api_state_image.setImageResource(R.drawable.loading_animation)
            view.api_state_text.text = view.resources.getString(R.string.api_status_almost)
        }
        APIStatus.ERROR -> {
            view.visibility = VISIBLE
            view.api_state_image.setImageResource(R.drawable.ic_connection_error)
            view.api_state_text.text = view.resources.getString(R.string.api_status_error)
        }
        APIStatus.DONE -> {
            view.visibility = GONE
        }
    }
}
