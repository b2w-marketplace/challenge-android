package br.com.rbueno.lodjinha

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import br.com.rbueno.lodjinha.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class CustomApp : Application() {

    @Inject
    lateinit var activityDispatchingInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentDispatchingInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        setupDagger()
    }

    private fun setupDagger() {
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
}