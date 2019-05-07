package br.com.amedigital

import br.com.amedigital.banner.BannerContract
import br.com.amedigital.banner.BannerPresenter
import br.com.amedigital.category.CategoryContract
import br.com.amedigital.category.CategoryPresenter
import br.com.amedigital.model.Banner
import br.com.amedigital.model.Category
import br.com.amedigital.model.Product
import br.com.amedigital.network.model.BodyRequestBanner
import br.com.amedigital.network.model.BodyRequestCategory
import br.com.amedigital.network.model.BodyRequestProduct
import br.com.amedigital.product.ProductContract
import br.com.amedigital.product.ProductPresenter
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityTest {

    @Mock
    lateinit var viewBanner: BannerContract.View
    @Mock
    lateinit var viewCategory: CategoryContract.View
    @Mock
    lateinit var viewMoreSales: ProductContract.View

    @Mock
    lateinit var callBanner: Call<BodyRequestBanner>
    @Mock
    lateinit var callCategory: Call<BodyRequestCategory>
    @Mock
    lateinit var callProduct: Call<BodyRequestProduct>

    private lateinit var bannerPresenter: BannerPresenter
    private lateinit var categoryPresenter: CategoryPresenter
    private lateinit var productPresenter: ProductPresenter

    @Captor
    var callbackArgumentCaptorBanner: ArgumentCaptor<Callback<BodyRequestBanner>>? = null
    private var bodyRequestBanner: BodyRequestBanner? = null
    private var responseBanner: Response<BodyRequestBanner>? = null

    @Captor
    var callbackArgumentCaptorCategory: ArgumentCaptor<Callback<BodyRequestCategory>>? = null
    private var bodyRequestCategory: BodyRequestCategory? = null
    private var responseCategory: Response<BodyRequestCategory>? = null

    @Captor
    var callbackArgumentCaptorProduct: ArgumentCaptor<Callback<BodyRequestProduct>>? = null
    private var bodyRequestProduct: BodyRequestProduct? = null
    private var responseProduct: Response<BodyRequestProduct>? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        bannerPresenter = BannerPresenter(callBanner, viewBanner)
        categoryPresenter = CategoryPresenter(callCategory, viewCategory)
        productPresenter = ProductPresenter()
    }

    @Test
    fun showBanners() {

        val banner = Banner()
        with(banner) {
            id = 1
            linkUrl = "https://images-submarino.b2w.io/spacey/2017/02/06/MainTop_GAMES_FEV17.png"
            urlImage = "https://images-submarino.b2w.io/spacey/2017/02/06/MainTop_GAMES_FEV17.png"
        }

        val banners = ArrayList<Banner>()
        banners.add(banner)
        bodyRequestBanner = BodyRequestBanner(banners)
        responseBanner = Response.success(bodyRequestBanner)

        callbackArgumentCaptorBanner?.apply {
            bannerPresenter.loadBanners()
            verify(callBanner).enqueue(capture())
            responseBanner?.apply {
                value?.onResponse(callBanner, this)
            }

            verify(viewBanner)?.setProgressIndicatorBanner(false)
            responseBanner?.body()?.let {
                verify(viewBanner)?.showBanners(it.data)
                Assert.assertEquals(it.data, banners)
            }
        }
    }

    @Test
    fun showErrorBanner() {
        val mediaType = MediaType.parse("application/json; charset=utf-8")
        val body = ResponseBody.create(mediaType, "Unauthorized")

        responseBanner = Response.error(401, body)
        bannerPresenter.loadBanners()

        verify(callBanner)?.enqueue(callbackArgumentCaptorBanner?.capture())
        callbackArgumentCaptorBanner?.value?.onFailure(callBanner, Throwable("Unauthorized"))

        verify(viewBanner)?.setProgressIndicatorBanner(false)
        verify(viewBanner)?.showErrorBanner("Unauthorized")
    }

    @Test
    fun showCategories() {
        val category = Category()
        with(category) {
            id = 1
            description = "Games"
            urlImage = "http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png"
        }

        val categories = ArrayList<Category>()
        categories.add(category)

        bodyRequestCategory = BodyRequestCategory(categories)
        responseCategory = Response.success(bodyRequestCategory)

        callbackArgumentCaptorCategory?.apply {
            categoryPresenter.loadCategories()
            verify(callCategory).enqueue(capture())
            responseCategory?.apply {
                value?.onResponse(callCategory, this)
            }

            verify(viewCategory)?.setProgressIndicatorCategory(false)
            responseCategory?.body()?.let {
                verify(viewCategory)?.showCategories(it.data)
                Assert.assertEquals(it.data, categories)
            }
        }
    }

    @Test
    fun showErrorCategory() {
        val mediaType = MediaType.parse("application/json; charset=utf-8")
        val body = ResponseBody.create(mediaType, "Unauthorized")

        responseCategory = Response.error(401, body)
        categoryPresenter.loadCategories()

        verify(callCategory)?.enqueue(callbackArgumentCaptorCategory?.capture())
        callbackArgumentCaptorCategory?.value?.onFailure(callCategory, Throwable("Unauthorized"))

        verify(viewCategory)?.setProgressIndicatorCategory(false)
        verify(viewCategory)?.showErrorCategory("Unauthorized")
    }

    @Test
    fun showMoreSales() {
        val product = Product()
        with(product) {
            id = 7
            name = "Fifa 17"
            description = "Games"
            urlImage = "http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png"
            priceOf = 299.0
            priceFor = 119.9899999999999948840923025272786617279052734375
        }

        val products = ArrayList<Product>()
        products.add(product)

        bodyRequestProduct = BodyRequestProduct(products)
        responseProduct = Response.success(bodyRequestProduct)

        callbackArgumentCaptorProduct?.apply {
            productPresenter.loadProductsMoreSales(callProduct, viewMoreSales)
            verify(callProduct).enqueue(capture())
            responseProduct?.apply {
                value?.onResponse(callProduct, this)
            }

            verify(viewMoreSales)?.setProgressProductMore(false)
            responseProduct?.body()?.let {
                verify(viewMoreSales)?.showProductsMore(it.data)
                Assert.assertEquals(it.data, products)
            }
        }
    }

    @Test
    fun showErrorProduct() {
        val mediaType = MediaType.parse("application/json; charset=utf-8")
        val body = ResponseBody.create(mediaType, "Unauthorized")

        responseProduct = Response.error(401, body)
        productPresenter.loadProductsMoreSales(callProduct, viewMoreSales)

        verify(callProduct)?.enqueue(callbackArgumentCaptorProduct?.capture())
        callbackArgumentCaptorProduct?.value?.onFailure(callProduct, Throwable("Unauthorized"))

        verify(viewMoreSales)?.setProgressProductMore(false)
        verify(viewMoreSales)?.showErrorProductMore("Unauthorized")
    }
}