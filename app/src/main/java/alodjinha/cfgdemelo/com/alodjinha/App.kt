package alodjinha.cfgdemelo.com.alodjinha

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule

open class App: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(module = Injection().graph)
        import(androidModule(this@App))
    }

}