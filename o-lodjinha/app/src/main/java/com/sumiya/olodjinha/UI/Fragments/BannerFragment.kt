package com.sumiya.olodjinha.UI.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sumiya.olodjinha.Model.BannerDataModel
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.Service.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BannerFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_banner, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }

        val call = APIService().banners().list()

        call.enqueue(object : Callback<BannerDataModel?> {
            override fun onFailure(call: Call<BannerDataModel?>?, t: Throwable?) {
                if (t != null) {
                    print(t.localizedMessage)
                }
            }

            override fun onResponse(call: Call<BannerDataModel?>?, response: Response<BannerDataModel?>?) {
                if (response != null) {
                    print(response.body())
                }

            }
        })
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }
}
