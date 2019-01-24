package com.example.b2w.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.b2w.R
import com.example.b2w.model.Product
import com.example.b2w.util.BUNDLE_PRODUCT

class DetailCategoryActivity : AppCompatActivity() {
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_category)

        supportActionBar?.title = ""

        product = intent.getParcelableExtra(BUNDLE_PRODUCT)

        initView()
    }

    private fun initView(){

    }
}
