package br.com.amedigital.product

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import br.com.amedigital.R
import br.com.amedigital.model.Category
import br.com.amedigital.model.Product
import br.com.amedigital.network.LojinhaServiceEndPoint
import br.com.amedigital.product.ProductDetailActivity.Companion.PRODUCT
import kotlinx.android.synthetic.main.activity_product_by_category.*

class ProductByCategoryActivity : AppCompatActivity(), ProductContract.ViewProductCategoryId {

    companion object {
        const val CATEGORY = "category"
    }

    var category : Category? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_by_category)

        getDataExtra(intent.extras)
    }

    private fun getDataExtra(extras: Bundle?) {
        category = extras?.getSerializable(CATEGORY) as Category
        category?.apply {
            getProductByCategory(id)
            setToolbar(description.toLowerCase())
        }
    }

    private fun setToolbar(title : String) {
        supportActionBar?.apply {
            this.title = title
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun getProductByCategory(categoryId: Int) {
        val call = LojinhaServiceEndPoint().getProductByCategory(categoryId)
        val presenter = ProductPresenter()
        presenter.listProduct(call, this)
    }

    override fun setProgressProdCategoryId(active: Boolean) {
        progressBarProdCategory.visibility = if (active) View.VISIBLE else View.GONE
    }

    override fun showProdCategoryId(products: ArrayList<Product>) {
        val twentyFirstProducts = products.take(20)
        recyclerViewProductsCategory?.adapter = ProductAdapter(twentyFirstProducts) {
            val product = twentyFirstProducts[it]
            val intent = Intent(this@ProductByCategoryActivity, ProductDetailActivity::class.java)
            intent.putExtra(PRODUCT, product)
            startActivity(intent)
        }
        val layoutManager = LinearLayoutManager(this)
        recyclerViewProductsCategory?.layoutManager = layoutManager
    }

    override fun showErrorProductCategoryId(message: String) {
        Toast.makeText(this@ProductByCategoryActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun showEmpytProductCategory(message: String) {
        textViewEmpytList.visibility = View.VISIBLE
        textViewEmpytList.text = message
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
