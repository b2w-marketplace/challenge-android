package br.com.andrecouto.alodjinha.ui.activity.home

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import br.com.andrecouto.alodjinha.R
import br.com.andrecouto.alodjinha.databinding.ActivityHomeBinding
import br.com.andrecouto.alodjinha.ui.base.BaseActivity
import br.com.andrecouto.alodjinha.ui.fragment.about.AboutFragment
import br.com.andrecouto.alodjinha.ui.fragment.home.HomeFragment
import br.com.andrecouto.alodjinha.ui.fragment.home.HomeViewModel
import br.com.andrecouto.alodjinha.util.FragmentUtil
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_main.*

class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>(), NavigationView.OnNavigationItemSelectedListener {

    override val layoutId: Int = R.layout.activity_home
    override val viewModel: HomeViewModel by getLazyViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayShowTitleEnabled(false)
        nav_view.setNavigationItemSelectedListener(this)
        FragmentUtil.replaceFragment(supportFragmentManager, HomeFragment.newInstance(), R.id.frame_home_container, false)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                FragmentUtil.replaceFragment(supportFragmentManager, HomeFragment.newInstance(), R.id.frame_home_container, true)

            }
            R.id.nav_about -> {
                FragmentUtil.replaceFragment(supportFragmentManager, AboutFragment.newInstance(), R.id.frame_home_container, true)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
