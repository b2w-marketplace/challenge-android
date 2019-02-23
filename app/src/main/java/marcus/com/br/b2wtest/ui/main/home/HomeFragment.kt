package marcus.com.br.b2wtest.ui.main.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import marcus.com.br.b2wtest.R
import marcus.com.br.b2wtest.helper.snap.OnSnapPositionChangeListener
import marcus.com.br.b2wtest.helper.snap.attachSnapHelperWithListener
import marcus.com.br.b2wtest.model.data.BannerData
import marcus.com.br.b2wtest.ui.BaseFragment

class HomeFragment : BaseFragment() {

    private val bannersAdapter = BannersAdapter()

    private val homeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setupBanner()
        initObservers()
        homeViewModel.getBanners()
    }

    private fun setupBanner() {
        fragmentHomeBanners.adapter = bannersAdapter
        fragmentHomeBanners.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initObservers() {
        homeViewModel.bannerResult.observeResource(this, onSuccess = {
            successBanner(it.bannerList)
        }, onError = {

        })
    }

    private fun successBanner(bannerList: List<BannerData>) {
        bannersAdapter.addToList(bannerList as ArrayList<BannerData>)
        fragmentHomeBannersIndicator.attach(fragmentHomeBanners)
        fragmentHomeBanners.attachSnapHelperWithListener(
            PagerSnapHelper(),
            object : OnSnapPositionChangeListener {
                override fun onSnapPositionChange(position: Int) {
                    fragmentHomeBannersIndicator.setSelected(position)
                }
            }
        )
    }

    companion object {
        const val TAG = "home.tag"
    }
}