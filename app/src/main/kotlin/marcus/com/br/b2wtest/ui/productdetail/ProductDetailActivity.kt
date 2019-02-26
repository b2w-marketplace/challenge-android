package marcus.com.br.b2wtest.ui.productdetail

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*
import marcus.com.br.b2wtest.R
import marcus.com.br.b2wtest.helper.toBRL
import marcus.com.br.b2wtest.model.data.ProductData
import marcus.com.br.b2wtest.ui.BaseActivity


class ProductDetailActivity : BaseActivity() {

    private lateinit var productData: ProductData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(marcus.com.br.b2wtest.R.layout.activity_product_detail)
        init()
    }

    private fun init() {
        productData = intent.getParcelableExtra("productData")
        setupToolbar()
        setLayout()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        Picasso.get()
            .load(productData.urlImage)
            .into(activityProductDetailImage)

        supportActionBar?.title = productData.name
    }

    private fun setLayout() {
        productDetailActivityDesc.text = fromHtml(productData.description)
        activityProductDetailFrom.text = getString(R.string.product_from_price, productData.fromPrice.toBRL())
        activityProductDetailTo.text = getString(R.string.product_to_price, productData.toPrice.toBRL())
    }

    private fun fromHtml(html: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }
}