package com.danilodequeiroz.lodjinha

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.transaction
import com.danilodequeiroz.lodjinha.about.ABOUT_FRAGMENT_TAG
import com.danilodequeiroz.lodjinha.about.AboutFragment
import com.danilodequeiroz.lodjinha.home.presentation.HOME_FRAGMENT_TAG
import com.danilodequeiroz.lodjinha.home.presentation.HomeFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        drawer_layout.addDrawerListener(ActionBarDrawerToggle(
                this,
                drawer_layout,
                toolbar,
                R.string.cd_navigation_drawer_open,
                R.string.cd_navigation_drawer_close
        ).apply { syncState() })

        nav_view.itemIconTintList = null
        nav_view.setNavigationItemSelectedListener(this)
        openHome(true)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun openHome(disableAnimation: Boolean = false) {
        if(supportFragmentManager.findFragmentByTag(HOME_FRAGMENT_TAG) !is HomeFragment) {
            supportFragmentManager.transaction(allowStateLoss = true) {
                if (!disableAnimation) setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.left_to_right, R.anim.right_to_left)
                replace(R.id.fragmentContainer, HomeFragment(), HOME_FRAGMENT_TAG)
            }
        }
    }

    private fun openAbout() {
        if(supportFragmentManager.findFragmentByTag(ABOUT_FRAGMENT_TAG) !is AboutFragment) {
            supportFragmentManager.transaction(allowStateLoss = true) {
                setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.left_to_right, R.anim.right_to_left)
                replace(R.id.fragmentContainer, AboutFragment(), ABOUT_FRAGMENT_TAG)
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_item_about_app -> openAbout()
            R.id.nav_item_home -> openHome()
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
