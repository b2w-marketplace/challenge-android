package br.com.b2w.desafio.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.com.b2w.desafio.databinding.ActivityMainBinding
import br.com.b2w.desafio.R
import br.com.b2w.desafio.databinding.NavHeaderMainBinding
import android.support.v4.app.Fragment
import br.com.b2w.desafio.ui.fragment.HomeFragment
import br.com.b2w.desafio.ui.fragment.SobreFragment


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingNavHeaderMain: NavHeaderMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setToolbar()

        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, binding.appBarMain.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)

        bindingNavHeaderMain = NavHeaderMainBinding.bind(binding.navView.getHeaderView(0))

        onNavigationItemSelected(binding.navView.menu.getItem(0))
    }

    private fun setToolbar() {
        setSupportActionBar(binding.appBarMain.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null

        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                fragment = HomeFragment()
                title = getString(R.string.title_a_lodjinha)
            }
            R.id.nav_sobre -> {
                fragment = SobreFragment()
                title = item.title
            }
        }

        if (fragment != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(binding.appBarMain.contentMain.contentFrame.id, fragment)
            ft.commit()
        }

        item.isChecked = true

        binding.drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }
}
