package com.sumiya.olodjinha.UI.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sumiya.olodjinha.Model.CategoryDataModel
import com.sumiya.olodjinha.Model.CategoryModel
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.Service.APIService
import com.sumiya.olodjinha.UI.Adapter.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryFragment : Fragment() {

    private var listener: CategoryListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CategoryListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement CategoryListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val call = APIService().categories().list()

        call.enqueue(object : Callback<CategoryDataModel>{
            override fun onFailure(call: Call<CategoryDataModel>?, t: Throwable?) {
                if (t != null) {
                    print(t.localizedMessage)
                }
            }

            override fun onResponse(call: Call<CategoryDataModel>?, response: Response<CategoryDataModel>?) {
                if (response != null) {
                    print(response.body())
                    val categories = response.body()!!
                    categoriesRecycler.adapter = CategoryAdapter(
                            categories,
                            { category : CategoryModel -> categoryClicked(category) }
                    )

                    categoriesRecycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                }
            }
        })
    }

    private fun categoryClicked(category: CategoryModel) {
        listener!!.requestProductList(category)
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface CategoryListener {
        fun requestProductList(category: CategoryModel)
    }

}
