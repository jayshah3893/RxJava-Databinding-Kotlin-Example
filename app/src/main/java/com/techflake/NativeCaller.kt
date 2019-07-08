package com.techflake

object NativeCaller {
    
    init {
        System.loadLibrary("native-lib")
    }

    external fun getGiphyBaseUrl(): String

    external fun getGiphyUrlPrefix(): String

    external fun getGiphyUrlSuffix(): String

    external fun getGiphyTrendingVideoUrl(): String


}
