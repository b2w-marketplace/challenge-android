package b2w.com.br.olodjinha.data.api;

import java.util.Map;

import b2w.com.br.olodjinha.data.models.ProductDTO;
import b2w.com.br.olodjinha.data.models.ProductsWrapper;
import io.reactivex.Observable;

import static b2w.com.br.olodjinha.data.api.RetrofitClient.*;

public class DataFacade {

    public static Observable<ProductsWrapper> queryProducts(Map<String, Object> query) {
        return getClient().getProductsByCategory(query);
    }

    public static Observable<ProductDTO> getProductById(Integer productId) {
        return getClient().getProductById(productId);
    }

    public static Observable<Object> doReservation(Integer productId) {
        return getClient().doReservation(productId);
    }
}
