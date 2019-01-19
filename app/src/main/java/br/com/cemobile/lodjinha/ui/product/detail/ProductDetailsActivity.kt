package br.com.cemobile.lodjinha.ui.product.detail

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.test.espresso.IdlingResource
import android.support.v4.text.HtmlCompat.fromHtml
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.MenuItem
import br.com.cemobile.domain.model.Product
import br.com.cemobile.domain.model.Status
import br.com.cemobile.lodjinha.R
import br.com.cemobile.lodjinha.databinding.ActivityProductDetailsBinding
import br.com.cemobile.lodjinha.extensions.formatToBrazilianCurrency
import br.com.cemobile.lodjinha.extensions.setTextWithStrikeThrough
import br.com.cemobile.lodjinha.ui.core.DialogManager
import br.com.cemobile.lodjinha.util.AnimationUtil
import br.com.cemobile.lodjinha.util.EXTRA_PRODUCT
import br.com.cemobile.lodjinha.util.image.ImageDownloader
import br.com.cemobile.lodjinha.util.testing.EspressoIdlingResource
import kotlinx.android.synthetic.main.activity_product_details.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class ProductDetailsActivity : AppCompatActivity() {

    private val viewModel by viewModel<ProductDetailsViewModel>()
    private val imageDownloader: ImageDownloader by inject()
    private val product by lazy { intent.getParcelableExtra<Product>(EXTRA_PRODUCT) }
    private lateinit var binding: ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_details)
        binding.viewModel = viewModel
        setupViews()
        bindingObserver()
        binding.executePendingBindings()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupViews() {
        setupCollapsingToolbar(product.name, product.imageUrl)
        setupToolbar(product.name)
        setupFab()

        binding.fromProductPriceText.setTextWithStrikeThrough(
            getString(R.string.from_price, product.fromPrice.formatToBrazilianCurrency())
        )
        binding.byProductPriceText.text = getString(R.string.by_price, product.byPrice.formatToBrazilianCurrency())
        binding.productNameText.text = product.name
        binding.productDescriptionText.text = fromHtml(product.description, Html.FROM_HTML_MODE_LEGACY)

    }

    private fun setupCollapsingToolbar(title: String, imageUrl: String) {
        collapsingToolbar.title = title
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.AppTheme_ExpandedAppBar)
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.AppTheme_CollapsedAppBar)

        productNameInToolbarText.text = title
        imageDownloader.loadUrlWithTransition(this, imageUrl, R.drawable.no_picture, productImage)
    }

    private fun setupToolbar(title: String) {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.title = title
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupFab() {
        fab.setOnClickListener {
            AnimationUtil.pulse(it)
            viewModel.reserveProduct(product.id)
        }
    }

    private fun bindingObserver() {
        // connect observers with related live data
        viewModel.getResourceLiveData().observe(this, Observer { resources ->
            when (resources?.status) {
                Status.SUCCESS -> showReservationStatus(resources.data)
                else -> Timber.e("Status inválido!")
            }

            binding.resource = resources
        })
    }

    private fun showReservationStatus(reservationSuccess: Boolean?) {
        val messageId = reservationSuccess?.let {
            if (it) R.string.product_reserved_with_success else R.string.error_reservation
        } ?: R.string.error_reservation

        DialogManager.showDialog(this, getString(messageId))
    }

    @VisibleForTesting
    fun getCountingIdlingResource(): IdlingResource = EspressoIdlingResource.idlingResource

    companion object {
        fun launchIntent(from: Context, product: Product) =
            Intent(from, ProductDetailsActivity::class.java).apply {
                putExtra(EXTRA_PRODUCT, product)
            }
    }

}