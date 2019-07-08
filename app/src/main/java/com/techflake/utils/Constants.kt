package com.techflake.utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.techflake.App
import com.techflake.R

object Constants {

    var KEY_THUMB = "KEY_THUMB"
    var KEY_ID = "KEY_ID"
    var KEY = "KEY"
    var MEDIA_PLAYER_SAMPLE = "mediaPlayerSample"

    fun loadGif(aUrl: String, aImageView: ImageView) {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.ic_placeholder)
        requestOptions.error(R.drawable.ic_placeholder)

        Glide.with(App.getInstance())
                .setDefaultRequestOptions(requestOptions)
                .load(aUrl)
                .into(aImageView)
    }

    fun checkNotEmptyString(i: String?): String {
        val ii = "N/A"
        return if (i == null || i.isEmpty())
            ii
        else
            i
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isAvailable && networkInfo.isConnected
    }

}
