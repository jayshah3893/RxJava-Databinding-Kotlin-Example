package com.techflake.network

import com.techflake.network.model.GiphyBaseResponse
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {

    /*Get videos as per user search */
    @GET
    fun doGetVideos(@Url s: String): Observable<GiphyBaseResponse>

    /*Get trending videos */
    @GET
    fun doGetTrendingVideos(@Url s: String): Observable<GiphyBaseResponse>

}