package br.com.andreguedes.alodjinha

import android.app.Application
import br.com.andreguedes.alodjinha.helper.InternetHelper
import java.io.File

class ALodjinhaApplication : Application() {

    init {
        instance = this
    }

    companion object {

        private var instance: ALodjinhaApplication? = null

        fun isNetworkAvailable(): Boolean {
            return InternetHelper.isNetworkAvailable(instance!!.getApplicationContext())
        }

        fun getFileCacheDir(): File {
            return instance!!.cacheDir
        }

    }

}