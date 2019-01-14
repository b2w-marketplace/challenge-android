package b2w.com.br.olodjinha.ui.home;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import javax.inject.Inject;

import b2w.com.br.olodjinha.data.api.DataFacade;
import b2w.com.br.olodjinha.data.api.RetrofitClient;
import b2w.com.br.olodjinha.data.models.BannerWrapper;
import b2w.com.br.olodjinha.data.models.ProductsWrapper;
import b2w.com.br.olodjinha.data.models.CategoryWrapper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends MvpNullObjectBasePresenter<HomeContract> {

    private final DataFacade mDataFacade;

    Disposable mBestSelleresSubscribe;
    Disposable mBannerSubscribe;
    Disposable mCategoriesSubscribe;

    @Inject
    public HomePresenter(DataFacade dataFacade) {
        mDataFacade = dataFacade;
    }

    public void init() {
        getBanners();
        getCategories();
        getBestSellers();
    }

    public void getBanners() {
        mBannerSubscribe = mDataFacade.getBanners()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bannerWrapper -> getView().showBanners(bannerWrapper.getData()),
                        throwable -> getView().showError());
    }

    public void getCategories() {
        mCategoriesSubscribe = mDataFacade.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categoryWrapper -> getView().showCategories(categoryWrapper.getData()),
                        throwable -> getView().showError());
    }

    public void getBestSellers() {
        mBestSelleresSubscribe = mDataFacade.getBestSellers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bestSellersWrapper -> getView().showBestSellers(bestSellersWrapper.getData()),
                        throwable -> getView().showError());
    }

    @Override
    public void detachView(boolean retainInstance) {
        if (mBestSelleresSubscribe != null) {
            mBannerSubscribe.dispose();
        }
        if (mBannerSubscribe != null) {
            mBannerSubscribe.dispose();
        }
        if (mCategoriesSubscribe != null) {
            mCategoriesSubscribe.dispose();
        }
        super.detachView(retainInstance);
    }
}
