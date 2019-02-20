package marcus.com.br.b2wtest.ui

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import marcus.com.br.b2wtest.R
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFont()
    }

    override fun attachBaseContext(newFont: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newFont))
    }

    private fun setFont() {
        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Pacifico-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }
}