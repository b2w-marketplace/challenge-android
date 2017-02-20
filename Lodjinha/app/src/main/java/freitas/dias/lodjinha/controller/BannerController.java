package freitas.dias.lodjinha.controller;


import java.util.List;

import freitas.dias.lodjinha.api.model.Banner;
import freitas.dias.lodjinha.api.model.Banners;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BannerController extends BaseController{

    public void getListBanners() {
        Call<Banners> call = apiService().getListBanners();
        call.enqueue(new Callback<Banners>() {
            @Override
            public void onResponse(Call<Banners> call, Response<Banners> response) {
                List<Banner> banners = response.body().getBanners();
                post(banners);
            }

            @Override
            public void onFailure(Call<Banners> call, Throwable t) {
               post(t);
            }
        });
    }
}
