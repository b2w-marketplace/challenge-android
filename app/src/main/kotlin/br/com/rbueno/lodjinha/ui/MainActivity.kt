package br.com.rbueno.lodjinha.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.navigation.ui.*
import br.com.rbueno.lodjinha.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private val navigationView by lazy { findViewById<NavigationView>(R.id.navigation_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_navigation) as NavHostFragment? ?: return

        val navController = host.navController
        val drawerLayout: DrawerLayout? = findViewById(R.id.drawer_layout)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.home_dest), drawerLayout)
        setupActionBar(navController, appBarConfiguration)
        setupNavigationMenu(navController)

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