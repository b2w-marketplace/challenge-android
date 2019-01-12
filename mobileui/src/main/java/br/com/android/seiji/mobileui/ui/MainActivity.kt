package br.com.android.seiji.mobileui.ui

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.com.android.seiji.mobileui.R
import br.com.android.seiji.mobileui.ui.about.AboutFragment
import br.com.android.seiji.mobileui.ui.home.HomeFragment
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        setToolbarName(resources.getString(R.string.app_name))

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment.newInstance())
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                setToolbarName(resources.getString(R.string.app_name))
                replaceFragment(HomeFragment.newInstance())
            }
            R.id.nav_about -> {
                setToolbarName(resources.getString(R.string.toolbar_about_title))
                replaceFragment(AboutFragment.newInstance())
            }
            else -> false
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setToolbarName(toolbarTitle: String) {
        toolbar.title = toolbarTitle
        setSupportActionBar(toolbar)
        setNavigationView()
    }

    private fun setNavigationView() {
        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.itemIconTintList = null

        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainActivityFragmentHost, fragment)
            .commit()
    }
}
