package br.com.wcisang.alojinha.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.wcisang.alojinha.R;
import br.com.wcisang.alojinha.databinding.ItemBannerBinding;
import br.com.wcisang.alojinha.model.Banner;

/**
 * Created by WCisang on 07/06/2018.
 */
public class ViewPagerAdapter extends PagerAdapter {


    private Context context;
    private List<Banner> banners;

    public ViewPagerAdapter(Context context, List<Banner> banners) {
        this.context = context;
        this.banners = banners;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        ItemBannerBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_banner, container, false);
        binding.setBanner(banners.get(position));
        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public int getCount() {
        return banners.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}
