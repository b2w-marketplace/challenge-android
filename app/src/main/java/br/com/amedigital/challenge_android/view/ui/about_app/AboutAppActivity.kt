package br.com.amedigital.challenge_android.view.ui.about_app

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import br.com.amedigital.challenge_android.R
import br.com.amedigital.challenge_android.view.ui.home.HomeActivity
import com.google.android.material.navigation.NavigationView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_about_app.*
import kotlinx.android.synthetic.main.activity_home.homeDrawer
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_about_app.*
import kotlinx.android.synthetic.main.nav_header_home.view.*

class AboutAppActivity  : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_app)
        setupNavigation()
    }

    private fun setupNavigation() {
        setSupportActionBar(toolbar)
        val typeFace = Typeface.createFromAsset(assets, "font/pacifico.ttf")

        val toggle = ActionBarDrawerToggle(
            this,
            homeDrawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        homeDrawer.addDrawerListener(toggle)
        toggle.syncState()

        appName.typeface = typeFace

        navViewAboutApp.itemIconTintList = null
        navViewAboutApp.getHeaderView(0).navTitle.typeface = typeFace
        navViewAboutApp.setNavigationItemSelectedListener(this)
        navViewAboutApp.bringToFront()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_home -> {
                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION
                startActivity(intent)
            }
            R.id.about_app -> {
                val intent = Intent(this, AboutAppActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION
                startActivity(intent)
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.homeDrawer)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.homeDrawer)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}