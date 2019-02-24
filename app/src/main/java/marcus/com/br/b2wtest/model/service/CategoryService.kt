package marcus.com.br.b2wtest.model.service

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import marcus.com.br.b2wtest.model.api.Api
import marcus.com.br.b2wtest.model.data.CategoryResult
import javax.inject.Inject

class CategoryService @Inject constructor(val api: Api) {

    fun getCategories(): Flowable<CategoryResult> {
        return api.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}