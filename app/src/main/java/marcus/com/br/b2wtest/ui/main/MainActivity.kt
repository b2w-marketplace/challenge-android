package marcus.com.br.b2wtest.ui.main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import marcus.com.br.b2wtest.R
import marcus.com.br.b2wtest.ui.BaseActivity
import marcus.com.br.b2wtest.ui.main.about.AboutFragmet
import marcus.com.br.b2wtest.ui.main.home.HomeFragment

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var manager: FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        setupToolBar()
        setupDrawer()
        MainNavigator.navigateToFragment(manager, HomeFragment(), R.id.activityHomeContainer, HomeFragment.TAG)
    }

    private fun setupToolBar() {
        setSupportActionBar(toolbar)
    }

    private fun setupDrawer() {
        val toggle = ActionBarDrawerToggle(
            this,
            activityHomeDrawerLayout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )

        activityHomeDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        activityHomeNavView.setNavigationItemSelectedListener(this)

        if (manager == null) manager = supportFragmentManager
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.menuHome -> {
                MainNavigator.navigateToFragment(manager, HomeFragment(), R.id.activityHomeContainer, HomeFragment.TAG)
            }

            R.id.menuAbout -> {
                MainNavigator.navigateToFragment(manager, AboutFragmet(), R.id.activityHomeContainer, AboutFragmet.TAG)
            }
        }

        activityHomeDrawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}