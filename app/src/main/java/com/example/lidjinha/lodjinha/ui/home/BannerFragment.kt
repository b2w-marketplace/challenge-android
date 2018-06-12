package com.example.lidjinha.lodjinha.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.lidjinha.lodjinha.R
import com.squareup.picasso.Picasso

class BannerFragment : Fragment() {

    companion object {
        const val argumentsLinkUrl = "link_url"
        const val argumentsImageUrl = "image_url"

        fun getInstance(link: String, image: String): Fragment {
            val fragment = BannerFragment()
            val bundle = Bundle()
            bundle.putString(argumentsLinkUrl, link)
            bundle.putString(argumentsImageUrl, image)
            fragment.arguments = bundle

            return fragment
        }
    }

    private lateinit var linkUrl: String
    private lateinit var imageUrl: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_banner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.apply {
            linkUrl = this.getString(argumentsLinkUrl)
            imageUrl = this.getString(argumentsImageUrl)
        }

        val banner = view.findViewById<ImageView>(R.id.iv_banner)
        Picasso.with(activity).load(imageUrl).into(banner)
    }
}