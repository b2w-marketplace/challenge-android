package br.com.rbueno.lodjinha.ui.home.banner

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.model.Banner
import br.com.rbueno.lodjinha.util.observe
import br.com.rbueno.lodjinha.viewmodel.HomeViewModel
import dagger.android.support.AndroidSupportInjection
import me.relex.circleindicator.CircleIndicator
import javax.inject.Inject

private const val BANNER_FLIPPER_POSITION = 0
private const val LOADING_FLIPPER_POSITION = 1
private const val ERROR_FLIPPER_POSITION = 2

class BannerFragment : Fragment() {

    @Inject
    lateinit var factory: HomeViewModel.HomeViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(HomeViewModel::class.java) }
    private val buttonTryAgain by lazy { view?.findViewById<Button>(R.id.button_try_again) }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_banner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        configTryAgainButton()
    }

    private fun configTryAgainButton() {
        buttonTryAgain?.setOnClickListener {
            viewModel.loadBanner()
        }
    }


    private fun initViewModel() {
        viewModel.apply {
            errorLiveData.observe(this@BannerFragment) {
                if (view is ViewFlipper) {
                    (view as ViewFlipper).displayedChild =
                        ERROR_FLIPPER_POSITION
                }
            }

            loadingLiveData.observe(this@BannerFragment) {
                if (view is ViewFlipper) {
                    (view as ViewFlipper).displayedChild = if (it) LOADING_FLIPPER_POSITION else BANNER_FLIPPER_POSITION
                }
            }

            bannerLiveData.observe(this@BannerFragment) {
                if (view is ViewFlipper) {
                    (view as ViewFlipper).displayedChild = BANNER_FLIPPER_POSITION
                }
                configViewPager(it)
                clearBannerLiveData()
            }
        }.loadBanner()
    }

    private fun configViewPager(banner: Banner) {
        val adapter = BannerAdapter(banner)
        val viewPager = view?.findViewById<ViewPager>(R.id.view_pager_banner)
        viewPager?.adapter = adapter

        val indicator = view?.findViewById(R.id.indicator) as CircleIndicator
        indicator.setViewPager(viewPager)
        adapter.registerDataSetObserver(indicator.dataSetObserver)
    }
}