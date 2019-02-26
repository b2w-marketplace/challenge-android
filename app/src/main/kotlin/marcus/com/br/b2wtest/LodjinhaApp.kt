package marcus.com.br.b2wtest

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import marcus.com.br.b2wtest.di.DaggerAppComponent

open class LodjinhaApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = DaggerAppComponent.builder().application(this).build()
        component.inject(this)
        return component
    }
}