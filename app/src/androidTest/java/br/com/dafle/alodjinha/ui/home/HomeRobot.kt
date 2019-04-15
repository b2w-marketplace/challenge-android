package br.com.dafle.alodjinha.ui.home

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import br.com.dafle.alodjinha.R
import br.com.dafle.alodjinha.matcher.hasItemCount
import br.com.dafle.alodjinha.matcher.hasItemCountViewPager
import br.com.dafle.alodjinha.model.*
import br.com.dafle.alodjinha.service.HomeService
import br.com.dafle.alodjinha.service.ProductService
import br.com.dafle.alodjinha.ui.main.MainActivity
import io.reactivex.Observable
import junit.framework.TestCase.*
import org.mockito.Mockito.`when`


fun itens(homeBusiness: HomeService, productBusinnes: ProductService, testRule: ActivityTestRule<MainActivity>,
          func: HomeRobot.() -> Unit) = HomeRobot(homeBusiness, productBusinnes, testRule).apply { func() }

class HomeRobot(private var homeBusiness: HomeService,
                private var productBusinnes: ProductService,
                private var testRule: ActivityTestRule<MainActivity>)  {

    private val intent = Intent().apply { }

    fun startActvityWithEmptyData() {
        `when`(homeBusiness.fetchBanner())
                .thenReturn(Observable.just(BannerResponse(listOf())))

        `when`(homeBusiness.fetchCategory())
                .thenReturn(Observable.just(CategoryResponse(listOf())))

        `when`(productBusinnes.fetchBestSeller())
                .thenReturn(Observable.just(ProductResponse(listOf(),0,34)))

        testRule.launchActivity(intent)
    }

    private fun mockBannerData() {
        `when`(homeBusiness.fetchBanner())
                .thenReturn(Observable.just(
                        BannerResponse(
                                listOf(
                                        Banner(1, "http://www.moovin.com.br/blog/wp-content/uploads/2018/04/banner.png", "http://www.moovin.com.br/blog/wp-content/uploads/2018/04/banner.png"),
                                        Banner(1, "http://www.moovin.com.br/blog/wp-content/uploads/2018/04/banner.png", "http://www.moovin.com.br/blog/wp-content/uploads/2018/04/banner.png"),
                                        Banner(1, "http://www.moovin.com.br/blog/wp-content/uploads/2018/04/banner.png", "http://www.moovin.com.br/blog/wp-content/uploads/2018/04/banner.png")
                                )
                        )
                ))
    }

    private fun mockCategoryData() {
        `when`(homeBusiness.fetchCategory())
                .thenReturn(Observable.just(
                        CategoryResponse(
                                listOf(
                                        Category("Description 1", 1, "https://www.walmart.com.br/categoria/eletrodomesticos/lavadoras-de-roupas/?fq=C:144/7953/7973/"),
                                        Category("Description 2", 2, "https://www.walmart.com.br/categoria/eletrodomesticos/lavadoras-de-roupas/?fq=C:144/7953/7973/"),
                                        Category("Description 3", 3, "https://www.walmart.com.br/categoria/eletrodomesticos/lavadoras-de-roupas/?fq=C:144/7953/7973/"),
                                        Category("Description 4", 4, "https://www.walmart.com.br/categoria/eletrodomesticos/lavadoras-de-roupas/?fq=C:144/7953/7973/"),
                                        Category("Description 5", 5, "https://www.walmart.com.br/categoria/eletrodomesticos/lavadoras-de-roupas/?fq=C:144/7953/7973/")
                                )
                        )
                ))
    }

    private fun mockBestSeller() {

        `when`(productBusinnes.fetchBestSeller())
                .thenReturn(Observable.just(
                        ProductResponse(
                                listOf(
                                        Product(Category("category description", 1, "http://qualquercoisa.com"), "product description", 1, "product_name", 9999.0, 7777.87, "url product"),
                                        Product(Category("category description another", 1, "http://qualquercoisa.com"), "product description", 1, "product_name", 399.0, 277.87, "url product"),
                                        Product(Category("category description another", 1, "http://qualquercoisa.com"), "product description", 1, "product_name", 399.0, 277.87, "url product"),
                                        Product(Category("category description another", 1, "http://qualquercoisa.com"), "product description", 1, "product_name", 399.0, 277.87, "url product"),
                                        Product(Category("category description another", 1, "http://qualquercoisa.com"), "product description", 1, "product_name", 399.0, 277.87, "url product"),
                                        Product(Category("category description another", 1, "http://qualquercoisa.com"), "product description", 1, "product_name", 399.0, 277.87, "url product"),
                                        Product(Category("category description another last", 1, "http://qualquercoisa.com"), "product description", 1, "product_name", 399.0, 277.87, "url product")
                                ), 0, 75)
                ))

    }

    fun startActivityWithBannerData() {
        mockBannerData()

        `when`(homeBusiness.fetchCategory())
                .thenReturn(Observable.just(CategoryResponse(listOf())))

        `when`(productBusinnes.fetchBestSeller())
                .thenReturn(Observable.just(ProductResponse(listOf(),0,34)))

        testRule.launchActivity(intent)
    }

    fun startActivityWithBannerAndCategoryData() {

        mockBannerData()
        mockCategoryData()

        `when`(productBusinnes.fetchBestSeller())
                .thenReturn(Observable.just(ProductResponse(listOf(),0,34)))

        testRule.launchActivity(intent)
    }

    fun startActivityWithError() {
        `when`(homeBusiness.fetchBanner())
                .thenReturn(Observable.error(Throwable("any string")))

        `when`(homeBusiness.fetchCategory())
                .thenReturn(Observable.error(Throwable("any string")))

        `when`(productBusinnes.fetchBestSeller())
                .thenReturn(Observable.error(Throwable("any string")))

        testRule.launchActivity(intent)
    }

    fun startActivityWithFullData() {
        mockBannerData()
        mockCategoryData()
        mockBestSeller()
        testRule.launchActivity(intent)
    }

    fun checkEmptyStateAndList() {
        onView(withId(R.id.categoriesRecyclerView)).check(matches(hasItemCount(0)))
        onView(withId(R.id.bestSellerRecyclerView)).check(matches(hasItemCount(0)))
        val homeFragment: HomeFragment = testRule.activity.supportFragmentManager.findFragmentById(R.id.container) as HomeFragment
        assertEquals(0, homeFragment.viewModel.banners.value?.size)
    }

    fun checkBannerData() {
        onView(withId(R.id.viewPager)).check(matches(hasItemCountViewPager(3)))
        onView(withId(R.id.categoriesRecyclerView)).check(matches(hasItemCount(0)))
        onView(withId(R.id.bestSellerRecyclerView)).check(matches(hasItemCount(0)))
        val homeFragment: HomeFragment = testRule.activity.supportFragmentManager.findFragmentById(R.id.container) as HomeFragment
        assertEquals(3, homeFragment.viewModel.banners.value?.size)
        assertEquals(0, homeFragment.viewModel.categories.value?.size)
        assertEquals(0, homeFragment.viewModel.bestSellers.value?.size)

    }

    fun checkBannerAndCategoryData() {
        onView(withId(R.id.viewPager)).check(matches(hasItemCountViewPager(3)))
        onView(withId(R.id.categoriesRecyclerView)).check(matches(hasItemCount(5)))
        onView(withId(R.id.bestSellerRecyclerView)).check(matches(hasItemCount(0)))
        val homeFragment: HomeFragment = testRule.activity.supportFragmentManager.findFragmentById(R.id.container) as HomeFragment
        assertEquals(3, homeFragment.viewModel.banners.value?.size)
        assertEquals(5, homeFragment.viewModel.categories.value?.size)
        assertEquals(0, homeFragment.viewModel.bestSellers.value?.size)
    }

    fun checkFullData() {
        onView(withId(R.id.viewPager)).check(matches(hasItemCountViewPager(3)))
        onView(withId(R.id.categoriesRecyclerView)).check(matches(hasItemCount(5)))
        onView(withId(R.id.bestSellerRecyclerView)).check(matches(hasItemCount(7)))
        val homeFragment: HomeFragment = testRule.activity.supportFragmentManager.findFragmentById(R.id.container) as HomeFragment
        assertEquals(3, homeFragment.viewModel.banners.value?.size)
        assertEquals(5, homeFragment.viewModel.categories.value?.size)
        assertEquals(7, homeFragment.viewModel.bestSellers.value?.size)

        Thread.sleep(1000)

        onView(withContentDescription(R.string.navigation_drawer_open))
                .perform(click())

        onView(withContentDescription(R.string.navigation_drawer_close))
                .perform(click())
    }

    fun checkHowShouldHehaveInCaseOfAnError() {
        onView(withText("OK")).perform(click())
        onView(withId(R.id.categoriesRecyclerView)).check(matches(hasItemCount(0)))
        onView(withId(R.id.bestSellerRecyclerView)).check(matches(hasItemCount(0)))
        val homeFragment: HomeFragment = testRule.activity.supportFragmentManager.findFragmentById(R.id.container) as HomeFragment
        assertNull(homeFragment.viewModel.banners.value)
        assertNull(homeFragment.viewModel.categories.value)
        assertNull(homeFragment.viewModel.bestSellers.value)
        assertNotNull(homeFragment.viewModel.errorState.value)
    }
}