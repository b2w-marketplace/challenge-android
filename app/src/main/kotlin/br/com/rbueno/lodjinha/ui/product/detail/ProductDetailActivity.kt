package br.com.rbueno.lodjinha.ui.product.detail

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.navArgs
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.model.Product
import br.com.rbueno.lodjinha.util.ImageLoader
import br.com.rbueno.lodjinha.util.observe
import br.com.rbueno.lodjinha.util.toHtmlSpanned
import br.com.rbueno.lodjinha.util.toMoneyDisplay
import br.com.rbueno.lodjinha.viewmodel.ProductViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

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

    private val navArgs by navArgs<ProductDetailActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        initViewModel()
        configToolbar()
    }

    private fun configToolbar() {
        toolbar.title = navArgs.toolbarTitle
    }

    private fun initViewModel() {
        viewModel.apply {
            loadingLiveData.observe(this@ProductDetailActivity) {

            }

            errorLiveData.observe(this@ProductDetailActivity) {

            }

            productLiveData.observe(this@ProductDetailActivity) {
                bindProduct(it)
                clearProductLiveData()
            }
        }.loadProduct(navArgs.productId)
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