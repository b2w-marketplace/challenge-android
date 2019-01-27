package com.sumiya.olodjinha.ui.activity

import android.os.Bundle
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.ui.activity.base.BaseDrawerActivity
import kotlinx.android.synthetic.main.app_bar_home.*

class AboutActivity : BaseDrawerActivity() {
    //Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_about)
        setSupportActionBar(toolbar)

        configureDrawer()
    }
}
