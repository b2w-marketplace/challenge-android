package br.com.dafle.alodjinha.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.dafle.alodjinha.R
import br.com.dafle.alodjinha.model.Banner
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_banner.*
import org.jetbrains.anko.browse

class HomeBannerFragment: Fragment() {

    companion object {

        val BANNER_EXTRA = "BANNER_EXTRA"

        fun newInstance(banner: Banner): HomeBannerFragment {
            val args = Bundle()
            args.putParcelable(BANNER_EXTRA, banner)
            val fragment = HomeBannerFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_banner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Banner>(BANNER_EXTRA)?.let { banner ->
            Glide.with(this).load(banner.urlImagem).placeholder(R.drawable.placeholder).into(ivBanner)
            ivBanner.setOnClickListener {
                context?.browse(banner.linkUrl)
            }
        }
    }
}