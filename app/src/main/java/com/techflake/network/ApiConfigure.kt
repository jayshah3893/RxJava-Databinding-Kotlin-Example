package com.techflake.network

interface ApiConfigure {
    fun doAfterTerminate()
    fun doGetResult(object_: Any)
    fun doGetError(error: String)
    fun noInternet()
}