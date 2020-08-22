package com.en.tech.domain.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Esraa Nayel on 8/18/2020.
 */

data class Photo(
    @SerializedName("id")
    val id: String,
    @SerializedName("owner")
    val owner: String,
    @SerializedName("secret")
    val secret: String,
    @SerializedName("server")
    val server: String,
    @SerializedName("farm")
    val farm: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("ispublic")
    val ispublic: Int,
    @SerializedName("isfriend")
    val isfriend: String,
    @SerializedName("isfamily")
    val isfamily: String
)