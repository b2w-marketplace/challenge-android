package marcus.com.br.b2wtest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import marcus.com.br.b2wtest.ui.main.MainActivity

@Module
abstract class ActivitiesModule {


    @ContributesAndroidInjector
    abstract fun contributesHomeActivity(): MainActivity
}