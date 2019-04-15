package br.com.dafle.alodjinha

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import br.com.dafle.alodjinha.koin.Modules
import org.koin.android.ext.android.startKoin
import androidx.multidex.MultiDex



open class LodjinhaApplication : Application(), Application.ActivityLifecycleCallbacks {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(
                Modules.appModule,
                Modules.businnesModule,
                Modules.viewModelModule
            )
        )
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onActivityPaused(activity: Activity?) {

    }

    override fun onActivityResumed(activity: Activity?) {

    }

    override fun onActivityStarted(activity: Activity?) {

    }

    override fun onActivityDestroyed(activity: Activity?) {

    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

    }

    override fun onActivityStopped(activity: Activity?) {

    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {

    }
}
