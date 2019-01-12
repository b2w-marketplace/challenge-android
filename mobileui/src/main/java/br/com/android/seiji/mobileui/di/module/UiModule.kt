package br.com.android.seiji.mobileui.di.module

import br.com.android.seiji.domain.executor.PostExecutionThread
import br.com.android.seiji.mobileui.UiThread
import br.com.android.seiji.mobileui.ui.MainActivity
import br.com.android.seiji.mobileui.ui.home.HomeFragment
import br.com.android.seiji.mobileui.ui.productDetail.ProductDetailActivity
import br.com.android.seiji.mobileui.ui.productsByCategory.ProductsByCategoryListActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesProductsByCategoryListActivity(): ProductsByCategoryListActivity

    @ContributesAndroidInjector
    abstract fun contributesProductDetailActivity(): ProductDetailActivity

    @ContributesAndroidInjector
    abstract fun contributesHomeFragment(): HomeFragment
}