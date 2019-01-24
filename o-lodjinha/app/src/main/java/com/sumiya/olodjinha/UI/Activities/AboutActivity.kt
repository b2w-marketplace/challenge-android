package com.sumiya.olodjinha.UI.Activities

import android.os.Bundle
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.UI.Activities.Base.BaseDrawerActivity
import kotlinx.android.synthetic.main.app_bar_home.*

class AboutActivity : BaseDrawerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setSupportActionBar(toolbar)

        configureUI()
    }
}
