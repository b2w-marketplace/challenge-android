package com.bryanollivie.lojinha.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.bryanollivie.lojinha.R
import com.bryanollivie.lojinha.ui.home.HomeFragment

class DrawerFragment : Fragment() {

    private var views: View? = null
    private var mDrawerToggle: ActionBarDrawerToggle? = null
    private var mDrawerLayout: DrawerLayout? = null
    private var containerView: View? = null
    lateinit var nav_menu_home: LinearLayout
    lateinit var nav_menu_sobre: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        views = inflater.inflate(R.layout.fragment_drawer, container, false)
        nav_menu_home = views!!.findViewById(R.id.nav_menu_home)
        nav_menu_sobre = views!!.findViewById(R.id.nav_menu_sobre)

        nav_menu_home.setOnClickListener {
            removeAllFragment(HomeFragment(), "Home")
            mDrawerLayout!!.closeDrawer(containerView!!)
        }

        nav_menu_sobre.setOnClickListener {
            //startActivity<SobreActivity>()
        }

        //openFragment(0)
        return views
    }

    private fun openFragment(position: Int) {

        when (position) {
            //0 -> removeAllFragment(HomeFragment(), "Home")

            else -> {
            }
        }
    }

    fun removeAllFragment(replaceFragment: Fragment, tag: String) {

        val manager = activity!!.supportFragmentManager
        val ft = manager.beginTransaction()
        manager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        ft.replace(R.id.container_body, replaceFragment)
        ft.commitAllowingStateLoss()
    }

    fun setUpDrawer(fragmentId: Int, drawerLayout: DrawerLayout, toolbar: Toolbar) {

        containerView = activity!!.findViewById(fragmentId)
        mDrawerLayout = drawerLayout
        mDrawerToggle = object : ActionBarDrawerToggle(
            activity,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
            }
        }

        mDrawerLayout!!.setDrawerListener(mDrawerToggle)
        mDrawerLayout!!.post { mDrawerToggle!!.syncState() }
        mDrawerToggle!!.getDrawerArrowDrawable().setColor(resources.getColor(R.color.colorAccent))

    }

}
