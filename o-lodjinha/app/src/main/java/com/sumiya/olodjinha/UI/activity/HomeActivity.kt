package com.sumiya.olodjinha.ui.activity

import android.content.Intent
import android.os.Bundle
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.ui.adapter.BannerPagerAdapter
import com.sumiya.olodjinha.ui.fragments.BestSellersFragment
import com.sumiya.olodjinha.ui.fragments.CategoryFragment
import com.sumiya.olodjinha.model.*
import com.sumiya.olodjinha.service.APIService
import com.sumiya.olodjinha.ui.activity.base.BaseDrawerActivity
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeActivity : BaseDrawerActivity(), CategoryFragment.CategoryListener, BestSellersFragment.BestSellersListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        configureDrawer()
        configureUI()
        configureData()
    }

    fun configureData() {
        val call = APIService().banners().list()

        call.enqueue(object : Callback<BannerDataModel?> {
            override fun onFailure(call: Call<BannerDataModel?>?, t: Throwable?) {
                hideLoading()
                if (t != null) {
                    print(t.localizedMessage)
                }
            }

            override fun onResponse(call: Call<BannerDataModel?>?, response: Response<BannerDataModel?>?) {
                hideLoading()
                if (response != null) {
                    print(response.body())

                    val banners = response.body()!!
                    bannerPager.adapter = BannerPagerAdapter(supportFragmentManager,banners)

                }
            }
        })
    }

    fun configureUI() {
        showLoading("Carregando a Lodjinha")
    }

    override fun requestProductList(category: CategoryModel) {
        val productIntent = Intent(this, ProductsActivity::class.java)
        productIntent.putExtra("category", category)
        startActivity(productIntent)
    }

    override fun openProductdetail(product: ProductModel) {
        val productDetailIntent = Intent(this, ProductDetailActivity::class.java)
        productDetailIntent.putExtra("produto", product)
        startActivity(productDetailIntent)
    }
}
