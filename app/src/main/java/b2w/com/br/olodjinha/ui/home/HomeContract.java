package b2w.com.br.olodjinha.ui.home;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import b2w.com.br.olodjinha.data.models.BannerDTO;
import b2w.com.br.olodjinha.data.models.ProductDTO;
import b2w.com.br.olodjinha.data.models.CategoryDTO;

public interface HomeContract extends MvpView {

    void showBanners(List<BannerDTO> data);

    void showCategories(List<CategoryDTO> data);

    void showBestSellers(List<ProductDTO> data);

    void showError();
}
