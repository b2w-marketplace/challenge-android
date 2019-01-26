package com.sumiya.olodjinha.UI.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sumiya.olodjinha.Model.BannerDataModel
import com.sumiya.olodjinha.Model.BannerModel
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.Service.APIService
import kotlinx.android.synthetic.main.fragment_banner.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BannerFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null
    lateinit var banner: BannerModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_banner, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Glide
                .with(this)
                .load(banner.urlImagem)
                .into(bannerImage)
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
