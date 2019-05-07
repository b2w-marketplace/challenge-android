package br.com.amedigital.product

import br.com.amedigital.model.Product
import br.com.amedigital.network.model.BodyRequestProduct
import retrofit2.Call

interface ProductContract {
    interface View {
        fun setProgressProductMore(active: Boolean)

        fun showProductsMore(products: ArrayList<Product>)

        fun showErrorProductMore(message : String)

        fun showEmpytProductMore(message : String)
    }

    interface ViewProductCategoryId {
        fun setProgressProdCategoryId(active: Boolean)

        fun showProdCategoryId(products: ArrayList<Product>)

        fun showErrorProductCategoryId(message : String)

        fun showEmpytProductCategory(message : String)
    }

    interface ViewReserveProduct {
        fun setProgressReserve(active: Boolean)

        fun successReserve()

        fun showErrorReserve(message : String)
    }

    interface ProductActionListener {
        fun loadProductsMoreSales(call: Call<BodyRequestProduct>, view: ProductContract.View)
        fun listProduct(call: Call<BodyRequestProduct>, view: ProductContract.ViewProductCategoryId)
        fun reserve(call: Call<Void>, view : ProductContract.ViewReserveProduct)
    }
}