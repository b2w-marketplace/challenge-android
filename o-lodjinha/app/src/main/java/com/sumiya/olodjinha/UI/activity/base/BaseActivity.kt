package com.sumiya.olodjinha.ui.activity.base

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_product_detail.*

open class BaseActivity : AppCompatActivity() {

    private var progress: ProgressDialog? = null

    //Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
    }

    //Toolbar Methods
    fun setupToolbar(title: String) {
        toolbar.title = title
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    //Loading Methods
    fun showLoading(message: String) {
        if (progress == null) {
            progress = ProgressDialog(this)
            progress!!.setMessage(message)
        }

        progress!!.show()
    }

    fun hideLoading() {
        if (progress != null && progress!!.isShowing) {
            progress!!.dismiss()
        }
    }
}