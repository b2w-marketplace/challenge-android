package b2w.com.br.olodjinha.ui.queryresult;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import b2w.com.br.olodjinha.data.models.ProductDTO;

public interface ResultContract extends MvpView {

    void showProducts(List<ProductDTO> data);

    void showError();
}
