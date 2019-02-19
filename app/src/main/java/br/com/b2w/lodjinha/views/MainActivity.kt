package br.com.b2w.lodjinha.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import br.com.b2w.lodjinha.R
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
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
        drawer = DrawerBuilder()
            .withActivity(this)
            .withToolbar(mainToolbar)
            .withHeader(R.layout.drawer_header)
            .addDrawerItems(
                PrimaryDrawerItem()
                    .withName(R.string.home)
                    .withIcon(R.drawable.home_menu)
                    .withTextColorRes(R.color.dark)
                    .withOnDrawerItemClickListener { _, _, _ ->
                        if (navController.currentDestination?.id != R.id.homeFragment) {
                            navController.popBackStack()
                            findNavController(R.id.fragment).navigate(R.id.homeFragment)
                        }
                        false
                    },
                PrimaryDrawerItem()
                    .withName(R.string.about_app)
                    .withIcon(R.drawable.tag_menu)
                    .withTextColorRes(R.color.dark)
                    .withOnDrawerItemClickListener { _, _, _ ->
                        if (navController.currentDestination?.id != R.id.aboutFragment) {
                            navController.popBackStack()
                            findNavController(R.id.fragment).navigate(R.id.aboutFragment)
                        }
                        false
                    }
            )
            .build()
    }

    private fun bindToolbarInNavController() {
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.aboutFragment), drawer.drawerLayout)
        mainToolbar.setupWithNavController(findNavController(R.id.fragment), appBarConfiguration)
    }
}