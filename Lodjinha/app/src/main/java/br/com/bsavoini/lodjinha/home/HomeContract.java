package br.com.bsavoini.lodjinha.home;

import br.com.bsavoini.lodjinha.api.model.BannerModel;
import br.com.bsavoini.lodjinha.api.model.CategoryModel;
import br.com.bsavoini.lodjinha.api.model.ProductModel;

import java.util.List;

public interface HomeContract {
    interface HomeInteractor {
        void requestBanners(BannerRequestListener listener);

        interface BannerRequestListener{
            void onBannerRequestSuccessful(List<BannerModel> bannersArr);
            void onBannerRequestFail();
        }

        void requestCategories(CategoriesRequestListener listener);

        interface CategoriesRequestListener{
            void onCategoriesRequestSuccessful(List<CategoryModel> categoriesArr);
            void onCategoriesRequestFail();
        }

        void requestBestSellers(BestSellersRequestListener listener);

        interface BestSellersRequestListener{
            void onBestSellersRequestSuccessful(List<ProductModel> productsArr);
            void onBestSellersRequestFail();
        }
    }

    interface HomeView{
        void initViewPager();
        void initRecyclerViews();

        void updateBannersAdapter(List<BannerModel> bannersArr);
        void updateCategoriesAdapter(List<CategoryModel> categoriesArr);
        void updateProductAdapter(List<ProductModel> productsArr);

        void showErrorMsg();

        void showProgressBar();
        void hideProgressBar();

    }

    interface HomePresenter{
        void initViews();

        void requestBanners();
        void requestCategories();
        void requestBestSellers();
    }

}
