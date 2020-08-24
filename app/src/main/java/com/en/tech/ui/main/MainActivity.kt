package com.en.tech.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
//        val filteredMovies = movies.filter { it.title.equals("day") }
//        Log.i("filteredMovies2", filteredMovies.toString())

        //
        val moviesByYear = movies
            .groupBy { it.year }
            .toSortedMap(compareByDescending { it })

        val items = mutableListOf<Any>()

        for (entry in moviesByYear) {
            items.add(entry.key)
            items.addAll(entry.value.sortedByDescending { it.rating })
        }

        mAdapter.updateMovieList(items)
    }


    //Sorting by Rating
    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    fun sort(arr: IntArray?, low: Int, high: Int) {
        if (low < high) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            val pi = partition(arr!!, low, high)

            // Recursively sort elements before
            // partition and after partition
            sort(arr, low, pi - 1)
            sort(arr, pi + 1, high)
        }
    }

    fun partition(arr: IntArray, low: Int, high: Int): Int {
        val pivot = arr[high]
        var i = low - 1 // index of smaller element
        for (j in low until high) {
            // If current element is smaller than the pivot
            if (arr[j] < pivot) {
                i++
                // swap arr[i] and arr[j]
                val temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
            }
        }
        // swap arr[i+1] and arr[high] (or pivot)
        val temp = arr[i + 1]
        arr[i + 1] = arr[high]
        arr[high] = temp
        return i + 1
    }

}