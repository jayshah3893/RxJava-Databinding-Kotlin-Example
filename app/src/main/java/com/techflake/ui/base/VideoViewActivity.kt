package com.techflake.ui.base

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.techflake.R
import com.techflake.databinding.ActivityVideoviewBinding
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.techflake.objectbox.ObjectBox
import com.techflake.objectbox.StoredGiphyClass
import com.techflake.network.model.Original
import com.techflake.objectbox.StoredGiphyClass_
import com.techflake.utils.Constants.KEY
import com.techflake.utils.Constants.KEY_ID
import com.techflake.utils.Constants.KEY_THUMB
import com.techflake.utils.Constants.MEDIA_PLAYER_SAMPLE
import io.objectbox.Box

class VideoViewActivity : BaseActivity() {

    /*variables declaration*/

    private var mBinding: ActivityVideoviewBinding? = null
    private lateinit var mPlayer: SimpleExoPlayer
    private lateinit var mMediaDataSourceFactory: DataSource.Factory
    private var mTrackSelector: DefaultTrackSelector? = null
    private val mVideoTrackSelectionFactory = AdaptiveTrackSelection.Factory()
    private var mCurrentWindow: Int = 0
    private var mPlaybackPosition: Long = 0
    private var mVoteModelBox: Box<StoredGiphyClass>? = null
    private var mUpVote = 0
    private var mDownVote = 0
    private var mVoterClass: StoredGiphyClass? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_videoview)

        /*variables initialization*/

        initExoPlayer()
        initModelBox()
    }

    fun initModelBox() {
        val mId = intent.getStringExtra(KEY_ID)
        mVoteModelBox = ObjectBox.getBoxStore().boxFor(StoredGiphyClass::class.java)

        val mVoterModelStoredList = mVoteModelBox!!
                .query()
                .equal(StoredGiphyClass_.mKey, mId)
                .build()
                .find()

        if (mVoterModelStoredList.isNotEmpty()) {
            mVoterClass = mVoterModelStoredList[0]
            mUpVote = mVoterClass!!.mUpVote
            mDownVote = mVoterClass!!.mDownVote
            refreshData()
        } else {
            mVoterClass = StoredGiphyClass()
            mVoterClass!!.mKey = mId
            mVoterClass!!.mUpVote = mUpVote
            mVoterClass!!.mDownVote = mDownVote
            mVoterClass!!.mThumb = intent.getStringExtra(KEY_THUMB)
            mVoterClass!!.mMp4Url = (intent.getSerializableExtra(KEY) as Original).mp4!!
            mVoteModelBox!!.put(mVoterClass!!)
        }
    }

    fun refreshData() {
        mBinding!!.data = mVoterClass
    }

    /*store up vote*/
    fun pushUpVote(aView: View) {
        mUpVote += 1
        mVoterClass!!.mUpVote = mUpVote
        mVoteModelBox!!.put(mVoterClass!!)
        refreshData()
        makeMeShake(aView)
    }

    /*store down vote*/
    fun pushDownVote(aView: View) {
        mDownVote += 1
        mVoterClass!!.mDownVote = mDownVote
        mVoteModelBox!!.put(mVoterClass!!)
        refreshData()
        makeMeShake(aView)
    }

    /*store in favourite*/
    fun pushStar(aView: View) {
        mVoterClass!!.mIsFavourite = true
        mVoteModelBox!!.put(mVoterClass!!)
        makeMeShake(aView)
        showToast(getString(R.string.lbl_added_favourite))
    }

    fun initExoPlayer() {

        mTrackSelector = DefaultTrackSelector(mVideoTrackSelectionFactory)
        mMediaDataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, MEDIA_PLAYER_SAMPLE))

        val mediaSource = ExtractorMediaSource.Factory(mMediaDataSourceFactory)
                .createMediaSource(Uri.parse((intent.getSerializableExtra(KEY) as Original).mp4))
        mPlayer = ExoPlayerFactory.newSimpleInstance(this, mTrackSelector)
        with(mPlayer) {
            prepare(mediaSource, false, false)
            playWhenReady = true
        }
        mBinding!!.exVideoView.setShutterBackgroundColor(Color.TRANSPARENT)
        mBinding!!.exVideoView.player = mPlayer
        mBinding!!.exVideoView.requestFocus()

    }

    private fun updateStartPosition() {
        with(mPlayer) {
            mPlaybackPosition = currentPosition
            mCurrentWindow = currentWindowIndex
            playWhenReady = playWhenReady
        }
    }

    private fun releasePlayer() {
        updateStartPosition()
        mPlayer.playWhenReady = false;
        mPlayer.release()
        mTrackSelector = null
    }

    public override fun onStart() {
        initExoPlayer()
        super.onStart()
    }

    public override fun onResume() {
        initExoPlayer()
        super.onResume()
    }

    public override fun onPause() {
        releasePlayer()
        super.onPause()
    }

    public override fun onStop() {
        releasePlayer()
        super.onStop()
    }

    override fun onDestroy() {
        releasePlayer()
        super.onDestroy()
    }
}
