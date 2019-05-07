package br.com.amedigital

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import br.com.amedigital.banner.*
import br.com.amedigital.category.CategoryAdapter
import br.com.amedigital.category.CategoryContract
import br.com.amedigital.category.CategoryPresenter
import br.com.amedigital.model.Banner
import br.com.amedigital.model.Category
import br.com.amedigital.model.Product
import br.com.amedigital.network.LojinhaServiceEndPoint
import br.com.amedigital.product.*
import br.com.amedigital.product.ProductByCategoryActivity.Companion.CATEGORY
import br.com.amedigital.util.NavigationViewController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BannerContract.View,
                                            CategoryContract.View,
                                            ProductContract.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadBanners()
        loadCategories()
        loadProductsMoreSales()
        navigationDrawer()
    }

    private fun navigationDrawer() {
        val navigationViewController = NavigationViewController(this, toolbar)
        val menu = navigationView.menu.findItem(R.id.drawer_home)
        menu.isChecked = true
        navigationViewController.initNavigationDrawer(navigationView, drawer, this@MainActivity)
        navigationView.inflateHeaderView(R.layout.drawer_header)
    }

    private fun loadBanners() {
        val call = LojinhaServiceEndPoint().getBanner()
        val presenter = BannerPresenter(call, this)
        presenter.loadBanners()
    }

    private fun loadCategories() {
        val call = LojinhaServiceEndPoint().getCategory()
        val presenter = CategoryPresenter(call, this)
        presenter.loadCategories()
    }

    private fun loadProductsMoreSales() {
        val call = LojinhaServiceEndPoint().getTopSellingProducts()
        val presenter = ProductPresenter()
        presenter.loadProductsMoreSales(call,this)
    }

    override fun setProgressIndicatorBanner(active: Boolean) {
        progressBarBanner.visibility = if (active) View.VISIBLE else View.GONE
    }

    override fun showBanners(banners: ArrayList<Banner>) {
        bannerSlider.setAdapter(BannerAdapter(banners))
        bannerSlider.setInterval(5000)
    }

    override fun showErrorBanner(message: String) {
        showErrorMessages(message)
    }

    override fun setProgressIndicatorCategory(active: Boolean) {
        progressBarCategory.visibility = if (active) View.VISIBLE else View.GONE
    }

    override fun showCategories(categories: ArrayList<Category>) {
        recyclerviewCategory?.adapter = CategoryAdapter(categories) {
            val category = categories[it]
            val intent = Intent(this@MainActivity, ProductByCategoryActivity::class.java)
            intent.putExtra(CATEGORY, category)
            startActivity(intent)
        }
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerviewCategory?.layoutManager = layoutManager
    }

    override fun showErrorCategory(message: String) {
        showErrorMessages(message)
    }

    override fun setProgressProductMore(active: Boolean) {
        progressBarProdMore.visibility = if (active) View.VISIBLE else View.GONE
    }

    override fun showProductsMore(products: ArrayList<Product>) {
        recyclerviewMoreSales?.adapter = ProductAdapter(products) {
            val intent = Intent(this@MainActivity, ProductDetailActivity::class.java)
            intent.putExtra(ProductDetailActivity.PRODUCT, products[it])
            startActivity(intent)
        }
        val layoutManager = LinearLayoutManager(this)
        recyclerviewMoreSales?.layoutManager = layoutManager
    }

    override fun showEmpytProductMore(message: String) {
        textViewEmpytMore.visibility = View.VISIBLE
        textViewEmpytMore.text = message
    }

    override fun showErrorProductMore(message: String) {
        showErrorMessages(message)
    }

    private fun showErrorMessages(errorMessage : String) {
        val snackBarError = Snackbar.make(textViewCategoria, errorMessage, Snackbar.LENGTH_INDEFINITE)
        snackBarError.setAction(getString(R.string.ok)) { snackBarError.dismiss() }
        snackBarError.setActionTextColor(Color.WHITE)
        snackBarError.show()
    }
}
