package br.com.rbueno.lodjinha.ui.product.detail

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.model.Product
import br.com.rbueno.lodjinha.ui.home.PRODUCT_ID_ARG
import br.com.rbueno.lodjinha.ui.home.TOOLBAR_TITLE_ARG
import br.com.rbueno.lodjinha.util.*
import br.com.rbueno.lodjinha.viewmodel.ProductViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.android.AndroidInjection
import javax.inject.Inject

private const val PRODUCT_FLIPPER_POSITION = 0
private const val LOADING_FLIPPER_POSITION = 1
private const val ERROR_FLIPPER_POSITION = 2

class ProductDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ProductViewModel.ProductViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(ProductViewModel::class.java) }

    private val imageProduct by lazy { findViewById<ImageView>(R.id.image_product) }
    private val textProductName by lazy { findViewById<TextView>(R.id.text_product_name) }
    private val textOldPrice by lazy { findViewById<TextView>(R.id.text_old_price) }
    private val textNewPrice by lazy { findViewById<TextView>(R.id.text_new_price) }
    private val textProductDescription by lazy { findViewById<TextView>(R.id.text_product_description) }
    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar_product) }
    private val floatingReserveProduct by lazy { findViewById<FloatingActionButton>(R.id.floating_reserve_product) }
    private val flipperProduct by lazy { findViewById<ViewFlipper>(R.id.flipper_product) }
    private val buttonTryAgain by lazy { findViewById<Button>(R.id.button_try_again) }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        initViewModel()
        configToolbar()
        configReserveListener()
        configTryAgainButton()
    }

    private fun configTryAgainButton() {
        buttonTryAgain.setOnClickListener {
            viewModel.loadProduct(intent.getIntExtra(PRODUCT_ID_ARG, 1))
        }
    }

    private fun configReserveListener() {
        floatingReserveProduct.setOnClickListener {
            viewModel.reserveProduct(intent.getIntExtra(PRODUCT_ID_ARG, 1))
        }
    }

    private fun configToolbar() {
        toolbar.title = intent.getStringExtra(TOOLBAR_TITLE_ARG)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true);
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initViewModel() {
        viewModel.apply {
            loadingLiveData.observe(this@ProductDetailActivity) {
                flipperProduct.displayedChild = if (it) LOADING_FLIPPER_POSITION else PRODUCT_FLIPPER_POSITION
            }

            errorLiveData.observe(this@ProductDetailActivity) {
                flipperProduct.displayedChild = ERROR_FLIPPER_POSITION
            }

            productLiveData.observe(this@ProductDetailActivity) {
                flipperProduct.displayedChild = PRODUCT_FLIPPER_POSITION
                bindProduct(it)
                clearProductLiveData()
            }

            reserveProductLiveData.observe(this@ProductDetailActivity) {
                showSuccessAlert()
                clearReserveProductLiveData()
            }

            reservationErrorLiveData.observe(this@ProductDetailActivity) {
                shoErrorReservation()
            }
        }.loadProduct(intent.getIntExtra(PRODUCT_ID_ARG, 1))
    }

    private fun shoErrorReservation() {
        showAlert(R.string.error_default_message, R.string.error_try_again) { _, _ ->
           viewModel.reserveProduct(intent.getIntExtra(PRODUCT_ID_ARG, 1))
        }
    }

    private fun showSuccessAlert() {
        showAlert(R.string.reserve_product_success, android.R.string.ok) { _, _ ->
            onBackPressed()
        }
    }

    private fun bindProduct(product: Product) {
        with(product) {
            ImageLoader.loadImage(urlImage, imageProduct)
            textProductName.text = name

            val spanOldPrice = SpannableString(
                String.format(
                    getString(R.string.old_price_format),
                    oldPrice.toMoneyDisplay()
                )
            )
            spanOldPrice.setSpan(StrikethroughSpan(), 0, spanOldPrice.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            textOldPrice.text = spanOldPrice

            textNewPrice.text = String.format(getString(R.string.new_price_format), newPrice.toMoneyDisplay())
            textProductDescription.text = description.toHtmlSpanned()
        }

    }
}