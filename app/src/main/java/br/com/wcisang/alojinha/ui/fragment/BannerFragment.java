package br.com.wcisang.alojinha.ui.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.wcisang.alojinha.R;
import br.com.wcisang.alojinha.model.Banner;
import br.com.wcisang.alojinha.ui.adapter.ViewPagerAdapter;
import br.com.wcisang.alojinha.viewmodel.BannerFragmentViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by WCisang on 07/06/2018.
 */
public class BannerFragment extends Fragment {

    private BannerFragmentViewModel viewModel;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.indicator)
    CircleIndicator indicator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.banner_fragment, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(BannerFragmentViewModel.class);
        viewModel.getListMutableLiveData().observe(this, getListObserver());
        viewModel.init();
    }

    private void setupViewPager(List<Banner> banners) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity(), banners);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
    }

    private Observer<List<Banner>> getListObserver(){
        return new Observer<List<Banner>>() {
            @Override
            public void onChanged(@Nullable List<Banner> banners) {
                if (banners != null)
                    setupViewPager(banners);
            }
        };
    }
}
