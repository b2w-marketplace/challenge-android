package br.com.dafle.alodjinha.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.dafle.alodjinha.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_banner.*
import org.jetbrains.anko.browse

class HomeBannerFragment: Fragment() {

    companion object {

        val BANNER_URL_EXTRA = "BANNER_URL_EXTRA"

        fun newInstance(urlImagem: String): HomeBannerFragment {
            val args = Bundle()
            args.putString(BANNER_URL_EXTRA, urlImagem)
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
        arguments?.let {
            Glide.with(this).load(it.getString(BANNER_URL_EXTRA)).placeholder(R.drawable.placeholder).into(ivBanner)
            ivBanner.setOnClickListener {
                context?.browse("https://www.submarino.com.br/")
            }
        }
    }
}