package br.com.amedigital.challenge_android.view.ui.home

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import br.com.amedigital.challenge_android.R
import br.com.amedigital.challenge_android.view.ui.about_app.AboutAppActivity
import com.bumptech.glide.manager.SupportRequestManagerFragment
import com.google.android.material.navigation.NavigationView
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.app_bar_home.view.*
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.nav_header_home.view.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HasSupportFragmentInjector, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupNavigation()
        initializeUI()
    }

    private fun initializeUI() {
        supportFragmentManager.beginTransaction().add(bannerFragment.id, BannerListFragment()).commit()
        supportFragmentManager.beginTransaction().add(categoriaFragment.id, CategoriaListFragment()).commit()
        supportFragmentManager.beginTransaction().add(maisVendidosFragment.id, MaisVendidosListFragment()).commit()
    }

    private fun setupNavigation() {
        setSupportActionBar(toolbar)

        val typeFace = Typeface.createFromAsset(assets, "font/pacifico.ttf")
        toolbar.toolbarTitle.typeface = typeFace

        val toggle = ActionBarDrawerToggle(
            this,
            homeDrawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        homeDrawer.addDrawerListener(toggle)
        toggle.syncState()

        navViewHome.itemIconTintList = null
        navViewHome.getHeaderView(0).navTitle.typeface = typeFace
        navViewHome.setNavigationItemSelectedListener(this)
        navViewHome.bringToFront()
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

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
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
