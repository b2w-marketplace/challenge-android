package br.com.prodigosorc.lodjinha.ui.activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import br.com.prodigosorc.lodjinha.ui.fragments.MainFragment
import br.com.prodigosorc.lodjinha.ui.fragments.SobreFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.support.v4.content.res.ResourcesCompat
import android.widget.TextView
import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.app.ActionBar
import br.com.prodigosorc.lodjinha.R
import br.com.prodigosorc.lodjinha.util.Constants.Companion.TITULO_MAIN
import br.com.prodigosorc.lodjinha.util.Constants.Companion.TITULO_SOBRE


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val actionBar by lazy { supportActionBar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        menuDrawer()
        iniciaMainFragment(fragmentTransaction())
    }

    private fun fragmentTransaction(): FragmentTransaction {
        val fragmentManager = supportFragmentManager
        val tx = fragmentManager.beginTransaction()
        return tx
    }

    private fun menuDrawer() {
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.itemIconTintList = null
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragmentManager = supportFragmentManager
        val tx = fragmentManager.beginTransaction()
        when (item.itemId) {
            R.id.nav_home -> {
                iniciaMainFragment(tx)
            }
            R.id.nav_sobre -> {
                setToolbar(TITULO_SOBRE, null)
                toolbar.logo = null
                tx.replace(R.id.frame_main, SobreFragment())
                tx.commit()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun iniciaMainFragment(tx: FragmentTransaction) {
        val typeface = ResourcesCompat.getFont(this, R.font.pacifico)
        setToolbar(" $TITULO_MAIN", typeface)
        toolbar.setLogo(R.mipmap.logo_menu)
        tx.replace(R.id.frame_main, MainFragment())
        tx.commit()
    }

    private fun setToolbar(title: String, typeface: Typeface?) {
        val tv = TextView(applicationContext)
        tv.text = title
        tv.textSize = 20f
        tv.setTextColor(Color.WHITE)
        tv.typeface = typeface
        actionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionBar!!.customView = tv
    }
}
