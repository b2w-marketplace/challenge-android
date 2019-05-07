package br.com.amedigital

import android.app.Application
import ss.com.bannerslider.Slider

class ApplicationAme : Application() {

    companion object {
        lateinit var instance: ApplicationAme
            private set
    }

    override fun onCreate() {
        super.onCreate()
        Slider.init(PicassoImageLoadingService())
    }
}