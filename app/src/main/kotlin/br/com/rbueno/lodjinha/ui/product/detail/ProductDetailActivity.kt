package br.com.rbueno.lodjinha.ui.product.detail

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.model.Product
import br.com.rbueno.lodjinha.util.ImageLoader
import br.com.rbueno.lodjinha.util.toHtmlSpanned
import br.com.rbueno.lodjinha.util.toMoneyDisplay
import com.google.gson.GsonBuilder

class ProductDetailActivity : AppCompatActivity() {

    private val imageProduct by lazy { findViewById<ImageView>(R.id.image_product) }
    private val textProductName by lazy { findViewById<TextView>(R.id.text_product_name) }
    private val textOldPrice by lazy { findViewById<TextView>(R.id.text_old_price) }
    private val textNewPrice by lazy { findViewById<TextView>(R.id.text_new_price) }
    private val textProductDescription by lazy { findViewById<TextView>(R.id.text_product_description) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        bindProduct(mockProduct())

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

    private fun mockProduct(): Product {
        val productJson = "{\n" +
                "  \"id\": 1,\n" +
                "  \"nome\": \"Game Horizon Zero Down\",\n" +
                "  \"urlImagem\": \"https://images-submarino.b2w.io/produtos/01/00/item/130836/1/130836199P1.jpg\",\n" +
                "  \"descricao\": \"Mussum Ipsum, cacilds vidis litro abertis. Atirei o pau no gatis, per gatis num morreus. Não sou faixa preta cumpadi, sou preto inteiris, inteiris. Praesent malesuada urna nisi, quis volutpat erat hendrerit non. Nam vulputate dapibus. Diuretics paradis num copo é motivis de denguis.<br/><br/>Copo furadis é disculpa de bebadis, arcu quam euismod magna. Casamentiss faiz malandris se pirulitá. Vehicula non. Ut sed ex eros. Vivamus sit amet nibh non tellus tristique interdum. in elementis mé pra quem é amistosis quis leo.<br/><br/>A ordem dos tratores não altera o pão duris Delegadis gente finis, bibendum egestas augue arcu ut est. Mé faiz elementum girarzis, nisi eros vermeio. Si u mundo tá muito paradis? Toma um mé que o mundo vai girarzis!\",\n" +
                "  \"precoDe\": 299,\n" +
                "  \"precoPor\": 119.99,\n" +
                "  \"categoria\": {\n" +
                "    \"id\": 1,\n" +
                "    \"descricao\": \"Games\",\n" +
                "    \"urlImagem\": \"http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png\"\n" +
                "  }\n" +
                "}"

        return GsonBuilder().create().fromJson(productJson, Product::class.java)
    }
}