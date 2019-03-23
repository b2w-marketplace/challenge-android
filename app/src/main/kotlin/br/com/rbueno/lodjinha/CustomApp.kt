package br.com.rbueno.lodjinha

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import br.com.rbueno.lodjinha.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class CustomApp : Application(), HasActivityInjector, HasSupportFragmentInjector {

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

    override fun activityInjector() = activityDispatchingInjector

    override fun supportFragmentInjector() = fragmentDispatchingInjector

}