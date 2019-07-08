package com.techflake.ui.giphy.view

interface GiphyView {
    fun doGetVideosResponse(aResponse : Any)
    fun showLoader()
    fun showToastMessage(aString: String)
    fun noInternetConnection()
}