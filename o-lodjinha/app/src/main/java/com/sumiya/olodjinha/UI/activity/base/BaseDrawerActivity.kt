package com.sumiya.olodjinha.ui.activity.base

import android.content.Intent
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.ui.activity.AboutActivity
import com.sumiya.olodjinha.ui.activity.HomeActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*

open class BaseDrawerActivity: BaseActivity() , NavigationView.OnNavigationItemSelectedListener {
    open fun configureDrawer() {
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        navigationMenu.setNavigationItemSelectedListener(this)
        navigationMenu.itemIconTintList = null
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.nav_home -> {
                startActivity(Intent(this@BaseDrawerActivity, HomeActivity::class.java))
                finish()
            }

            R.id.nav_sobre -> {
                startActivity(Intent(this@BaseDrawerActivity, AboutActivity::class.java))
                finish()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}