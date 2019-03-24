package br.com.rbueno.lodjinha

import br.com.rbueno.lodjinha.di.DaggerAppComponentTest

class AppTest : CustomApp() {

    lateinit var baseUrl: String

    override fun onCreate() {
        super.onCreate()

        setupDagger()
    }

    private fun setupDagger() {
        DaggerAppComponentTest.builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun getUrl() = baseUrl
}
