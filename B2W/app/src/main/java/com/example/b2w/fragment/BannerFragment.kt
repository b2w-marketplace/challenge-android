package com.example.b2w.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.b2w.R
import com.example.b2w.adapter.BannerAdapter
import com.example.b2w.model.Banner
import com.example.b2w.util.URL_BANNER
import com.example.b2w.util.mountBanners
import com.example.b2w.util.showToast
import kotlinx.android.synthetic.main.fragment_banner.view.*

class BannerFragment : Fragment() {
    private lateinit var mView: View
    private lateinit var banners: MutableList<Banner>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_banner, container, false)

        retrieveBanners()

        return mView
    }

    private fun retrieveBanners(){
        Volley.newRequestQueue(mView.context).add(object: StringRequest(
            Request.Method.GET,
            URL_BANNER,
            Response.Listener {
                banners = mountBanners(it)

                mView.recycler_banner.layoutManager = LinearLayoutManager(mView.context, LinearLayoutManager.HORIZONTAL, false)
                mView.recycler_banner.adapter = BannerAdapter(mView.context, banners)
            },
            Response.ErrorListener {
                showToast(view!!.context, "Ocorreu um erro ao tentar listar o banner.")
            }
        ){})
    }
}
