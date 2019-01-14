package b2w.com.br.olodjinha.data.api;

import java.util.Map;

import b2w.com.br.olodjinha.data.models.BannerWrapper;
import b2w.com.br.olodjinha.data.models.CategoryWrapper;
import b2w.com.br.olodjinha.data.models.ProductDTO;
import b2w.com.br.olodjinha.data.models.ProductsWrapper;
import io.reactivex.Observable;

import static b2w.com.br.olodjinha.data.api.RetrofitClient.getClient;

public class DataFacade implements API{

    @Override
    public Observable<ProductsWrapper> getProductsByCategory(Map<String, Object> query) {
        return getClient().getProductsByCategory(query);
    }

    public Observable<ProductDTO> getProductById(Integer productId) {
        return getClient().getProductById(productId);
    }

    @Override
    public Observable<Object> doReservation(Integer productId) {
        return getClient().doReservation(productId);
    }

    @Override
    public Observable<BannerWrapper> getBanners() {
        return getClient().getBanners();
    }

    @Override
    public Observable<CategoryWrapper> getCategories() {
        return getClient().getCategories();
    }

    @Override
    public Observable<ProductsWrapper> getBestSellers() {
        return getClient().getBestSellers();
    }
}
