package br.com.douglas.fukuhara.lodjinha.productDetail

import android.content.Context
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.ImageView
import android.widget.TextView
import br.com.douglas.fukuhara.lodjinha.R
import br.com.douglas.fukuhara.lodjinha.network.RestApi.PRODUTO
import br.com.douglas.fukuhara.lodjinha.network.RetrofitImpl
import br.com.douglas.fukuhara.lodjinha.network.vo.ProductDataVo
import br.com.douglas.fukuhara.lodjinha.utils.InstrumentedTestUtils
import br.com.douglas.fukuhara.lodjinha.utils.InstrumentedTestUtils.getStringFromJson
import br.com.douglas.fukuhara.lodjinha.view.ProductDetailActivity
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.Matchers.notNullValue
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductDetailTest {

    @get:Rule
    var mActivityRule = ActivityTestRule<ProductDetailActivity>(ProductDetailActivity::class.java, false, false)

    lateinit var mockServer : MockWebServer
    lateinit var mAppContext: Context
    lateinit var mTestContext: Context
    lateinit var mProductData: ProductDataVo

    lateinit var mIntentId1: Intent
    lateinit var mIntentId2: Intent
    lateinit var mIntentId3: Intent
    lateinit var mIntentId4: Intent

    @Before
    fun setUp() {
        mAppContext = InstrumentationRegistry.getTargetContext()
        mTestContext = InstrumentationRegistry.getContext()

        // SetUp of a Product that will receive a ResultCode 200 and Success in reservation
        mProductData = InstrumentedTestUtils.getObjFromJson(mTestContext, "product_detail_id_01.json", ProductDataVo::class.java)
        mIntentId1 = ProductDetailActivity.newIntent(mAppContext, mProductData)

        mockServer = MockWebServer()
        mockServer.start()
        RetrofitImpl.getInstance().setBaseUrl(mockServer.url("/").toString())
        mockServer.setDispatcher(object : Dispatcher() {
            override fun dispatch(request: RecordedRequest?): MockResponse {
                val path = request?.path

                return when (path) {
                    "$PRODUTO/1" -> MockResponse().setBody(getStringFromJson(mTestContext, "reservation_success_response.json"))
                    "$PRODUTO/2" -> MockResponse().setBody(getStringFromJson(mTestContext, "reservation_failed_with_message.json"))
                    "$PRODUTO/3" -> MockResponse().setBody(getStringFromJson(mTestContext, "reservation_failed_without_message.json"))
                    else -> MockResponse().setResponseCode(404)
                }
            }
        })
    }

    @Test
    fun whenOpeningProductDetailsThenAllInformationMustBeProperlyDisplayed() {
        mActivityRule.launchActivity(mIntentId1)

        val activityUnderTest = mActivityRule.activity

        var mIvProductImage: ImageView? = null
        var mTvProductTitle: TextView? = null
        var mTvProductPrevPrice: TextView? = null
        var mTvProductFinalPrice: TextView? = null
        var mTvProductDesc: TextView? = null

        with(activityUnderTest) {
            mIvProductImage = findViewById(R.id.iv_product_image)
            mTvProductTitle = findViewById(R.id.tv_product_title)
            mTvProductPrevPrice = findViewById(R.id.tv_product_prev_price)
            mTvProductFinalPrice = findViewById(R.id.tv_product_final_price)
            mTvProductDesc = findViewById(R.id.tv_product_description)
        }

        assertThat(mIvProductImage, notNullValue())
        assertEquals(mTvProductTitle?.text.toString(),"Game Horizon Zero Down")
        assertEquals(mTvProductPrevPrice?.text.toString(), " De: 299,00 ")
        assertEquals(mTvProductFinalPrice?.text.toString(), "Por 119,99")
        assertEquals(mTvProductDesc?.text.toString(), "Mussum Ipsum, cacilds vidis litro abertis. Atirei o pau no gatis, per gatis num morreus. Não sou faixa preta cumpadi, sou preto inteiris, inteiris. Praesent malesuada urna nisi, quis volutpat erat hendrerit non. Nam vulputate dapibus. Diuretics paradis num copo é motivis de denguis.\n\nCopo furadis é disculpa de bebadis, arcu quam euismod magna. Casamentiss faiz malandris se pirulitá. Vehicula non. Ut sed ex eros. Vivamus sit amet nibh non tellus tristique interdum. in elementis mé pra quem é amistosis quis leo.\n\nA ordem dos tratores não altera o pão duris Delegadis gente finis, bibendum egestas augue arcu ut est. Mé faiz elementum girarzis, nisi eros vermeio. Si u mundo tá muito paradis? Toma um mé que o mundo vai girarzis!")
    }

    @Test
    fun WhenClickingOnReserveButtonAndPositiveResponseIsReceivedThenInformItToUser() {
        mActivityRule.launchActivity(mIntentId1)
        // Click on the Reserve Button
        onView(withId(R.id.fab_product_reserve)).perform(click())

        // Verify if the message of successful reservation is displayed
        onView(withId(android.R.id.message)).check(matches(withText("Produto reservado com sucesso")))

        // Click OK on AlertDialog Button
        onView(withId(android.R.id.button1)).perform(click())

        // Verify if the dialog is no longer visible
        onView(withId(android.R.id.message)).check(doesNotExist())
    }

    @Test
    fun WhenClickingOnReserveButtonAndNegativeResponseIsReceivedThenInformItToUser() {
        // SetUp of a Product that will receive a ResultCode 200 and Failure in reservation (with error message from server)
        mProductData = InstrumentedTestUtils.getObjFromJson(mTestContext, "product_detail_id_02.json", ProductDataVo::class.java)
        mIntentId2 = ProductDetailActivity.newIntent(mAppContext, mProductData)

        mActivityRule.launchActivity(mIntentId2)
        // Click on the Reserve Button
        onView(withId(R.id.fab_product_reserve)).perform(click())

        // Verify if the message of failure reservation from server is displayed
        onView(withId(android.R.id.message)).check(matches(withText("Produto não encontrado")))

        // Click OK on AlertDialog Button
        onView(withId(android.R.id.button1)).perform(click())

        // Verify if the dialog is no longer visible
        onView(withId(android.R.id.message)).check(doesNotExist())
    }

    @Test
    fun WhenClickingOnReserveButtonAndNegativeResponseIsWithoutMessageReceivedThenInformItToUser() {
        // SetUp of a Product that will receive a ResultCode 200 and Failure in reservation (without error message from server)
        mProductData = InstrumentedTestUtils.getObjFromJson(mTestContext, "product_detail_id_03.json", ProductDataVo::class.java)
        mIntentId3 = ProductDetailActivity.newIntent(mAppContext, mProductData)

        mActivityRule.launchActivity(mIntentId3)
        // Click on the Reserve Button
        onView(withId(R.id.fab_product_reserve)).perform(click())

        // Verify if a generic error message of failure reservation is displayed
        onView(withId(android.R.id.message)).check(matches(withText("Falha ao reservar o produto")))

        // Click OK on AlertDialog Button
        onView(withId(android.R.id.button1)).perform(click())

        // Verify if the dialog is no longer visible
        onView(withId(android.R.id.message)).check(doesNotExist())
    }

    @Test
    fun WhenClickingOnReserveButtonAndReceiveHttpErrorThenInformItToUser() {
        // SetUp of a Product that will receive a HttpError code 404
        mProductData = InstrumentedTestUtils.getObjFromJson(mTestContext, "product_detail_id_04.json", ProductDataVo::class.java)
        mIntentId4 = ProductDetailActivity.newIntent(mAppContext, mProductData)

        mActivityRule.launchActivity(mIntentId4)
        // Click on the Reserve Button
        onView(withId(R.id.fab_product_reserve)).perform(click())

        // Verify if a generic error message of failure reservation is displayed
        onView(withId(android.R.id.message)).check(matches(withText("HTTP 404 Client Error")))

        // Click OK on AlertDialog Button
        onView(withId(android.R.id.button1)).perform(click())

        // Verify if the dialog is no longer visible
        onView(withId(android.R.id.message)).check(doesNotExist())
    }

    @After
    fun tearDown() {
        mActivityRule.finishActivity()
    }
}