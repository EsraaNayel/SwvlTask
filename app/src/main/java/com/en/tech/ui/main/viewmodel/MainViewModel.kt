package com.en.tech.ui.main.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.en.tech.data.remote.network.response.RemoteResponse
import com.en.tech.data.remote.repo.MovieRepo
import com.en.tech.domain.models.Movie
import com.en.tech.domain.models.Photo

/**
 * Created by Esraa Nayel on 8/18/2020.
 */
class MainViewModel : ViewModel() {

    fun getMovies(context: Context): List<Movie> {
        return MovieRepo.mainRepo.getMovies(context)
    }

    fun getMoviePhotos(api_key: String, text: String): LiveData<RemoteResponse<List<Photo>>> {
        return MovieRepo.mainRepo.getMoviePhotos(api_key, text)
    }
}