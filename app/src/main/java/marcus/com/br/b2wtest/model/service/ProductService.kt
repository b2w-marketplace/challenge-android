package marcus.com.br.b2wtest.model.service

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import marcus.com.br.b2wtest.model.api.Api
import marcus.com.br.b2wtest.model.data.ProductResult
import javax.inject.Inject

class ProductService @Inject constructor(val api: Api) {

    fun getBestSellers(): Flowable<ProductResult> {
        return api
            .getBestSellers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getByCategory(offset: Int, categoryId: Int): Flowable<ProductResult> {
        return api
            .getByCategory(offset = offset, categoryId = categoryId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}