package com.en.tech.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.en.tech.App.Companion.context
import com.en.tech.databinding.ActivityMainBinding
import com.en.tech.ui.details.MovieDetailsActivity
import com.en.tech.ui.main.adapter.MainAdapter
import com.en.tech.ui.main.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mAdapter: MainAdapter

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
        mAdapter.updateMovieList(mainViewModel.getMovies(context))

        binding.rvMovies.adapter = mAdapter
        binding.rvMovies.layoutManager = LinearLayoutManager(context)

    }
}