package com.sumiya.olodjinha.UI.Activities

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.UI.Activities.Base.BaseActivity
import com.sumiya.olodjinha.UI.Fragments.BestSellersFragment
import com.sumiya.olodjinha.UI.Fragments.CategoryFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*


class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        configureUI()
    }

    override fun configureData() {

    }

    override fun configureUI() {
        super.configureUI()

    }
}
