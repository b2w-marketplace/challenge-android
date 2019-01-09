package alodjinha.cfgdemelo.com.alodjinha

import alodjinha.cfgdemelo.com.viewmodel.di.ViewModelInjection
import org.kodein.di.Kodein

class Injection {
    val graph = Kodein.Module(name = INJECTION) {
        import(ViewModelInjection().module)
    }

    companion object {
        const val INJECTION = "Injection"
    }
}