package com.desafioamedigital.ui.activity.about

import android.content.Intent
import android.os.Bundle
import com.desafioamedigital.R
import com.desafioamedigital.ui.activity.home.HomeActivity
import com.desafioamedigital.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.toolbar_default.*

class AboutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        configureToolbar(resources.getString(R.string.about_activity_toolbar_title))
        configureDrawer(drawer, toolbar_default, nv_menu)
    }

    override fun onBackPressed() {
        startActivity(Intent(getContext(), HomeActivity::class.java))
    }
}
