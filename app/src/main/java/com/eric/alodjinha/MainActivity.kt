package com.eric.alodjinha

import android.graphics.Typeface
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.eric.alodjinha.base.BaseActivity
import com.eric.alodjinha.feature.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*
import android.graphics.Typeface.createFromAsset
import com.eric.alodjinha.features.about.AboutFragment


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, MainActivityView {

    val presenter: MainActivityPresenter = MainActivityPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onCreate()
    }

    override fun configureViews() {

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.itemIconTintList = null

        addFragment(HomeFragment.getInstance())
    }

    override fun onBackPressed() {}

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_item_home -> {

                addFragment(HomeFragment.getInstance())
            }
            R.id.nav_item_about -> {

                addFragment(AboutFragment.getInstance())
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
