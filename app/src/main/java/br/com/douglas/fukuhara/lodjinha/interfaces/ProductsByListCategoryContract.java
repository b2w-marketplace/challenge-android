package br.com.douglas.fukuhara.lodjinha.interfaces;

import java.util.List;

import br.com.douglas.fukuhara.lodjinha.network.vo.ProductDataVo;

public interface ProductsByListCategoryContract {

    interface View {
        void displayListOfProducts(List<ProductDataVo> mListOfProducts);

        void showLoader();
        void dismissLoader();
        void showRecyclerViewContainer();
        void onProductListFailedGenericError();
        void onProductFetchFailed(String message);
    }

    interface Presenter {
        void fetchData();
        void disposeAll();
    }
}
