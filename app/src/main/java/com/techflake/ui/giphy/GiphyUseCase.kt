package com.techflake.ui.giphy

interface GiphyUseCase {

    fun doGetVideoList(aSearch : String)

    fun doGetTrendingVideoList()

    fun onDestroy()
}