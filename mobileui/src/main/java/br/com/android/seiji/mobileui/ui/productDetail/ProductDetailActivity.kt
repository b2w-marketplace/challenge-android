package br.com.android.seiji.mobileui.ui.productDetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.text.Spanned
import br.com.android.seiji.domain.model.Product
import br.com.android.seiji.mobileui.R
import br.com.android.seiji.mobileui.di.ViewModelFactory
import br.com.android.seiji.mobileui.extensions.gone
import br.com.android.seiji.mobileui.extensions.visible
import br.com.android.seiji.mobileui.utils.toCurrency
import br.com.android.seiji.presentation.state.Resource
import br.com.android.seiji.presentation.state.ResourceState
import br.com.android.seiji.presentation.viewModel.PostProductReservationViewModel
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_product_detail.*
import timber.log.Timber
import javax.inject.Inject


class ProductDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: PostProductReservationViewModel  by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(PostProductReservationViewModel::class.java)
    }

    private lateinit var extraProduct: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        AndroidInjection.inject(this)

        val product = intent.extras.getSerializable(EXTRA_PRODUCT) as Product
        extraProduct = product.copy()

        configureToolbar()
        configureFab()
        initViewModel()
        setProductDetail()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun configureToolbar() {
        toolbarProductDetail.title = extraProduct.category.descricao
        setSupportActionBar(toolbarProductDetail)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        toolbarLayout.title = extraProduct.nome
        toolbarLayout.setExpandedTitleColor(ResourcesCompat.getColor(resources, android.R.color.white, null))
        toolbarLayout.setContentScrimColor(ResourcesCompat.getColor(resources, R.color.colorPrimary, null))
        toolbarLayout.setStatusBarScrimColor(ResourcesCompat.getColor(resources, R.color.colorPrimaryDark, null))

        toolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedToolbarText)
        toolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedToolbarText)
    }

    private fun configureFab() {
        fabProductDetail.setOnClickListener {
            layoutProgressBarProductDetail.visible()
            viewModel.fetchPostProductReservation(extraProduct.id)
        }
    }

    private fun setProductDetail() {
//        textProductDetailName.text = extraProduct.nome
        textProductDetailDescription.text = fromHtml(extraProduct.descricao)

        Picasso.get()
            .load(extraProduct.urlImagem)
            .into(imageProductDetail)

        textProductDetailPreviousPrice.text = getString(
            R.string.product_previous_price,
            extraProduct.precoDe.toCurrency()
        )
        textProductDetailPreviousPrice.paintFlags = textProductDetailPreviousPrice.paintFlags or
                Paint.STRIKE_THRU_TEXT_FLAG

        textProductDetailCurrentPrice.text = getString(
            R.string.product_current_price,
            extraProduct.precoPor.toCurrency()
        )
    }

    private fun fromHtml(html: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }

    private fun initViewModel() {
        viewModel.getPostProductReservation().observe(this,
            Observer<Resource<Any>> {
                it?.let {
                    handlePostProductReservationDataState(it)
                }
            })
    }

    private fun handlePostProductReservationDataState(resource: Resource<Any>) {
        Timber.i(resource.status.toString())
        when (resource.status) {
            ResourceState.LOADING -> {
            }
            ResourceState.SUCCESS -> {
                showDialog()
            }
            ResourceState.ERROR -> {
                layoutProgressBarProductDetail.gone()
                Timber.e(resource.message)
            }
        }
    }

    private fun showDialog() {
        val alertDialog = AlertDialog.Builder(this, R.style.AlertDialogCustom)
        alertDialog.setTitle(R.string.product_reservation_success)
        alertDialog.setPositiveButton(android.R.string.ok) { alert, _ ->
            alert.dismiss()
        }.setOnDismissListener {
            layoutProgressBarProductDetail.gone()
        }.create().show()
    }

    companion object {
        const val EXTRA_PRODUCT = "EXTRA_PRODUCT"
    }
}
