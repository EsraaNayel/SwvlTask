package com.en.tech.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.en.tech.App.Companion.context
import com.en.tech.data.remote.network.response.RemoteResponse
import com.en.tech.databinding.ActivityDetailsBinding
import com.en.tech.domain.models.Movie
import com.en.tech.domain.models.Photo
import com.en.tech.ui.details.adapter.MoviePhotosAdapter
import com.en.tech.ui.main.viewmodel.MainViewModel


class MovieDetailsActivity : AppCompatActivity() {

    companion object {
        const val KEY_MOVIE = "KEY_MOVIE"

        fun getIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(KEY_MOVIE, movie)
            return intent
        }
    }

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mAdapter: MoviePhotosAdapter

    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = intent.getParcelableExtra(KEY_MOVIE)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        initViewBinding()
        initUI()
        initData()
    }

    private fun initViewBinding() {
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun initUI() {
        mAdapter = MoviePhotosAdapter(this)
        binding.rvMoviesPhotos.adapter = mAdapter
        binding.rvMoviesPhotos.layoutManager = GridLayoutManager(context, 3)
    }

    private fun initData() {
        mainViewModel.getMoviePhotos("77dab8a558829f31bc73beba55622b6b", movie.title)
            .observe(this, Observer {
                when (it) {
                    is RemoteResponse.Success -> mAdapter.updatePhotos(it.data)

                    is RemoteResponse.Error -> Toast.makeText(
                        this,
                        it.errorMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}