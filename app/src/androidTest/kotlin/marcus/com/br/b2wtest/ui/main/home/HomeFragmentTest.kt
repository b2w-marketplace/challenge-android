package marcus.com.br.b2wtest.ui.main.home

import android.content.Intent
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import io.mockk.mockk
import marcus.com.br.b2wtest.BaseTest
import marcus.com.br.b2wtest.model.service.BannerService
import marcus.com.br.b2wtest.model.service.CategoryService
import marcus.com.br.b2wtest.model.service.ProductService
import marcus.com.br.b2wtest.ui.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest : BaseTest() {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java, true, false)

    private fun robots(func: HomeRobots.() -> Unit) = HomeRobots(rule, server).apply(func)

    private val intent = Intent()

    private lateinit var bannerService: BannerService
    private lateinit var categoryService: CategoryService
    private lateinit var producrService: ProductService

    @Before
    fun before() {
        super.setup()
        bannerService = mockk()
        categoryService = mockk()
        producrService = mockk()
    }

    @Test
    fun validateIfHomeLoadSuccess() {
        robots {
            mockResponse()

            initActivity(intent)
            validateBanner(2)
            validateCategory(5)
            validateBestSeller(7)
        }
    }
}