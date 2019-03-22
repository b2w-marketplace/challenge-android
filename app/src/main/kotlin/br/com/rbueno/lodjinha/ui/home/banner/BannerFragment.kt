package br.com.rbueno.lodjinha.ui.home.banner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.model.Banner
import com.google.gson.GsonBuilder
import me.relex.circleindicator.CircleIndicator


class BannerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_banner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configViewPager()
    }

    private fun configViewPager() {
        val adapter = BannerAdapter(mockBanner())
        val viewPager = view?.findViewById<ViewPager>(R.id.view_pager_banner)
        viewPager?.adapter = adapter

        val indicator = view?.findViewById(R.id.indicator) as CircleIndicator
        indicator.setViewPager(viewPager)

        // optional
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