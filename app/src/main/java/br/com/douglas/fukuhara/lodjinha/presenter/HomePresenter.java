package br.com.douglas.fukuhara.lodjinha.presenter;

import java.lang.ref.WeakReference;
import java.util.List;

import br.com.douglas.fukuhara.lodjinha.interfaces.HomeContract;
import br.com.douglas.fukuhara.lodjinha.network.RestClient;
import br.com.douglas.fukuhara.lodjinha.network.vo.BannerDataVo;
import br.com.douglas.fukuhara.lodjinha.network.vo.BannerVo;
import br.com.douglas.fukuhara.lodjinha.network.vo.CategoryDataVo;
import br.com.douglas.fukuhara.lodjinha.network.vo.CategoryVo;
import br.com.douglas.fukuhara.lodjinha.network.vo.ProductBestSellerVo;
import br.com.douglas.fukuhara.lodjinha.network.vo.ProductDataVo;
import io.reactivex.disposables.CompositeDisposable;

import static br.com.douglas.fukuhara.lodjinha.network.NetworkUtils.getObservableNetworkThread;

public class HomePresenter implements HomeContract.Presenter {

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private final WeakReference<HomeContract.View> mView;
    private final RestClient mRestClient;

    public HomePresenter(HomeContract.View view, RestClient restClient) {
        mView = new WeakReference<>(view);
        mRestClient = restClient;
    }

    @Override
    public void fetchContent() {
        if (mView.get() != null) {
            mView.get().showBannerLoading();
            mView.get().showCategoryLoading();
            mView.get().showBestSellerProductsLoading();
        }

        mCompositeDisposable.add(
                mRestClient.getApi().getBanner()
                    .compose(getObservableNetworkThread())
                    .subscribe(
                        this::onBannerSuccess,
                        this::onBannerFailed)
        );

        mCompositeDisposable.add(
                mRestClient.getApi().getCategoria()
                    .compose(getObservableNetworkThread())
                    .subscribe(
                        this::onCategorySuccess,
                        this::onCategoryFailed)
        );

        mCompositeDisposable.add(
                mRestClient.getApi().getProdutoMaisVendido()
                    .compose(getObservableNetworkThread())
                    .subscribe(
                            this::onBestSellerSuccess,
                            this::onBestSellerFailed)
        );
    }

    private void onBannerSuccess(BannerVo bannerVo) {
        List<BannerDataVo> listOfData = bannerVo.getData();
        if (mView.get() != null) {
            mView.get().hideBannerLoading();
            mView.get().onBannerContentLoaded(listOfData);
        }
    }

    private void onBannerFailed(Throwable throwable) {
        if (mView.get() != null) {
            mView.get().hideBannerLoading();
            if (throwable.getMessage() == null || throwable.getMessage().isEmpty()) {
                mView.get().onBannerContentFailedGenericError();
            } else {
                mView.get().onBannerContentFailed(throwable.getMessage());
            }
        }
    }

    private void onCategorySuccess(CategoryVo categoryVo) {
        List<CategoryDataVo> listOfData = categoryVo.getData();
        if (mView.get() != null) {
            mView.get().hideCategoryLoading();
            mView.get().onCategoryContentLoaded(listOfData);
        }
    }

    private void onCategoryFailed(Throwable throwable) {
        if (mView.get() != null) {
            mView.get().hideCategoryLoading();
            if (throwable.getMessage() == null || throwable.getMessage().isEmpty()) {
                mView.get().onCategoryContentFailedGenericError();
            } else {
                mView.get().onCategoryContentFailed(throwable.getMessage());
            }
        }
    }

    private void onBestSellerSuccess(ProductBestSellerVo productBestSellerVo) {
        List<ProductDataVo> listOfData = productBestSellerVo.getData();
        if (mView.get() != null) {
            mView.get().hideBestSellerProductsLoading();
            mView.get().onBestSellerContentLoaded(listOfData);
        }
    }

    private void onBestSellerFailed(Throwable throwable) {
        if (mView.get() != null) {
            mView.get().hideBestSellerProductsLoading();
            if (throwable.getMessage() == null || throwable.getMessage().isEmpty()) {
                mView.get().onBestSellerContentFailedGenericError();
            } else {
                mView.get().onBestSellerContentFailed(throwable.getMessage());
            }
        }
    }

    @Override
    public void disposeAll() {
        mCompositeDisposable.clear();
    }
}
