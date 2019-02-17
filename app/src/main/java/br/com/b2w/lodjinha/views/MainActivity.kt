package br.com.b2w.lodjinha.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavOptions
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

    private fun setupDrawer() {
        drawer = drawer {
            toolbar = mainToolbar
            headerViewRes = R.layout.drawer_header
            primaryItem(R.string.home) {
                icon = R.drawable.home_menu
                textColorRes = R.color.dark
                onClick { _ ->
                    findNavController(R.id.fragment).navigate(R.id.action_homeFragmentDrawer_to_homeFragment)
                    false
                }
            }
            primaryItem(R.string.about) {
                icon = R.drawable.tag_menu
                textColorRes = R.color.dark
                onClick { _ ->
                    false
                }
            }
        }
    }

    private fun bindToolbarInNavController() {
        val appBarConfiguration = AppBarConfiguration(findNavController(R.id.fragment).graph, drawer.drawerLayout)
        mainToolbar.setupWithNavController(findNavController(R.id.fragment), appBarConfiguration)
    }
}