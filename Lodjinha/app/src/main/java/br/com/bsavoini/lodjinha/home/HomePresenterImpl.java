package br.com.bsavoini.lodjinha.home;

import br.com.bsavoini.lodjinha.api.model.BannerModel;
import br.com.bsavoini.lodjinha.api.model.CategoryModel;
import br.com.bsavoini.lodjinha.api.model.ProductModel;

import java.util.List;

public class HomePresenterImpl implements HomeContract.HomePresenter,
        HomeContract.HomeInteractor.BannerRequestListener,
        HomeContract.HomeInteractor.CategoriesRequestListener,
        HomeContract.HomeInteractor.BestSellersRequestListener{
    private HomeContract.HomeView view;
    private HomeContract.HomeInteractor interactor;

    public HomePresenterImpl(HomeContract.HomeView view, HomeContract.HomeInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void initViews() {
        view.initRecyclerViews();
        view.initViewPager();
    }

    @Override
    public void requestBanners() {
        view.showProgressBar();
        interactor.requestBanners(this);
    }

    @Override
    public void requestCategories() {
        view.showProgressBar();
        interactor.requestCategories(this);

    }

    @Override
    public void requestBestSellers() {
        view.showProgressBar();
        interactor.requestBestSellers(this);
    }

    @Override
    public void onBannerRequestSuccessful(List<BannerModel> bannersArr) {
        view.hideProgressBar();
        view.updateBannersAdapter(bannersArr);
    }

    @Override
    public void onBannerRequestFail() {
        view.hideProgressBar();
        view.showErrorMsg();
    }

    @Override
    public void onCategoriesRequestSuccessful(List<CategoryModel> categoriesArr) {
        view.hideProgressBar();
        view.updateCategoriesAdapter(categoriesArr);
    }

    @Override
    public void onCategoriesRequestFail() {
        view.hideProgressBar();
        view.showErrorMsg();
    }

    @Override
    public void onBestSellersRequestSuccessful(List<ProductModel> productsArr) {
        view.hideProgressBar();
        view.updateProductAdapter(productsArr);
    }

    @Override
    public void onBestSellersRequestFail() {
        view.hideProgressBar();
        view.showErrorMsg();
    }
}
