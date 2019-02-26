package marcus.com.br.b2wtest.di

import android.app.Application
import dagger.Binds
import dagger.Module
import marcus.com.br.b2wtest.LodjinhaApp

@Module
abstract class ApplicationModule {

    @Binds
    abstract fun application(app: LodjinhaApp): Application
}