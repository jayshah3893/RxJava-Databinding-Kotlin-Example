package com.techflake.ui.base

import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.techflake.*
import com.techflake.objectbox.ObjectBox
import com.techflake.objectbox.StoredGiphyClass
import com.techflake.objectbox.StoredGiphyClass_
import com.techflake.ui.adapter.GiphyFavouriteAdapter
import io.objectbox.Box
import kotlinx.android.synthetic.main.activity_favourites.*

class FavouritesActivity : BaseActivity() {

    /*variables declaration*/

    private var mVoteModelBox: Box<StoredGiphyClass>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)

        /*variables initialization*/


        initModelBox()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        rvFavouritesVideoList.apply {
            rvFavouritesVideoList.layoutManager = GridLayoutManager(this@FavouritesActivity, 2)
            rvFavouritesVideoList.itemAnimator = DefaultItemAnimator()
            rvFavouritesVideoList.isNestedScrollingEnabled = false
        }
    }

    fun initModelBox() {
        mVoteModelBox = ObjectBox.getBoxStore().boxFor(StoredGiphyClass::class.java)
        val mVoterModelStoredList = mVoteModelBox!!
                .query()
                .equal(StoredGiphyClass_.mIsFavourite, true)
                .build()
                .find()
        rvFavouritesVideoList.adapter = GiphyFavouriteAdapter(mVoterModelStoredList)
    }

}
