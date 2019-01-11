package br.com.bsavoini.lodjinha.catalog;

import br.com.bsavoini.lodjinha.api.model.ProductModel;

import java.util.List;

public interface CatalogContract {

    interface CatalogInteractor {
        void requestProducts(int categoryId, int offset, int limit, ProductsRequestListener productsRequestListener);

        interface ProductsRequestListener {
            void onProductRequestSuccessful(int total, List<ProductModel> productsArr);
            void onProductRequestFail();
        }
    }

    interface CatalogView {
        void initProgressBar();
        void initErrorMessageView();

        void showErrorMsg();

        void setTollbarTitleWithCategory();
        void initRecyclerView();
        void enableToolbarBackButton();

        int retrieveCategoryId();

        void updateAdapter(List<ProductModel> productsArr);

        void showProgressBar();
        void hideProgressBar();

    }

    interface CatalogPresenter {
        void initViews();
        void requestProducts();
    }

}
