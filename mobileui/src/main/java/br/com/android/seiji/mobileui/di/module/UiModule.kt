package br.com.android.seiji.mobileui.di.module

import br.com.android.seiji.domain.executor.PostExecutionThread
import br.com.android.seiji.mobileui.MainActivity
import br.com.android.seiji.mobileui.UiThread
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity
}