package br.com.cemobile.lodjinha.ui.home

import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.design.widget.NavigationView
import android.support.test.espresso.IdlingResource
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.com.cemobile.lodjinha.R
import br.com.cemobile.lodjinha.ui.about.AboutFragment
import br.com.cemobile.lodjinha.ui.base.BaseFragment
import br.com.cemobile.lodjinha.util.testing.EspressoIdlingResource
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupViews()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_settings -> true
        else -> super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> navigateToHome()
            R.id.nav_about -> navigateToAbout()
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun setTitle(title: String) {
        supportActionBar?.title = title
    }

    fun showToolbarLogo() {
        supportActionBar?.setLogo(R.drawable.logo_menu)
    }

    fun hideToolbarLogo() {
        supportActionBar?.setLogo(null)
    }

    private fun setupViews() {
        setSupportActionBar(toolbar)
        setupNavigationDrawer()

        if (supportFragmentManager.findFragmentById(R.id.contentLayout) == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.contentLayout, HomeFragment.newInstance())
                .commit()
        }
    }

    private fun setupNavigationDrawer() {
        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun setFragment(fragment: BaseFragment) {
        // does not replace current fragment if it is the same type
        val currentFragment = supportFragmentManager.findFragmentById(R.id.contentLayout) as BaseFragment
        if (currentFragment.getTagName() == fragment.getTagName()) {
            return
        }

        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .replace(R.id.contentLayout, fragment)
            .addToBackStack(fragment.getTagName())
            .commit()
    }

    private fun navigateToHome() {
        setFragment(HomeFragment.newInstance())
    }

    private fun navigateToAbout() {
        setFragment(AboutFragment.newInstance())
    }

    @VisibleForTesting
    fun getCountingIdlingResource(): IdlingResource = EspressoIdlingResource.idlingResource

}
