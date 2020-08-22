package com.en.tech.data.remote.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.en.tech.data.remote.network.ApiService
import com.en.tech.data.remote.network.NetworkClient
import com.en.tech.data.remote.network.response.PhotosResponse
import com.en.tech.data.remote.network.response.RemoteResponse
import com.en.tech.domain.models.Movie
import com.en.tech.domain.models.Photo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type
import java.util.regex.Matcher
import java.util.regex.Pattern


/**
 * Created by Esraa Nayel on 8/18/2020.
 */
class MovieRepo {
    private val apiService: ApiService = NetworkClient.networkClient.invoke()

    companion object {
        val mainRepo = MovieRepo()
    }

    fun getMovies(context: Context): List<Movie> {
        var jsonString: String
        val gson = Gson()
        try {
            jsonString = context.assets.open("movies.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            jsonString = ""
        }
        val type: Type = object : TypeToken<List<Movie>>() {}.type
        return gson.fromJson(jsonString, type)
    }

    fun getMoviePhotos(api_key: String, text: String): LiveData<RemoteResponse<List<Photo>>> {
        val remoteResponse = MutableLiveData<RemoteResponse<List<Photo>>>()

        apiService.getMoviesPhotos(
            "flickr.photos.search",
            api_key,
            "json",
            1,
            text,
            1,
            10
        ).enqueue(object : Callback<PhotosResponse> {
            override fun onResponse(
                call: Call<PhotosResponse>,
                response: Response<PhotosResponse>
            ) {
                if (response.isSuccessful) {
                    remoteResponse.value = RemoteResponse.Success(
                        response.body()?.photosData?.photo ?: emptyList()
                    )
                    Log.i("response",response.body()?.toString())
                } else {
                    remoteResponse.value =
                        RemoteResponse.Error(response.code(), response.message())
                }
            }

            override fun onFailure(call: Call<PhotosResponse>, t: Throwable) {
                remoteResponse.value =
                    RemoteResponse.Error(500, t.message ?: "Something went wrong ")
            }
        })
        return remoteResponse
    }
}