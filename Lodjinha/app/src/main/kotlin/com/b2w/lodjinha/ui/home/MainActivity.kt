package com.b2w.lodjinha.ui.home

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import com.b2w.lodjinha.R
import com.b2w.lodjinha.ui.BaseActivity
import com.b2w.lodjinha.util.bindView
import com.b2w.lodjinha.util.networkInfo
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import com.b2w.lodjinha.data.model.CategoryItem
import com.b2w.lodjinha.data.model.ProductItem
import com.b2w.lodjinha.ui.about.AboutFragment
import com.b2w.lodjinha.ui.home.banner.BannerPagerAdapter
import com.b2w.lodjinha.ui.home.category.CategoryListAdapter
import com.b2w.lodjinha.ui.product.EXTRA_PRODUCT
import com.b2w.lodjinha.ui.product.ProductActivity
import com.b2w.lodjinha.ui.productslist.EXTRA_PRODUCT_CATEGORY
import com.b2w.lodjinha.ui.productslist.ProductsListActivity
import com.b2w.lodjinha.ui.productslist.ProductsListAdapter
import it.xabaras.android.viewpagerindicator.widget.ViewPagerIndicator

class MainActivity : BaseActivity() {

    val toolbar by bindView<Toolbar>(R.id.toolbar_main)
    val drawer by bindView<DrawerLayout>(R.id.drawer_main)
    val navigation by bindView<NavigationView>(R.id.nav_view_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        configureToolbar()
        configureHomeFragment()
    }

    fun configureHomeFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.content_container, HomeFragment())
            .commit()
    }

    fun configureToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        drawer.addDrawerListener(
            ActionBarDrawerToggle(
                this, drawer,
                toolbar, R.string.app_name, R.string.app_name
            )
        )

        navigation.setNavigationItemSelectedListener {
            drawer.closeDrawers()
            when (it.itemId) {
                R.id.nav_item_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.content_container, HomeFragment())
                        .commit()
                    true

                }
                R.id.nav_item_about -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.content_container, AboutFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}
