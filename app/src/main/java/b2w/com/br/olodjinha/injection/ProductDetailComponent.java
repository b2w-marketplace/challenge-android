package b2w.com.br.olodjinha.injection;

import b2w.com.br.olodjinha.productdetail.ProductDetailActivity;
import b2w.com.br.olodjinha.productdetail.ProductDetailPresenter;
import dagger.Component;

@Component(modules = ScreenFlowModule.class)
public interface ProductDetailComponent {

    void inject(ProductDetailActivity productDetailActivity);

    ProductDetailPresenter presenter();
}
