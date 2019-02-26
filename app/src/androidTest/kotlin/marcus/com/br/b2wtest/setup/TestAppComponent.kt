package marcus.com.br.b2wtest.setup

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import marcus.com.br.b2wtest.di.*
import okhttp3.HttpUrl
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (AndroidSupportInjectionModule::class),
        (ViewModelModule::class),
        (ActivitiesModule::class),
        (FragmentsModule::class),
        (ServiceModule::class),
        (ApplicationModule::class),
        (TestNetworkModule::class)
    ]
)
interface TestAppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: TestApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestAppComponent
    }
}

object TestServerUrl {
    var url: HttpUrl? = null
}