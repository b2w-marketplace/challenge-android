package marcus.com.br.b2wtest.setup

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import marcus.com.br.b2wtest.LodjinhaApp

class TestApp : LodjinhaApp() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent: TestAppComponent = DaggerTestAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }
}
