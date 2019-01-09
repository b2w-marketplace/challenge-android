package alodjinha.cfgdemelo.com.repository.di

import org.kodein.di.Kodein

class ApiInjection {

    var module = Kodein.Module(name = API_INJECTION) {

    }

    companion object {
        const val API_INJECTION = "apiInjection"
    }
}