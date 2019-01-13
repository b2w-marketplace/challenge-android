package b2w.com.br.olodjinha.main;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import javax.inject.Inject;

import b2w.com.br.olodjinha.data.api.RetrofitClient;
import b2w.com.br.olodjinha.data.models.BannerWrapper;
import b2w.com.br.olodjinha.data.models.ProductsWrapper;
import b2w.com.br.olodjinha.data.models.CategoryWrapper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends MvpNullObjectBasePresenter<HomeContract> {

    private Object mCategories;

    @Inject
    public HomePresenter() {

    }

    public void init() {
        getBanners();
        getCategories();
        getBestSellers();
    }

    public void getBanners() {
        RetrofitClient.getClient().getBanners()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BannerWrapper>() {
                    @Override
                    public void accept(BannerWrapper bannerWrapper) throws Exception {
                        getView().showBanners(bannerWrapper.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void getCategories() {
        RetrofitClient.getClient().getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CategoryWrapper>() {
                    @Override
                    public void accept(CategoryWrapper categoryWrapper) throws Exception {
                        getView().showCategories(categoryWrapper.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void getBestSellers() {
        RetrofitClient.getClient().getBestSellers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProductsWrapper>() {
                    @Override
                    public void accept(ProductsWrapper bestSellersWrapper) throws Exception {
                        getView().showBestSellers(bestSellersWrapper.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("ERROR", throwable.getMessage());
                    }
                });
        ;
    }
}
