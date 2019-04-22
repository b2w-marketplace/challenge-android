package com.desafioamedigital.application

import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: ProjectApplication)
}