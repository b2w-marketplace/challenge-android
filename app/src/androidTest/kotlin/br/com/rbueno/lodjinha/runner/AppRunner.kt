package br.com.rbueno.lodjinha.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import br.com.rbueno.lodjinha.AppTest

class AppRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, AppTest::class.java.name, context)
    }

}