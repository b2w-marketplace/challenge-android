package br.com.rbueno.lodjinha.ui

import android.content.Intent
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.model.Product
import br.com.rbueno.lodjinha.ui.about.AboutActivity
import br.com.rbueno.lodjinha.ui.home.HomeActivity
import br.com.rbueno.lodjinha.ui.home.PRODUCT_ID_ARG
import br.com.rbueno.lodjinha.ui.home.TOOLBAR_TITLE_ARG
import br.com.rbueno.lodjinha.ui.product.detail.ProductDetailActivity
import br.com.rbueno.lodjinha.ui.product.list.ProductListActivity
import com.google.android.material.navigation.NavigationView


abstract class BaseActivity : AppCompatActivity() {

    private val contentFrame by lazy { findViewById<FrameLayout>(R.id.content) }
    private val navigationView by lazy { findViewById<NavigationView>(R.id.navigation_view) }
    private val drawer by lazy { findViewById<DrawerLayout>(R.id.drawer_layout) }
    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }

    protected abstract fun getIconToolBar(): IconToolbar
    protected abstract fun getToolbarText(): String


    override fun setContentView(layoutResID: Int) {
        super.setContentView(R.layout.activity_base)
        layoutInflater.inflate(layoutResID, contentFrame)
        setupToolbar()
        setupNavigationView()
        setupDrawer()
    }


    protected fun navigateToProduct(product: Product) {
        startActivity(Intent(this, ProductDetailActivity::class.java).apply {
            putExtra(PRODUCT_ID_ARG, product.id)
            putExtra(TOOLBAR_TITLE_ARG, product.category.description)
        })
    }

    private fun setupDrawer() {
        toolbar.setNavigationOnClickListener {
            when (IconToolbar.valueOf(getIconToolBar().toString())) {
                IconToolbar.MENU -> drawer.openDrawer(GravityCompat.START)
                IconToolbar.BACK -> {
                    onBackPressed()
                }
            }
        }
        setIconMenu()
    }

    private fun setIconMenu() {
        when (IconToolbar.valueOf(getIconToolBar().toString())) {
            IconToolbar.MENU -> toolbar.setNavigationIcon(R.drawable.baseline_menu_white)
            IconToolbar.BACK -> toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white)
        }
    }

    private fun setupToolbar() {
        toolbar.title = getToolbarText()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupNavigationView() {
        navigationView.itemIconTintList = null
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_item_home -> navigateToHome()
                R.id.menu_item_about -> navigateToAbout()
            }
            drawer.closeDrawers()
            true
        }
    }

    private fun navigateToAbout() {
        startActivity(Intent(this, AboutActivity::class.java))
    }

    private fun navigateToHome() {
        if (this !is HomeActivity) {
            startActivity(Intent(this, HomeActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            })
        }
    }
}


enum class IconToolbar {
    MENU,
    BACK,
}