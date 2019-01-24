package com.sumiya.olodjinha.UI.Activities

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.sumiya.olodjinha.Model.BannerDataModel
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.Service.APIService
import com.sumiya.olodjinha.UI.Activities.Base.BaseActivity
import com.sumiya.olodjinha.UI.Adapter.BannerPagerAdapter
import com.sumiya.olodjinha.UI.Fragments.BestSellersFragment
import com.sumiya.olodjinha.UI.Fragments.CategoryFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.security.AccessController.getContext


class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        configureUI()
        configureData()
    }

    override fun configureData() {
        val call = APIService().banners().list()

        call.enqueue(object : Callback<BannerDataModel?> {
            override fun onFailure(call: Call<BannerDataModel?>?, t: Throwable?) {
                if (t != null) {
                    print(t.localizedMessage)
                }
            }

            override fun onResponse(call: Call<BannerDataModel?>?, response: Response<BannerDataModel?>?) {
                if (response != null) {
                    print(response.body())

                    val banners = response.body()!!
                    bannerPager.adapter = BannerPagerAdapter(supportFragmentManager,banners)

                }
            }
        })
    }

    override fun configureUI() {
        super.configureUI()

    }
}
