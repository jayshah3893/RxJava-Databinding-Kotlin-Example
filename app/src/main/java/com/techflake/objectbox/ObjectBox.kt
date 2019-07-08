package com.techflake.objectbox

import android.content.Context
import io.objectbox.BoxStore

class ObjectBox {
    companion object {
        /*Object box initialization*/
        lateinit var mBoxStore: BoxStore
        fun initStore(aContext: Context) {
            mBoxStore = MyObjectBox.builder()
                .androidContext(aContext.applicationContext)
                .build();
        }
        fun getBoxStore() : BoxStore{
            return mBoxStore;
        }
    }
}