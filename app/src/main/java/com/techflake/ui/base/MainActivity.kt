package com.techflake.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.techflake.R
import com.techflake.ui.giphy.presenter.GiphyPresenter
import com.techflake.ui.giphy.view.GiphyView
import com.techflake.ui.adapter.GiphyVideoAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.techflake.network.model.GiphyBaseResponse
import com.techflake.network.model.PreviewWebp
import com.techflake.utils.Constants.KEY
import com.techflake.utils.Constants.KEY_ID
import com.techflake.utils.Constants.KEY_THUMB
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.collections.forEachWithIndex
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Context
import android.view.inputmethod.InputMethodManager


class MainActivity : BaseActivity(), GiphyView {

    /*variables declaration*/

    var mGiphyPresenter: GiphyPresenter? = null
    var mGiphyBaseResponse: GiphyBaseResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*variables initialization*/

        mGiphyPresenter = GiphyPresenter(this)

        initRecyclerView()
        getTrendingVideos()
    }

    private fun initRecyclerView() {

        rvVideoList.apply {
            rvVideoList.layoutManager = GridLayoutManager(this@MainActivity, 2)
            rvVideoList.itemAnimator = DefaultItemAnimator()
            rvVideoList.isNestedScrollingEnabled = false
        }

        tvFavourite.setOnClickListener {
            startActivity(Intent(this@MainActivity, FavouritesActivity::class.java))
        }
    }

    public fun onClickSearchVideo(view: View) {
        val mSearchKey = edSearchVideo.text.toString()
        if (mSearchKey.isNotEmpty()) {
            tvTitle.text = mSearchKey
        }
        mGiphyPresenter!!.doGetVideoList(mSearchKey)
            hideKeyboard(view)
    }

    public fun getTrendingVideos() {
        mGiphyPresenter!!.doGetTrendingVideoList()
    }

    override fun doGetVideosResponse(aResponse: Any) {
        hideLoader()
        if (aResponse is GiphyBaseResponse) {
            mGiphyBaseResponse = aResponse
            val mPreviewWebpList = ArrayList<PreviewWebp>()
            aResponse.data!!.forEachWithIndex { i, giphyBaseData ->
                mPreviewWebpList.add(giphyBaseData.images!!.preview_webp!!)
            }
            rvVideoList.adapter = GiphyVideoAdapter(this, mPreviewWebpList)
        }
    }

    override fun showLoader() {
        showProgressLoader()
    }

    override fun showToastMessage(aMessage: String) {
        showToast(aMessage)
    }

    override fun noInternetConnection() {
        showToast(getString(R.string.lbl_no_internet))
    }

    public fun goToVideoView(aPosition: Int) {
        val aIntent = Intent(this, VideoViewActivity::class.java)
        aIntent.putExtra(KEY_THUMB, mGiphyBaseResponse!!.data!![aPosition].images!!.preview!!.mp4)
        aIntent.putExtra(KEY_ID, mGiphyBaseResponse!!.data!![aPosition].id)
        aIntent.putExtra(KEY, mGiphyBaseResponse!!.data!![aPosition].images!!.original)
        startActivity(aIntent)
    }
}
