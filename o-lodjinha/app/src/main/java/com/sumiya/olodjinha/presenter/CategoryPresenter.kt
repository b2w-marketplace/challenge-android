package com.sumiya.olodjinha.presenter

import com.sumiya.olodjinha.contracts.PresenterCategoriesContract
import com.sumiya.olodjinha.contracts.ViewCategoryFragmentContract
import com.sumiya.olodjinha.model.CategoryDataModel
import com.sumiya.olodjinha.service.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryPresenter(val categoryView: ViewCategoryFragmentContract) : PresenterCategoriesContract {
    //Methods
    override fun getCategories() {
        val call = APIService().categories().list()

        call.enqueue(object : Callback<CategoryDataModel> {
            override fun onFailure(call: Call<CategoryDataModel>?, t: Throwable?) {
                if (t != null) {
                    print(t.localizedMessage)
                }
            }

            override fun onResponse(call: Call<CategoryDataModel>?, response: Response<CategoryDataModel>?) {
                if (response != null) {
                    categoryView.setCategoryResponse(response.body()!!)
                }
            }
        })
    }
}