package br.com.dafle.alodjinha.ui.main

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import br.com.dafle.alodjinha.R
import br.com.dafle.alodjinha.model.NavItem
import br.com.dafle.alodjinha.model.NavItemEnum
import br.com.dafle.alodjinha.ui.about.AboutFragment
import br.com.dafle.alodjinha.ui.home.HomeFragment
import br.com.dafle.alodjinha.ui.main.navigation.NavigationDrawerFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.okButton

class MainActivity : AppCompatActivity(), NavigationDrawerFragment.NavigationDrawerCallbacks {

    private var mTitle: CharSequence? = null
    private var mNavigationDrawerFragment: NavigationDrawerFragment? = null
    private var mUserLearnedDrawer: Boolean = false
    private val PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigationDrawer()
        supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment.newInstance()).commit()
    }

    private fun setupNavigationDrawer() {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false)
        val toggle = object : ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                this@MainActivity.invalidateOptionsMenu()
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true
                    sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply()
                }
                this@MainActivity.invalidateOptionsMenu()
            }
        }
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        mNavigationDrawerFragment = supportFragmentManager.findFragmentById(R.id.navigation_drawer) as NavigationDrawerFragment
        mTitle = title
        mNavigationDrawerFragment?.setUp(R.id.navigation_drawer, drawer_layout)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            val view = findViewById<View>(R.id.navigation_drawer)
            drawer_layout.closeDrawer(view)
        } else {
            alert {
                title = getString(R.string.main_activity_do_you_want_close_app)
                okButton {
                    super.onBackPressed()
                }
                cancelButton { it.dismiss() }
            }.also {
                setTheme(R.style.AlertDialogTheme)
            }.show()
        }
    }

    override fun onNavigationDrawerItemSelected(item: NavItem) {
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        when (item.type) {
            NavItemEnum.HOME -> {
                if (fragment !is HomeFragment) {
                    supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment.newInstance()).commit()
                }
            }

            NavItemEnum.ABOUT -> {
                if (fragment !is AboutFragment) {
                    supportFragmentManager.beginTransaction().replace(R.id.container, AboutFragment.newInstance()).commit()
                }
            }
        }
    }
}

