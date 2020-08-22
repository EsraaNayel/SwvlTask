package com.en.tech.data.remote.network

import com.en.tech.data.remote.network.response.PhotosResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {


    //getPhotos
    @GET("/services/rest/")
    fun getMoviesPhotos(
        //flickr.photos.search for search
        @Query("method") method: String,
        @Query("api_key") api_key: String,
        @Query("format") format: String,
        @Query("nojsoncallback") nojsoncallback: Int,
        @Query("text") text: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Call<PhotosResponse>
}