package br.com.android.seiji.mobileui.di.module

import br.com.android.seiji.data.BannersDataRepository
import br.com.android.seiji.domain.repository.BannerRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindBannersDataRepository(dataRepository: BannersDataRepository): BannerRepository
}