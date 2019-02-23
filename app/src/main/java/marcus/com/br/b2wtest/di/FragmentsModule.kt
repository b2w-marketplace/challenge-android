package marcus.com.br.b2wtest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import marcus.com.br.b2wtest.ui.main.home.HomeFragment

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun contributesHomeFragment(): HomeFragment
}