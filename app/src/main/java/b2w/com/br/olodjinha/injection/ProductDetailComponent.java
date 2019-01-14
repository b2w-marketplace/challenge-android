package b2w.com.br.olodjinha.injection;

import b2w.com.br.olodjinha.ui.productdetail.ProductDetailActivity;
import b2w.com.br.olodjinha.ui.productdetail.ProductDetailPresenter;
import dagger.Component;

@Component(modules = {ScreenFlowModule.class, NetworkModule.class})
public interface ProductDetailComponent {

    void inject(ProductDetailActivity productDetailActivity);

    ProductDetailPresenter presenter();
}
