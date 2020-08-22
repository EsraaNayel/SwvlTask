package com.en.tech.data.remote.network.response

import androidx.annotation.Keep
import com.en.tech.domain.models.Photo
import com.google.gson.annotations.SerializedName

/**
 * Created by Esraa Nayel on 8/18/2020.
 */
@Keep
data class PhotosData(
    @SerializedName("page")
    val page: Int,

    @SerializedName("pages")
    val pages: Int,

    @SerializedName("perpage")
    val perpage: Int,

    @SerializedName("total")
    val total: Int,

    @SerializedName("photo")
    val photo: List<Photo>
)