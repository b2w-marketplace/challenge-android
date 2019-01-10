package br.com.bsavoini.lodjinha.catalog;

import br.com.bsavoini.lodjinha.api.model.ProductModel;

import java.util.List;

public interface CatalogContract {

    interface CatalogInteractor {
        void requestProducts(int categoryId, ProductsRequestListener productsRequestListener);

        interface ProductsRequestListener {
            void onProductRequestSuccessful(List<ProductModel> productsArr);
            void onProductRequestFail();
        }
    }

    interface CatalogView {
        void initErrorMessageView();

        void hideErrorMsg();
        void showErrorMsg();

        void setTollbarTitleWithCategory();
        void initRecyclerView();
        void enableToolbarBackButton();

        int retrieveCategoryId();

        void updateAdapter(List<ProductModel> productsArr);

        void showProgressBar();
        void hideProgressBar();

        //todo implement infinite loading
    }

    interface CatalogPresenter {
        void initViews();
        void requestProducts();
    }

}
