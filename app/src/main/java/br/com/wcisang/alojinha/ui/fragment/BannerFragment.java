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
import android.widget.ProgressBar;

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

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

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

    private Observer<List<Banner>> getListObserver(){
        return banners -> {
            if (banners != null)
                setupViewPager(banners);
        };
    }

    private void setupViewPager(List<Banner> banners) {
        progressBar.setVisibility(View.GONE);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity(), banners);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
    }
}
