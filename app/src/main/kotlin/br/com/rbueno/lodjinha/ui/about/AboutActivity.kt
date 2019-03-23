package br.com.rbueno.lodjinha.ui.about

import android.os.Bundle
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.ui.BaseActivity
import br.com.rbueno.lodjinha.ui.IconToolbar

class AboutActivity : BaseActivity() {
    override fun getToolbarText() = getString(R.string.about_title)
    override fun getIconToolBar() = IconToolbar.BACK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_about)
    }
}
