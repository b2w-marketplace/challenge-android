package br.com.bsavoini.lodjinha.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.bsavoini.lodjinha.R;
import br.com.bsavoini.lodjinha.api.RetrofitInstance;
import br.com.bsavoini.lodjinha.api.model.BannersResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private ViewPager bannersViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        bannersViewPager = view.findViewById(R.id.view_pager_banners);
        requestBanners();
    }
//TODO call request from interactor
    private void requestBanners() {
        Call<BannersResponse> bannersResponseCall = RetrofitInstance.getLodjinhaService().requestBanner();
        bannersResponseCall.enqueue(new Callback<BannersResponse>() {
            @Override
            public void onResponse(Call<BannersResponse> call, Response<BannersResponse> response) {

                bannersViewPager.setAdapter(new BannerPagerAdapter(response.body().getBannersArr()));

            }

            @Override
            public void onFailure(Call<BannersResponse> call, Throwable t) {
            }
        });
    }
}
