package com.techflake.ui.giphy

import android.util.Log
import com.techflake.App
import com.techflake.NativeCaller.getGiphyTrendingVideoUrl
import com.techflake.NativeCaller.getGiphyUrlPrefix
import com.techflake.NativeCaller.getGiphyUrlSuffix
import com.techflake.network.ApiConfigure
import com.techflake.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class GiphyController(aOnApiConfigure: ApiConfigure) : GiphyUseCase {

    private var mOnApiConfigure: ApiConfigure? = null
    private var mDisposable: Disposable? = null
    private val aLogRequest = "apiRequest"
    private val aLogResponse = "apiResponse"
    private val aLogError = "apiError"

    init {
        mOnApiConfigure = aOnApiConfigure
    }

    override fun doGetVideoList(aStringString: String) {
        if (Constants.isNetworkAvailable(App.getInstance())) {
            mDisposable = App
                .create()
                .doGetVideos(getGiphyUrlPrefix() + aStringString + getGiphyUrlSuffix())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate {
                    mOnApiConfigure!!.doAfterTerminate()
                }.subscribe(
                    { result ->
                        run {
                            /*rest api response*/
                            printLoggerResponse(result.toString())
                            mOnApiConfigure!!.doGetResult(result)
                        }
                    }, { error ->
                        /*rest api error*/
                        printLoggerError(error!!.message!!)
                        mOnApiConfigure!!.doGetError(error.message!!)
                    }
                )
        } else {
            mOnApiConfigure!!.noInternet()
        }
    }

    override fun doGetTrendingVideoList() {
        if (Constants.isNetworkAvailable(App.getInstance())) {
            mDisposable = App
                .create()
                .doGetTrendingVideos(getGiphyTrendingVideoUrl())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate {
                    mOnApiConfigure!!.doAfterTerminate()
                }.subscribe(
                    { result ->
                        run {
                            /*rest api response*/
                            printLoggerResponse(result.toString())
                            mOnApiConfigure!!.doGetResult(result)
                        }
                    }, { error ->
                        /*rest api error*/
                        printLoggerError(error!!.message!!)
                        mOnApiConfigure!!.doGetError(error.message!!)
                    }
                )
        } else {
            mOnApiConfigure!!.noInternet()
        }
    }


    private fun printLoggerResponse(aResponse: String) {
        Log.d(aLogResponse, aResponse)
    }

    private fun printLoggerRequest(aRequest: String) {
        Log.d(aLogRequest, aRequest)
    }

    private fun printLoggerError(aResponse: String) {
        Log.d(aLogError, aResponse)
    }

    override fun onDestroy() {
        if(!mDisposable!!.isDisposed){
            mDisposable!!.dispose()
        }
    }

}