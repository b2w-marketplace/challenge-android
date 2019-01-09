package br.com.android.seiji.mobileui

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import br.com.android.seiji.mobileui.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject

class CustomApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }

    override fun onCreate() {
        super.onCreate()
        setUpTimber()

        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)

    }

    private fun setUpTimber() {
        Timber.plant(Timber.DebugTree())
    }
}