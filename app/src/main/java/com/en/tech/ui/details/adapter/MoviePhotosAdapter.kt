package com.en.tech.ui.details.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.en.tech.databinding.MovieItemBinding
import com.en.tech.domain.models.Photo
import com.en.tech.utils.PhotoUtil
import com.squareup.picasso.Picasso

/**
 * Created by Esraa Nayel on 8/22/2020.
 */
class MoviePhotosAdapter(private val context: Context) :
    RecyclerView.Adapter<MoviePhotosAdapter.MovieViewHolder>() {

    private var photos: List<Photo>? = null

    fun updatePhotos(photos: List<Photo>) {
        this.photos = photos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemBinding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindHomeMovies(photos!![position], context)
    }

    override fun getItemCount(): Int {
        return photos?.size ?: 0
    }

    class MovieViewHolder(
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindHomeMovies(photo: Photo, context: Context) {

            binding.ivMovie.setImageDrawable(null)

            val url = PhotoUtil.photoUrl(photo.farm, photo.server, photo.id, photo.secret)
            Log.i("adapter",url)

            Picasso.get()
                .load(url)
                .into(binding.ivMovie)
        }
    }
}