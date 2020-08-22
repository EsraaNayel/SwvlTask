package com.en.tech.utils

/**
 * Created by Esraa Nayel on 8/21/2020.
 */
class PhotoUtil {

    companion object {

        fun photoUrl(farm: String, server: String, id: String, secret: String): String {
            val photoUrl =
                "http://farm$farm.static.flickr.com/" + server + "/" + id + "_" + secret + ".jpg"
            return photoUrl
        }

    }
}