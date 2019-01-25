package com.sumiya.olodjinha.UI.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.sumiya.olodjinha.Model.CategoryModel
import com.sumiya.olodjinha.Model.ProductDataModel
import com.sumiya.olodjinha.Model.ProductModel
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.Service.APIService
import com.sumiya.olodjinha.UI.Activities.Base.BaseActivity
import com.sumiya.olodjinha.UI.Adapter.ProductAdapter
import kotlinx.android.synthetic.main.content_products.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsActivity : BaseActivity() {
    lateinit var category: CategoryModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        category = (intent.extras.getSerializable("category") as? CategoryModel)!!

        configureData()

        setupToolbar(category.descricao)
    }

    fun configureUI() {
//        val toggle = ActionBarDrawerToggle(
//                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
//        drawer_layout.addDrawerListener(toggle)
//        toggle.syncState()
    }

    fun configureData() {
        val call = APIService().product().list(0,20,category.id)

        call.enqueue(object : Callback<ProductDataModel> {
            override fun onFailure(call: Call<ProductDataModel>?, t: Throwable?) {
                if (t != null) {
                    print(t.localizedMessage)
                }
            }

            override fun onResponse(call: Call<ProductDataModel>?, response: Response<ProductDataModel>?) {
                if (response != null) {
                    print(response.body())
                    productsRecycler.adapter = ProductAdapter(response.body()!!, { product : ProductModel -> productClicked(product)})
                    productsRecycler.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
                    productsRecycler.addItemDecoration(DividerItemDecoration(applicationContext!!, LinearLayoutManager.VERTICAL))
                }
            }
        })
    }

    private fun productClicked(product: ProductModel) {
        var productDetailIntent = Intent(this, ProductDetailActivity::class.java)
        productDetailIntent.putExtra("produto",product)
        startActivity(productDetailIntent)
    }
}
