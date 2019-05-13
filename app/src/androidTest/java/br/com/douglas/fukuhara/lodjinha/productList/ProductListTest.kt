package br.com.douglas.fukuhara.lodjinha.productList

import android.content.Context
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import br.com.douglas.fukuhara.lodjinha.R
import br.com.douglas.fukuhara.lodjinha.network.RestApi.PRODUTO
import br.com.douglas.fukuhara.lodjinha.network.RetrofitImpl
import br.com.douglas.fukuhara.lodjinha.utils.InstrumentedTestUtils
import br.com.douglas.fukuhara.lodjinha.utils.InstrumentedTestUtils.withRecyclerView
import br.com.douglas.fukuhara.lodjinha.view.ProductsListByCategoryActivity
import junit.framework.Assert.assertEquals
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ProductListTest {

    private val CATEGORY_NAME = "Categoria Inicial"
    private val CATEGORY_ID: Int  = 1

    @get:Rule
    var mActivityRule = ActivityTestRule<ProductsListByCategoryActivity>(ProductsListByCategoryActivity::class.java, false, false)

    lateinit var mockServer : MockWebServer

    lateinit var mIntent: Intent

    lateinit var mTestContext: Context
    lateinit var mAppcontext: Context

    @Before
    fun setUp() {
        mAppcontext = InstrumentationRegistry.getTargetContext()
        mTestContext = InstrumentationRegistry.getContext()

        mockServer = MockWebServer()
        mockServer.start()
        RetrofitImpl.getInstance().setBaseUrl(mockServer.url("/").toString())
        mockServer.setDispatcher(object: Dispatcher() {
            override fun dispatch(request: RecordedRequest?): MockResponse {
                val path = request?.path

                return when (path) {
                    "$PRODUTO?offset=0&limit=20&categoriaId=1" ->
                        MockResponse().setBody(
                                InstrumentedTestUtils.getStringFromJson(mTestContext, "product_list_20_items.json")
                        )
                    else -> MockResponse().setResponseCode(404)
                }
            }
        })

        mIntent = ProductsListByCategoryActivity.newIntent(mAppcontext, CATEGORY_ID, CATEGORY_NAME)
    }

    @Test
    fun whenAllTwelveItemsAreRetrievedThemAllDataShouldBeDisplayed() {
        mActivityRule.launchActivity(mIntent)

        val activity = mActivityRule.activity
        val recyclerView: RecyclerView = activity.findViewById(R.id.rv_list_of_products_by_category)

        // Checks if the RecyclerView is holding all the 20 product items
        assertEquals(20, recyclerView.adapter?.itemCount)

        // Check if the first item is properly displayed
        onView(withRecyclerView(R.id.rv_list_of_products_by_category)
                .atPositionOnView(0, R.id.tv_best_seller_list_prod_name))
                .check(matches(withText("Game Horizon Zero Down")))
        onView(withRecyclerView(R.id.rv_list_of_products_by_category)
                .atPositionOnView(0, R.id.tv_best_seller_list_prev_price))
                .check(matches(withText(" De: 299,00 ")))
        onView(withRecyclerView(R.id.rv_list_of_products_by_category)
                .atPositionOnView(0, R.id.tv_best_seller_list_final_price))
                .check(matches(withText("Por 119,99")))

        // Scroll to the last position of the Recycler View
        onView(withId(R.id.rv_list_of_products_by_category))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))

        // ... and check the last one as well
        onView(withRecyclerView(R.id.rv_list_of_products_by_category)
                .atPositionOnView(19, R.id.tv_best_seller_list_prod_name))
                .check(matches(withText("Ghost Recon")))
        onView(withRecyclerView(R.id.rv_list_of_products_by_category)
                .atPositionOnView(19, R.id.tv_best_seller_list_prev_price))
                .check(matches(withText(" De: 399,00 ")))
        onView(withRecyclerView(R.id.rv_list_of_products_by_category)
                .atPositionOnView(19, R.id.tv_best_seller_list_final_price))
                .check(matches(withText("Por 319,99")))
    }

    @Test
    fun whenClickingInAProductItemThenItShouldLoadProductsDetailScreen() {
        mActivityRule.launchActivity(mIntent)

        onView(withId(R.id.rv_list_of_products_by_category))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // In fact, this is not a good way to perform this validation
        // This workaround procedure checks if the RecyclerView from ProductsList does not exists and if the
        // ImageView container defined in Product Detail screen is visible
        onView(withId(R.id.rv_list_of_products_by_category))
                .check(ViewAssertions.doesNotExist())
        onView(withId(R.id.iv_product_image))
                .check(matches(isDisplayed()))
    }

    @After
    fun tearDown() {
        mActivityRule.finishActivity()
    }
}