package com.b2w.lodjinha.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

fun Context.networkInfo(): NetworkInfo? = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo