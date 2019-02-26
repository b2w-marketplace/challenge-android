package br.com.andrecouto.alodjinha.di.component

import android.app.Application
import br.com.andrecouto.alodjinha.LodjinhaApplication
import br.com.andrecouto.alodjinha.di.builder.ActivityBuilder
import br.com.andrecouto.alodjinha.di.module.AppModule
import br.com.andrecouto.alodjinha.di.module.DataModule
import br.com.andrecouto.alodjinha.di.module.DatabaseModule
import br.com.andrecouto.alodjinha.di.module.UtilModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AppModule::class),
    (DataModule::class),
    (UtilModule::class),
    (AndroidInjectionModule::class),
    (DatabaseModule::class),
    (ActivityBuilder::class)])
interface AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

    fun inject(app: LodjinhaApplication)
}
