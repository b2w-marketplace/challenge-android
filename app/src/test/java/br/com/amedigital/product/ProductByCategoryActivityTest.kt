package br.com.amedigital.product

import br.com.amedigital.model.Product
import br.com.amedigital.network.model.BodyRequestProduct
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductByCategoryActivityTest {
    @Mock
    lateinit var viewProduct: ProductContract.ViewProductCategoryId

    @Mock
    lateinit var callProduct: Call<BodyRequestProduct>

    lateinit var productPresenter: ProductPresenter

    @Captor
    var callbackArgumentCaptorProduct: ArgumentCaptor<Callback<BodyRequestProduct>>? = null
    private var bodyRequestProduct: BodyRequestProduct? = null
    private var responseProduct: Response<BodyRequestProduct>? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        productPresenter = ProductPresenter()
    }

    @Test
    fun showProductsForCategoryId() {
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
            productPresenter.listProduct(callProduct, viewProduct)
            Mockito.verify(callProduct).enqueue(capture())
            responseProduct?.apply {
                value?.onResponse(callProduct, this)
            }

            Mockito.verify(viewProduct)?.setProgressProdCategoryId(false)
            responseProduct?.body()?.let {
                Mockito.verify(viewProduct)?.showProdCategoryId(it.data)
                Assert.assertEquals(it.data, products)
            }
        }
    }
}