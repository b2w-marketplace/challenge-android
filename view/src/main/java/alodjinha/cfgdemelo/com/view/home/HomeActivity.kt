package alodjinha.cfgdemelo.com.view.home

import alodjinha.cfgdemelo.com.model.BannersResponse
import alodjinha.cfgdemelo.com.view.R
import alodjinha.cfgdemelo.com.viewmodel.HomeViewModel
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.apend.slider.model.Slide
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val homeViewModel by lazy { HomeViewModel() }
    private val compositeDisposable by lazy { CompositeDisposable() }
    private var bannersResponse: BannersResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.itemIconTintList = null
        initSlider()
        initCategories()
        initErrorObservable()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                includeHome.visibility = View.VISIBLE
                includeAbout.visibility = View.GONE
            }
            R.id.nav_about -> {
                includeHome.visibility = View.GONE
                includeAbout.visibility = View.VISIBLE
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun initSlider() {
        homeViewModel.getBanners()
        homeViewModel.bannersObservable.subscribe {
            bannersResponse = it
            val slideList: ArrayList<Slide> = ArrayList()
            for (banner in it.banners) {
                slideList.add(Slide(banner.id, banner.imageUrl, 2))
            }
            slider.setItemClickListener { _, _, i, _ ->
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(bannersResponse!!.banners[i].linkUrl)))
            }
            runOnUiThread {
                slider.addSlides(slideList)
            }
        }.let { compositeDisposable.add(it) }
    }

    private fun initCategories() {
        homeViewModel.getCategories()

        homeViewModel.categoriesObservable.subscribe {

        }.let { compositeDisposable.add(it) }
    }

    private fun initErrorObservable() {
        homeViewModel.errorObservable.subscribe {
            Toast.makeText(this, getString(alodjinha.cfgdemelo.com.viewmodel.R.string.tryAgainLater), Toast.LENGTH_SHORT).show()
        }.let { compositeDisposable.add(it) }
    }
}
