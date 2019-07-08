package com.techflake

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.google.gson.GsonBuilder
import com.techflake.NativeCaller.getGiphyBaseUrl
import com.techflake.network.ApiService
import com.techflake.objectbox.ObjectBox
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        mApp = this

        ObjectBox.initStore(mApp)
    }

    companion object {

        lateinit var mApp: App
        var mRetrofit: Retrofit? = null

        fun getInstance(): App {
            return mApp
        }

        fun create(): ApiService {
            /*Retrofit initialization*/
            if (mRetrofit == null) {
                val gson = GsonBuilder().setLenient().create()
                mRetrofit = Retrofit
                    .Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(getGiphyBaseUrl())
                    .client(mOkHttpClient)
                    .build()
            }
            return mRetrofit!!.create(ApiService::class.java)

        }

        var mOkHttpClient = OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}