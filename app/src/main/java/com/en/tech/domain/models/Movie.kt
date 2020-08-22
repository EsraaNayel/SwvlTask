package com.en.tech.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Esraa Nayel on 8/20/2020.
 */

@Parcelize
data class Movie(
    val title: String,
    val year: Int,
    val cast: List<String>?,
    val genres: List<String>?,
    val rating: Int

) : Parcelable
