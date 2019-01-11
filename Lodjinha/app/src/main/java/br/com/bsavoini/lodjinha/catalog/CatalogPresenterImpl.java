package br.com.bsavoini.lodjinha.catalog;

import br.com.bsavoini.lodjinha.api.model.ProductModel;

import java.util.List;

public class CatalogPresenterImpl implements CatalogContract.CatalogPresenter,
        CatalogContract.CatalogInteractor.ProductsRequestListener {

    private CatalogContract.CatalogView view;
    private CatalogContract.CatalogInteractor interactor;
    private int categoryId;

    private int currentOffset;
    private int maxPagination;
    final private int pagination = 20;

    public CatalogPresenterImpl(CatalogContract.CatalogView view, CatalogContract.CatalogInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
        this.currentOffset = 0;
    }

    @Override
    public void initViews() {
        categoryId = view.retrieveCategoryId();
        view.initProgressBar();
        view.initErrorMessageView();
        view.enableToolbarBackButton();
        view.setTollbarTitleWithCategory();
        view.initRecyclerView();
    }

    @Override
    public void requestProducts() {
        if (currentOffset <= maxPagination) {
            view.showProgressBar();
            interactor.requestProducts(categoryId, currentOffset, currentOffset + pagination, this);
            currentOffset += pagination;
        }
    }

    @Override
    public void onProductRequestSuccessful(int total, List<ProductModel> productsArr) {
        this.maxPagination = total;
        view.hideProgressBar();
        view.updateAdapter(productsArr);
    }

    @Override
    public void onProductRequestFail() {
        view.hideProgressBar();
        view.showErrorMsg();
    }
}
