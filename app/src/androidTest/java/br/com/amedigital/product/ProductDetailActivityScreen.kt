package br.com.amedigital.product

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.isDialog
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.amedigital.R
import br.com.amedigital.model.Category
import br.com.amedigital.model.Product
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
open class ProductDetailActivityScreen {

    @Rule
    @JvmField
    val mActivityRule: ActivityTestRule<ProductDetailActivity> =
        object : ActivityTestRule<ProductDetailActivity>(ProductDetailActivity::class.java) {
            override fun getActivityIntent(): Intent {

                val category = Category()
                with(category) {
                    id = 1
                    description = "Games"
                    urlImage = "http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png"
                }

                val product = Product()
                with(product) {
                    id = 7
                    name = "Fifa 17"
                    description = "Mussum Ipsum, cacilds vidis litro abertis. Atirei o pau no gatis, per gatis num morreus. Não sou faixa preta cumpadi, sou preto inteiris, inteiris. Praesent malesuada urna nisi, quis volutpat erat hendrerit non. Nam vulputate dapibus. Diuretics paradis num copo é motivis de denguis.<br/><br/>Copo furadis é disculpa de bebadis, arcu quam euismod magna. Casamentiss faiz malandris se pirulitá. Vehicula non. Ut sed ex eros. Vivamus sit amet nibh non tellus tristique interdum. in elementis mé pra quem é amistosis quis leo.<br/><br/>A ordem dos tratores não altera o pão duris Delegadis gente finis, bibendum egestas augue arcu ut est. Mé faiz elementum girarzis, nisi eros vermeio. Si u mundo tá muito paradis? Toma um mé que o mundo vai girarzis!"
                    urlImage = "https://images-submarino.b2w.io/produtos/01/00/item/128926/4/128926443_1GG.png"
                    priceOf = 299.0
                    priceFor = 119.9899999999999948840923025272786617279052734375
                    this.category = category
                }

                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                val result = Intent(targetContext, ProductDetailActivity::class.java)
                result.putExtra("product", product)
                return result
            }

        }

    @Test
    fun showCategoryName() {
        onView(withId(R.id.textViewCategoryName)).check(matches(withText("Games")))
        Thread.sleep(1000)
    }

    @Test
    fun clickOnItemReserveProduct() {
        onView(withId(R.id.floatingButtonReserve)).perform(click())
        Thread.sleep(2000)
        onView(withText("ok")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click())
    }
}