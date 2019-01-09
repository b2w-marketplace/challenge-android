package alodjinha.cfgdemelo.com.repository.di

import org.kodein.di.Kodein

class RepositoryInjection {

    var module = Kodein.Module(name = REPOSITORY_INJECTION) {
        import(ApiInjection().module)
    }

    companion object {
        const val REPOSITORY_INJECTION = "repositoryInjection"
    }
}