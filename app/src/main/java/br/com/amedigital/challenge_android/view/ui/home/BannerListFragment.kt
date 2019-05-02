package br.com.amedigital.challenge_android.view.ui.home

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import br.com.amedigital.challenge_android.R
import br.com.amedigital.challenge_android.extension.observeLiveData
import br.com.amedigital.challenge_android.factory.AppViewModelFactory
import br.com.amedigital.challenge_android.models.Resource
import br.com.amedigital.challenge_android.models.Status
import br.com.amedigital.challenge_android.models.entity.Banner
import br.com.amedigital.challenge_android.view.adapter.BannerPagerListAdapter
import br.com.amedigital.challenge_android.view.viewholder.BannerListViewHolder
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_banner_pager_list.*
import org.jetbrains.anko.support.v4.toast
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class BannerListFragment : Fragment(), BannerListViewHolder.Delegate {

    private lateinit var swipeTimer: Timer
    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private lateinit var viewModel: HomeActivityViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_banner_pager_list, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeActivityViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel() {
        observeLiveData(viewModel.getBannerListObservable()) { updateBannerList(it) }
        viewModel.postBannerPage(1)
    }

    private fun updateBannerList(resource: Resource<List<Banner>>) {
        when (resource.status) {
            Status.LOADING -> Unit
            Status.SUCCESS -> initializeUI(resource)
            Status.ERROR -> toast(resource.errorEnvelope?.status_message.toString())
        }
    }

    private fun initializeUI(resource: Resource<List<Banner>>){
        createBanner(resource)

    }

    private fun createBanner(resource: Resource<List<Banner>>) {

        pager.adapter = BannerPagerListAdapter(ArrayList(resource.data))
        indicator.setViewPager(pager)
        val density = resources.displayMetrics.density
        indicator.radius = 3 * density

        NUM_PAGES = resource.data!!.size

        // start viewpager
        val handler = Handler()
        val Update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            pager!!.setCurrentItem(currentPage++, true)
        }
        swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 4000, 4000)

        // Pager listener over indicator
        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                currentPage = position

            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {

            }

            override fun onPageScrollStateChanged(pos: Int) {

            }
        })
    }

    override fun onPause() {
        super.onPause()
        swipeTimer?.cancel()
    }

    override fun onItemClick(banner: Banner) {}

    companion object {

        private var currentPage = 0
        private var NUM_PAGES = 0
    }
}