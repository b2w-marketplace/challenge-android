package br.com.amedigital.about

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.amedigital.R
import br.com.amedigital.util.NavigationViewController
import kotlinx.android.synthetic.main.activity_main.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        navigationDrawer()
        toolbar.title = getString(R.string.about)
    }

    private fun navigationDrawer() {
        val navigationViewController = NavigationViewController(this, toolbar)
        val menu = navigationView.menu.findItem(R.id.drawer_about)
        menu.isChecked = true
        navigationViewController.initNavigationDrawer(navigationView, drawer, this@AboutActivity)
        navigationView.inflateHeaderView(R.layout.drawer_header)
    }
}
