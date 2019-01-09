package alodjinha.cfgdemelo.com.viewmodel.di

import org.kodein.di.Kodein

class ViewModelInjection {
    var module = Kodein.Module(name = VIEW_MODEL_INJECTION) {

    }

    companion object {
        const val VIEW_MODEL_INJECTION = "viewModelInjection"
    }
}