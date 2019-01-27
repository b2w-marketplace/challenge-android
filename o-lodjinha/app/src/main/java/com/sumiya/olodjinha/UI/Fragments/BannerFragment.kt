package com.sumiya.olodjinha.ui.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.model.BannerModel
import kotlinx.android.synthetic.main.fragment_banner.*

class BannerFragment : Fragment() {

    private var listener: BannerListener? = null
    lateinit var banner: BannerModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_banner, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BannerListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement BannerListener")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Glide
                .with(this)
                .load(banner.urlImagem)
                .into(bannerImage)

        bannerImage.setOnClickListener {
            listener?.bannerCLick(banner.linkUrl)
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface BannerListener {
        fun bannerCLick(bannerUrl: String)
    }
}
