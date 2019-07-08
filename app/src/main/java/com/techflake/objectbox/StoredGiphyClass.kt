package com.techflake.objectbox

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
class StoredGiphyClass {
    /*Objectbox pojo class */
    @Id var mGifId : Long = 0
    var mKey : String = ""
    var mUpVote : Int = 0
    var mDownVote : Int = 0
    var mIsFavourite : Boolean = false
    var mThumb : String = ""
    var mMp4Url : String = ""
}