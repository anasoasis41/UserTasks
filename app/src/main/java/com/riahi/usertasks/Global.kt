package com.riahi.usertasks

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

const val WEB_SERVICE_URL = "https://jsonplaceholder.typicode.com"

@Suppress("DEPRECATION")
fun networkAvailable(app: Application): Boolean {
    val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo?.isConnectedOrConnecting ?: false
}