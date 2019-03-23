package br.com.rbueno.lodjinha.ui.home.banner

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.model.Banner
import br.com.rbueno.lodjinha.util.observe
import br.com.rbueno.lodjinha.viewmodel.HomeViewModel
import com.google.gson.GsonBuilder
import dagger.android.support.AndroidSupportInjection
import me.relex.circleindicator.CircleIndicator
import javax.inject.Inject


class BannerFragment : Fragment() {

    @Inject
    lateinit var factory: HomeViewModel.HomeViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(HomeViewModel::class.java) }


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
    }

    private fun initViewModel() {
        viewModel.apply {
            errorLiveData.observe(this@BannerFragment) {

            }

            loadingLiveData.observe(this@BannerFragment) {

            }

            bannerLiveData.observe(this@BannerFragment) {
                configViewPager(it)
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

    private fun mockBanner(): Banner {
        val bannerString = "{\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"urlImagem\": \"https://images-submarino.b2w.io/spacey/2017/02/06/MainTop_GAMES_FEV17.png\",\n" +
                "      \"linkUrl\": \"https://images-submarino.b2w.io/spacey/2017/02/06/MainTop_GAMES_FEV17.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"urlImagem\": \"https://images-submarino.b2w.io/spacey/2017/02/06/DESTAQUE_FULL_CARTAO_CASA_FEV.png\",\n" +
                "      \"linkUrl\": \"https://images-submarino.b2w.io/spacey/2017/02/06/DESTAQUE_FULL_CARTAO_CASA_FEV.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3,\n" +
                "      \"urlImagem\": \"https://images-submarino.b2w.io/spacey/2017/02/03/sub-home-dest-full-655x328-touch-play.png\",\n" +
                "      \"linkUrl\": \"https://images-submarino.b2w.io/spacey/2017/02/03/sub-home-dest-full-655x328-touch-play.png\"\n" +
                "    }\n" +
                "  ]\n" +
                "}"

        return GsonBuilder().create().fromJson(bannerString, Banner::class.java)
    }

}