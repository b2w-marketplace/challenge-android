package br.com.amedigital.network

import br.com.amedigital.network.model.BodyRequestBanner
import br.com.amedigital.network.model.BodyRequestCategory
import br.com.amedigital.network.model.BodyRequestProduct
import retrofit2.Call

class LojinhaServiceEndPoint : RestClient() {

    fun getBanner() : Call<BodyRequestBanner> {
        val service = LojinhaServiceEndPoint().createService(LojinhaService::class.java)
        return service.getBanner()
    }

    fun getCategory() : Call<BodyRequestCategory> {
        val service = LojinhaServiceEndPoint().createService(LojinhaService::class.java)
        return service.getCategory()
    }

    fun getProductByCategory(categoryId : Int) : Call<BodyRequestProduct> {
        val service = LojinhaServiceEndPoint().createService(LojinhaService::class.java)
        return service.getProductByCategory(categoryId)
    }

    fun getTopSellingProducts() : Call<BodyRequestProduct> {
        val service = LojinhaServiceEndPoint().createService(LojinhaService::class.java)
        return service.getTopSellingProducts()
    }

    fun setReservation(productId : Int) : Call<Void> {
        val service = LojinhaServiceEndPoint().createService(LojinhaService::class.java)
        return service.setReservation(productId)
    }
}