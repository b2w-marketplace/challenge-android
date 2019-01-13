package b2w.com.br.olodjinha.productdetail;

import com.hannesdorfmann.mosby.mvp.MvpView;

import b2w.com.br.olodjinha.data.models.ProductDTO;

public interface ProductDetailContract extends MvpView {

    void showProductDetails(ProductDTO data);

    void showSuccessfulReservation();

    void showProgress(boolean show);
}
