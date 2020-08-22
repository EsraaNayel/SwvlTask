package com.en.tech.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.en.tech.databinding.MovieMainItemBinding
import com.en.tech.domain.models.Movie

class MainAdapter(
    private val context: Context,
    private val onMovieClicked: (Movie) -> Unit
) : RecyclerView.Adapter<MainAdapter.MovieViewHolder>() {
    private var movieList: List<Movie>? = null

    fun updateMovieList(movieList: List<Movie>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemBinding =
            MovieMainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemBinding, onMovieClicked)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindHomeMovies(movieList!![position], context)
    }

    override fun getItemCount(): Int {
        return if (movieList.isNullOrEmpty()) 0 else movieList!!.size
    }

    class MovieViewHolder(
        private val binding: MovieMainItemBinding,
        private val onMovieClicked: (Movie) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindHomeMovies(movie: Movie, context: Context) {
            binding.movieTitle.text = movie.title
            binding.rating.text = movie.rating.toString()
            binding.releaseDate.text = movie.year.toString()
            binding.item.setOnClickListener { onMovieClicked(movie) }
        }
    }
}