package br.com.rbueno.lodjinha.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.com.rbueno.lodjinha.R
import com.google.android.material.navigation.NavigationView
import dagger.android.AndroidInjection

const val TOOLBAR_TITLE_ARG = "toolbar_title"

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private val navigationView by lazy { findViewById<NavigationView>(R.id.navigation_view) }
    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_navigation) as NavHostFragment? ?: return

        val navController = host.navController
        val drawerLayout: DrawerLayout? = findViewById(R.id.drawer_layout)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.home_dest), drawerLayout)
        setupActionBar(navController, appBarConfiguration)
        setupNavigationMenu(navController)

        navController.addOnDestinationChangedListener { _, _, arguments ->
            arguments?.let {
                if (it.containsKey(TOOLBAR_TITLE_ARG)) {
                    toolbar.title = it.getString(TOOLBAR_TITLE_ARG)
                }
            }
        }
    }

    private fun setupNavigationMenu(navController: NavController) {
        navigationView.setupWithNavController(navController)
        navigationView.itemIconTintList = null
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_item_home -> navigateToHome()
                R.id.menu_item_about -> navigateToAbout()
            }
            closeDrawer()
            true
        }
    }

    private fun closeDrawer() {
        appBarConfiguration.drawerLayout?.closeDrawers()
    }

    private fun navigateToAbout() {
        val navController = findNavController(R.id.fragment_navigation)
        if (navController.currentDestination?.id != R.id.about_dest) {
            findNavController(R.id.fragment_navigation).navigate(R.id.about_dest)
        }
    }

    private fun navigateToHome() {
        val navController = findNavController(R.id.fragment_navigation)
        if (navController.currentDestination?.id != R.id.home) {
            navController.popBackStack(R.id.home_dest, true)
            navController.navigate(R.id.home_dest)
        }
    }

    private fun setupActionBar(navController: NavController, appBarConfig: AppBarConfiguration) {
        setupActionBarWithNavController(navController, appBarConfig)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragment_navigation).navigateUp(appBarConfiguration)
    }
}