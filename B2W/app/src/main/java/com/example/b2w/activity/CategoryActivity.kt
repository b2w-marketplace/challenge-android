package com.example.b2w.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.b2w.R
import com.example.b2w.adapter.ListAdapter
import com.example.b2w.model.Product
import com.example.b2w.util.*
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {
    private lateinit var categoryDescription: String
    private lateinit var products: MutableList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        categoryDescription = intent.getStringExtra(BUNDLE_CATEGORY)
        title = categoryDescription

        retrieveProducts()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home ->{
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun itemClicked(position: Int){
        startActivity(Intent(this, DetailCategoryActivity::class.java)
            .putExtra(BUNDLE_PRODUCT, products[position]))
    }

    private fun retrieveProducts(){
        Volley.newRequestQueue(this).add(object: StringRequest(
            Request.Method.GET,
            URL_PRODUCT,
            Response.Listener {
                products = mountProducts(it)

                recycler_categories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                recycler_categories.adapter = ListAdapter(this, products)
            },
            Response.ErrorListener {
                showToast(this, "Ocorreu um erro ao tentar listar o banner.")
            }
        ){})
    }
}
