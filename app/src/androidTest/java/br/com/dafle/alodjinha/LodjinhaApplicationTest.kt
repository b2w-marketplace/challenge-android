package br.com.dafle.alodjinha

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.multidex.MultiDex
import br.com.dafle.alodjinha.koin.ModulesTest
import org.koin.android.ext.android.startKoin

class LodjinhaApplicationTest: Application(), Application.ActivityLifecycleCallbacks {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(
                ModulesTest.appModule,
                ModulesTest.businnesModule,
                ModulesTest.viewModelModule
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