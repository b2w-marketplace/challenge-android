package br.com.android.seiji.mobileui.ui.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.android.seiji.domain.model.Banner
import br.com.android.seiji.mobileui.R
import br.com.android.seiji.mobileui.di.ViewModelFactory
import br.com.android.seiji.mobileui.extensions.gone
import br.com.android.seiji.mobileui.extensions.visible
import br.com.android.seiji.mobileui.mapper.BannerViewMapper
import br.com.android.seiji.mobileui.ui.ClickListener
import br.com.android.seiji.mobileui.ui.home.adapter.BannerAdapter
import br.com.android.seiji.presentation.viewModel.GetBannersViewModel
import br.com.android.seiji.presentation.model.BannerView
import br.com.android.seiji.presentation.state.Resource
import br.com.android.seiji.presentation.state.ResourceState
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber
import javax.inject.Inject

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    @Inject
    lateinit var bannerAdapter: BannerAdapter

    @Inject
    lateinit var mapper: BannerViewMapper

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val bannerViewModel: GetBannersViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(GetBannersViewModel::class.java)
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBannerRecycler()
    }

    override fun onStart() {
        super.onStart()
        bannerViewModel.getBanners().observe(this,
            Observer<Resource<List<BannerView>>> {
                it?.let {
                    handleBannerDataState(it)
                }
            })
        bannerViewModel.fetchBanners()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun setupBannerRecycler() {
        bannerAdapter.clickListener = clickListener
        val linearLayoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL, false
        )
        recyclerBanners.setHasFixedSize(true)
        recyclerBanners.layoutManager = linearLayoutManager
        recyclerBanners.isNestedScrollingEnabled = false
        recyclerBanners.adapter = bannerAdapter
    }

    private fun handleBannerDataState(resource: Resource<List<BannerView>>) {
        Timber.i(resource.status.toString())
        when (resource.status) {
            ResourceState.LOADING -> {
                bannerProgressBar.visible()
                recyclerBanners.gone()
            }
            ResourceState.SUCCESS -> {
                setBannerSuccess(resource.data?.map { mapper.mapToView(it) })
            }
            ResourceState.ERROR -> {
                Timber.e(resource.message)
            }
        }
    }

    private fun setBannerSuccess(banners: List<Banner>?) {
        bannerProgressBar.gone()
        banners?.let {
            bannerAdapter.banners = it
            bannerAdapter.notifyDataSetChanged()
            recyclerBanners.visible()
        }
    }

    private val clickListener = object : ClickListener {
        override fun onBannerClicked(bannerUrl: String) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(bannerUrl))
            startActivity(browserIntent)
        }
    }
}