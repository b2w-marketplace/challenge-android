package marcus.com.br.b2wtest.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import marcus.com.br.b2wtest.LodjinhaApp
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (AndroidSupportInjectionModule::class),
        (ViewModelModule::class),
        (ActivitiesModule::class),
        (FragmentsModule::class),
        (NetworkModule::class),
        (ServiceModule::class),
        (ApplicationModule::class)
    ]
)
interface AppComponent : AndroidInjector<LodjinhaApp> {

    override fun inject(application: LodjinhaApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: LodjinhaApp): Builder

        fun build(): AppComponent
    }
}