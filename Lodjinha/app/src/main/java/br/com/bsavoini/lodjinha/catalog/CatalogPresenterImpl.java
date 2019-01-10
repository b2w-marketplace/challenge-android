package br.com.bsavoini.lodjinha.catalog;

import br.com.bsavoini.lodjinha.api.model.ProductModel;

import java.util.List;

public class CatalogPresenterImpl implements CatalogContract.CatalogPresenter,
        CatalogContract.CatalogInteractor.ProductsRequestListener {

    private CatalogContract.CatalogView view;
    private CatalogContract.CatalogInteractor interactor;

    public CatalogPresenterImpl(CatalogContract.CatalogView view, CatalogContract.CatalogInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void initViews() {
        view.initErrorMessageView();
        view.hideErrorMsg();
        view.enableToolbarBackButton();
        view.setTollbarTitleWithCategory();
        view.initRecyclerView();
    }

    @Override
    public void requestProducts() {
        int categoryId = view.retrieveCategoryId();
        view.showProgressBar();
        interactor.requestProducts(categoryId, this);
    }

    @Override
    public void onProductRequestSuccessful(List<ProductModel> productsArr) {
        view.hideProgressBar();
        view.updateAdapter(productsArr);
    }

    @Override
    public void onProductRequestFail() {
        view.hideProgressBar();
        view.showErrorMsg();
    }
}
