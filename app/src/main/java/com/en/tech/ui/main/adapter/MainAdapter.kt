package com.en.tech.ui.main.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.en.tech.App.Companion.context
import com.en.tech.R
import com.en.tech.domain.models.Movie
import kotlinx.android.synthetic.main.movie_main_item.view.*
import kotlinx.android.synthetic.main.year_item.view.*

private const val VIEW_TYPE_HEADER = 1
private const val VIEW_TYPE_MOVIE = 2

class MainAdapter(
    private val context: Context,
    private val onMovieClicked: (Movie) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Any> = emptyList()

    fun updateMovieList(items: List<Any>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return if (item is Int)
            VIEW_TYPE_HEADER
        else {
            VIEW_TYPE_MOVIE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_HEADER) {
            HeaderViewHolder(
                LayoutInflater.from(context).inflate(R.layout.year_item, parent, false)
            )
        } else {
            MovieItemViewHolder(
                LayoutInflater.from(context).inflate(R.layout.movie_main_item, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is HeaderViewHolder) {
            holder.bind(item as Int)
        } else if (holder is MovieItemViewHolder) {
            holder.bind(item as Movie)
        }
    }

    inner class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var releaseDate: TextView = view.release_date

        fun bind(year: Int) {
            releaseDate.text = year.toString()
        }
    }

    inner class MovieItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var movieTitle: TextView = view.movie_title
        private var rating: TextView = view.rating

        fun bind(movie: Movie) {
            movieTitle.text = movie.title
            rating.text = movie.rating.toString()
        }
    }
}