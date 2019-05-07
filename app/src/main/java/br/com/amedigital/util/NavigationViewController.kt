package br.com.amedigital.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import br.com.amedigital.MainActivity
import br.com.amedigital.R
import br.com.amedigital.about.AboutActivity


class NavigationViewController (var context: Context, var toolbar: Toolbar)  {

    fun initNavigationDrawer(navigationView: NavigationView, drawerLayout: DrawerLayout, activity: Activity) {
        navigationView.setNavigationItemSelectedListener { menu ->
            val id = menu.itemId
            when (id) {
                R.id.drawer_home -> {
                    val intent =  Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    drawerLayout.closeDrawers()
                }
                R.id.drawer_about -> {
                    val intent =  Intent(context, AboutActivity::class.java)
                    context.startActivity(intent)
                    drawerLayout.closeDrawers()
                }
            }
            true
        }
        val actionBarDrawerToggle = object : ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
        }
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

}