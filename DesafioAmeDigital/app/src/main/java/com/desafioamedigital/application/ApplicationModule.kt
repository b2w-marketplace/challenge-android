package com.desafioamedigital.application

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: ProjectApplication){

    @Provides
    @Singleton
    fun provideApplication(): Application = application

}