package com.example.lidjinha.lodjinha

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.example.lidjinha.lodjinha.model.Banner
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : AppCompatActivity() {

    lateinit var banners: List<Banner>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupToolbar()
        setupOfferBanners()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        val toolbar: Toolbar = tb_generic_toolbar
        toolbar.title = getString(R.string.lodjinha)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupOfferBanners() {
        val bannersPagerAdapter = BannersPagerAdapter(supportFragmentManager)
        val offerBanners: ViewPager = vp_offerts_banners
        offerBanners.adapter = bannersPagerAdapter
    }

    inner class BannersPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {

            return BannerFragment.getInstance(banners[position].linkUrl, banners[position].linkUrl)
        }

        override fun getCount(): Int {
            return banners.size
        }
    }
}
