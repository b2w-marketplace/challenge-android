package br.com.b2w.lodjinha.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import br.com.b2w.lodjinha.R
import co.zsmb.materialdrawerkt.builders.drawer
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import com.mikepenz.materialdrawer.Drawer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var drawer: Drawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupDrawer()
        bindToolbarInNavController()
    }

    fun showToolbarLogoInfo() {
        mainToolbar.title = ""
        toolbarAppLogo.visibility = View.VISIBLE
        toolbarAppName.visibility = View.VISIBLE
    }

    fun hideToolbarLogoInfo() {
        toolbarAppLogo.visibility = View.GONE
        toolbarAppName.visibility = View.GONE
    }

    private fun setupDrawer() {
        val navController = findNavController(R.id.fragment)
        drawer = drawer {
            toolbar = mainToolbar
            headerViewRes = R.layout.drawer_header
            primaryItem(R.string.home) {
                icon = R.drawable.home_menu
                textColorRes = R.color.dark
                onClick { _ ->
                    if (navController.currentDestination?.id != R.id.homeFragment) {
                        navController.popBackStack()
                        findNavController(R.id.fragment).navigate(R.id.homeFragment)
                    }
                    false
                }
            }
            primaryItem(R.string.about_app) {
                icon = R.drawable.tag_menu
                textColorRes = R.color.dark
                onClick { _ ->
                    if (navController.currentDestination?.id != R.id.aboutFragment) {
                        navController.popBackStack()
                        findNavController(R.id.fragment).navigate(R.id.aboutFragment)
                    }
                    false
                }
            }
        }
    }

    private fun bindToolbarInNavController() {
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.aboutFragment), drawer.drawerLayout)
        mainToolbar.setupWithNavController(findNavController(R.id.fragment), appBarConfiguration)
    }
}