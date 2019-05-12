package br.com.douglas.fukuhara.lodjinha.interfaces;

import java.util.List;

import br.com.douglas.fukuhara.lodjinha.network.vo.BannerDataVo;
import br.com.douglas.fukuhara.lodjinha.network.vo.CategoryDataVo;
import br.com.douglas.fukuhara.lodjinha.network.vo.ProductDataVo;

public interface HomeContract {
    interface View {

        void onBannerContentLoaded(List<BannerDataVo> listOfData);
        void onBannerContentFailed(String message);
        void onBannerContentFailedGenericError();

        void onCategoryContentLoaded(List<CategoryDataVo> listOfData);
        void onCategoryContentFailed(String message);
        void onCategoryContentFailedGenericError();

        void onBestSellerContentLoaded(List<ProductDataVo> listOfData);
        void onBestSellerContentFailed(String message);
        void onBestSellerContentFailedGenericError();

        void showBannerLoading();
        void hideBannerLoading();
        void showCategoryLoading();
        void hideCategoryLoading();
        void showBestSellerProductsLoading();
        void hideBestSellerProductsLoading();
    }

    interface Presenter {

        void fetchContent();
        void disposeAll();
    }
}
