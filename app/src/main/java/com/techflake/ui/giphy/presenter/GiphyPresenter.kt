package com.techflake.ui.giphy.presenter

import com.techflake.App
import com.techflake.BuildConfig
import com.techflake.R
import com.techflake.network.ApiConfigure
import com.techflake.ui.giphy.GiphyController
import com.techflake.ui.giphy.GiphyUseCase
import com.techflake.ui.giphy.view.GiphyView
import com.techflake.utils.Constants.isNetworkAvailable

class GiphyPresenter(aGiphyView : GiphyView?) : GiphyUseCase, ApiConfigure {

    var mGiphyView : GiphyView? = null
    var mGiphyController : GiphyController? = null

    init {
        mGiphyView = aGiphyView
        mGiphyController = GiphyController(this)
    }

    override fun doGetVideoList(aStringSearch: String) {
        if(aStringSearch.isNotEmpty()){
            if (isNetworkAvailable(App.getInstance())) {
                mGiphyController!!.doGetVideoList(aStringSearch)
            }else{
                noInternet()
            }    
        }else{
            mGiphyView!!.showToastMessage(App.getInstance().getString(R.string.msg_please_enter_keyword))
        }
    }

    override fun doGetTrendingVideoList() {
        mGiphyView!!.showLoader()
        if (isNetworkAvailable(App.getInstance())) {
            mGiphyController!!.doGetTrendingVideoList()
        }else{
            noInternet()
        }
    }

    override fun doGetResult(object_: Any) {
        mGiphyView!!.doGetVideosResponse(object_)
    }

    override fun doAfterTerminate() {
        
    }

    override fun doGetError(aError: String) {
        mGiphyView!!.showToastMessage(aError)
    }

    override fun noInternet() {
        mGiphyView!!.noInternetConnection()
    }

    override fun onDestroy() {
        mGiphyView = null
    }

}