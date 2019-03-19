package com.danilodequeiroz.lodjinha.productdetail.presentation

import android.graphics.Paint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.danilodequeiroz.lodjinha.R
import com.danilodequeiroz.lodjinha.common.presentation.*
import com.danilodequeiroz.lodjinha.common.util.CONNECTION_FAULTY
import com.danilodequeiroz.lodjinha.common.util.fromHtml
import com.danilodequeiroz.lodjinha.productdetail.domain.DetailedProducViewModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail_paralaxed.*
import kotlinx.android.synthetic.main.toolbar_collapsing_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductDetailActivity : AppCompatActivity() {

    companion object { const val PRODUCT_ID = "product-id"}

    private val productDetailViewModel: ProductDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail_paralaxed)
        setSupportActionBar(toolbarProductDetail)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        appBarProductDetail.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
             var isShow = true
             var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarProductDetail.title = ""
                    isShow = true
                } else if (isShow) {
                    collapsingToolbarProductDetail.setExpandedTitleColor(ContextCompat.getColor(this@ProductDetailActivity,R.color.white))
                    collapsingToolbarProductDetail.setCollapsedTitleTextColor(ContextCompat.getColor(this@ProductDetailActivity,R.color.white))
                    collapsingToolbarProductDetail.title = productDetailViewModel.stateProduct.value?.data?.categoriaName ?: ""
                    isShow = false
                }
            }
        })


        observeBestSellingProducts()
        observePost()
        intent?.extras?.getInt(PRODUCT_ID)?.let { paramId ->
            savedInstanceState?.let {
                productDetailViewModel.restoreProduct()
            } ?: kotlin.run {
                productDetailViewModel.pullProduct(paramId)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun observeBestSellingProducts() {
        productDetailViewModel.stateProduct.observe(this, Observer<SingleState> { state ->
            state?.let {
                when (state) {
                    is DefaultSingleState -> {
                        bestSellingProductsBind(it.data)
                    }
                    is LoadingSingleState -> bestSellingProductsLoadingBind()
                    is ErrorSingleState -> bestSellingProductsErrorBind()
                }
            }
        })
    }

    private fun observePost() {
        productDetailViewModel.stateReserve.observe(this, Observer<PostState> { state ->
            state?.let {
                when (state) {
                    is PostSuccessState -> createPostDialog()
                    is PostingState -> Snackbar.make(coordinator,R.string.reserve_loading,Snackbar.LENGTH_SHORT).show()
                    is PostErrorState -> createPostErrorSnackBarDialog(it.result, it.message)
                }
            }
        })
    }

    fun createPostDialog() {
        AlertDialog.Builder(this).setPositiveButton(R.string.ok,null).setMessage(R.string.reserve_success).show()
    }


    fun createPostErrorSnackBarDialog(result: String?, message: String?) {
        Snackbar.make(coordinator,if(result == CONNECTION_FAULTY) getString(R.string.reserve_connection_faulty) else message.toString(),Snackbar.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun bestSellingProductsBind(productViewModel: DetailedProducViewModel?) {
        productViewModel?.let { product ->
            detailProduct.visibility = View.VISIBLE
            headerCollapsingProduct.visibility = View.VISIBLE
            errorViewProductDetail.visibility = View.GONE
            progressBarProductDetail.visibility = View.GONE
            loadImage(product)
            txtProductName.text = product.nome
            txtProductCategory.text = product.categoriaName
            txtProductDesc.text = product.desc?.fromHtml()
            txtOldPrice.text = resources.getString(R.string.old_price_label,product.precoDe)
            txtOldPrice.paintFlags = txtOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            txtNewPrice.text = resources.getString(R.string.new_price_label,product.precoPor)
            fabReserve.show()
            fabReserve?.setOnClickListener {
                productDetailViewModel.postReserve()
            }
        }
    }

    private fun loadImage(product: DetailedProducViewModel) {
        imgProduct?.apply {
            progressBarProductImage.visibility = View.VISIBLE
            Picasso.get().load(product.urlImagem).error(R.drawable.ic_broken_image).into(this, object : Callback {
                override fun onSuccess() {
                    this@apply.setOnClickListener(null)
                    progressBarProductImage.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    progressBarProductImage.visibility = View.GONE
                    this@apply.setBackgroundColor(ContextCompat.getColor(this@ProductDetailActivity,R.color.background_image_error))
                    this@apply.setOnClickListener{loadImage(product)}
                }
            })
        }
    }

    private fun bestSellingProductsLoadingBind() {
        fabReserve.hide()
        headerCollapsingProduct.visibility = View.GONE
        detailProduct.visibility = View.GONE
        errorViewProductDetail.visibility = View.GONE
        progressBarProductImage.visibility = View.GONE
        progressBarProductDetail.visibility = View.VISIBLE
    }

    private fun bestSellingProductsErrorBind() {
        fabReserve.hide()
        headerCollapsingProduct.visibility = View.GONE
        detailProduct.visibility = View.GONE
        errorViewProductDetail.visibility = View.VISIBLE
        progressBarProductImage.visibility = View.GONE
        progressBarProductDetail.visibility = View.GONE
        errorViewProductDetail.setOnClickListener {
            intent?.extras?.getInt(PRODUCT_ID)?.let { paramId ->productDetailViewModel.pullProduct(paramId) }
        }
    }

}