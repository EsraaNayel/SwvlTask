package com.en.tech.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.en.tech.App.Companion.context
import com.en.tech.databinding.ActivityMainBinding
import com.en.tech.domain.models.Movie
import com.en.tech.ui.details.MovieDetailsActivity
import com.en.tech.ui.main.adapter.MainAdapter
import com.en.tech.ui.main.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mAdapter: MainAdapter

    private var movies: List<Movie> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        initViewBinding()
        initUI()
    }

    private fun initViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun initUI() {
        mAdapter = MainAdapter(applicationContext) {
            startActivity(MovieDetailsActivity.getIntent(this, it))
        }
        binding.rvMovies.adapter = mAdapter
        binding.rvMovies.layoutManager = LinearLayoutManager(context)

        movies = mainViewModel.getMovies(context)

        Log.i("filteredMovies1", movies.toString())
//        val filteredMovies = movies.filter { it.title.contains("Day") }
//        Log.i("filteredMovies2", filteredMovies.toString())

        val moviesByYear = movies
            .groupBy { it.year }
            .toSortedMap(compareByDescending { it })

        val items = mutableListOf<Any>()

        for (entry in moviesByYear) {
            items.add(entry.key)
            items.addAll(entry.value.sortedByDescending { it.rating })
        }

        mAdapter.updateMovieList(items)

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    search(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                binding.searchBar.clearFocus()
//                binding.searchBar.setQuery(newText, false)
//                mAdapter.updateMovieList(mainViewModel.getMovies(context))

                return true
            }

        })
//        binding.searchBar.setOnCloseListener{
////            override fun onClose(): Boolean {
//              return false
//            mAdapter.updateMovieList(mainViewModel.getMovies(context))
//
////            }
//
//        }

//        binding.searchBar.setOnCloseListener {
////            searchImage.setVisibility(View.VISIBLE)
//            mAdapter.updateMovieList(mainViewModel.getMovies(context))
//            true
//        }
    }

    fun search(text: String) {
        val filteredMovies = movies.filter { it.title.contains(text) }
        val moviesByYear = filteredMovies
            .groupBy { it.year }
            .toSortedMap(compareByDescending { it })

        val items = mutableListOf<Any>()

        for (entry in moviesByYear) {
            items.add(entry.key)
            items.addAll(entry.value.sortedByDescending { it.rating }.take(5))
        }
        mAdapter.updateMovieList(items)
    }
}