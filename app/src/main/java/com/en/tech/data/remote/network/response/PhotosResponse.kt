package com.en.tech.data.remote.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Esraa Nayel on 8/19/2020.
 */

data class PhotosResponse(
    @SerializedName("photos")
    val photosData:PhotosData,
    @SerializedName("stat")
    val stat: String
)