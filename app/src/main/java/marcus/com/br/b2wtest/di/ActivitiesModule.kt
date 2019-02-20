package marcus.com.br.b2wtest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import marcus.com.br.b2wtest.ui.home.HomeActivity

@Module
abstract class ActivitiesModule {


    @ContributesAndroidInjector
    abstract fun contributesHomeActivity(): HomeActivity
}