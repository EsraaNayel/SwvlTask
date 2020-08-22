package com.en.tech.data.remote.network.response

import com.en.tech.domain.models.Photo

/**
 * Created by Esraa Nayel on 8/21/2020.
 */
sealed class RemoteResponse<T> {
    class Success<T>(val data: T) : RemoteResponse<T>()
    class Error<T>(
        val errorCode: Int,
        val errorMessage: String
    ) : RemoteResponse<T>()
}