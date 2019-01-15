package alodjinha.cfgdemelo.com.view.home

import alodjinha.cfgdemelo.com.model.BannersResponse
import alodjinha.cfgdemelo.com.model.Product
import alodjinha.cfgdemelo.com.model.Category
import alodjinha.cfgdemelo.com.view.R
import alodjinha.cfgdemelo.com.view.adapter.ProductsAdapter
import alodjinha.cfgdemelo.com.view.adapter.CategoriesAdapter
import alodjinha.cfgdemelo.com.view.category.CategoryActivity
import alodjinha.cfgdemelo.com.viewmodel.home.HomeViewModel
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import io.reactivex.disposables.CompositeDisposable
import ir.apend.slider.model.Slide
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    CategoriesAdapter.CategoryClickListener,
    ProductsAdapter.ProductClickListener {

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
        initBestSellers()
    }

    override fun onResume() {
        super.onResume()
        toolbar.setTitle(R.string.aLodjinha)
        toolbar.setLogo(R.drawable.logo_navbar)
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
                toolbar.setTitle(R.string.aLodjinha)
                toolbar.setLogo(R.drawable.logo_navbar)
            }
            R.id.nav_about -> {
                includeHome.visibility = View.GONE
                includeAbout.visibility = View.VISIBLE
                toolbar.setTitle(R.string.about)
                toolbar.logo = null
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
            runOnUiThread {
                pbCategories.visibility = View.GONE
                rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                rvCategories.adapter = CategoriesAdapter(this, it.categories, this@HomeActivity)
            }
        }.let { compositeDisposable.add(it) }

    }

    private fun initErrorObservable() {
        homeViewModel.errorObservable.subscribe {
//            Toast.makeText(this, getString(alodjinha.cfgdemelo.com.viewmodel.R.string.tryAgainLater), Toast.LENGTH_SHORT).show()
        }.let { compositeDisposable.add(it) }
    }

    override fun getCategory(context: Context, category: Category) {
        startActivity(CategoryActivity.getActivityIntent(this, category.id, category.description))
    }

    private fun initBestSellers() {
        homeViewModel.getBestSellers()

        homeViewModel.productsObservable.subscribe {
            runOnUiThread {
                pbBestSellers.visibility = View.GONE
                rvBestSellers.layoutManager = LinearLayoutManager(this)
                rvBestSellers.adapter = ProductsAdapter(this, it.products, this@HomeActivity)
            }
        }.let { compositeDisposable.add(it) }
    }

    override fun getProduct(context: Context, product: Product) {
        Toast.makeText(this, "Você clicou em: ${product.name}", Toast.LENGTH_SHORT).show()
    }
}
