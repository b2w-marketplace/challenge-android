package com.example.lidjinha.lodjinha

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_banner.*

class BannerFragment : Fragment() {

    companion object {
        const val argumentsLinkUrl = "link_url"
        const val argumentsImagekUrl = "link_url"

        fun getInstance(link: String, image: String): Fragment {
            val fragment = BannerFragment()
            fragment.arguments.putString(argumentsLinkUrl, link)
            fragment.arguments.putString(argumentsImagekUrl, image)

            return BannerFragment()
        }
    }

    lateinit var linkUrl: String
    lateinit var imageUrl: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_banner, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments.apply {
            linkUrl = this.getString(argumentsLinkUrl)
            imageUrl = this.getString(argumentsImagekUrl)
        }

        val banner = iv_banner
        Picasso.get().load(imageUrl).into(banner)
    }

}