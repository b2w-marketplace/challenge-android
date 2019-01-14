package b2w.com.br.olodjinha.productdetail;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import b2w.com.br.olodjinha.data.api.DataFacade;
import b2w.com.br.olodjinha.data.models.BannerDTO;
import b2w.com.br.olodjinha.data.models.BannerWrapper;
import b2w.com.br.olodjinha.data.models.ProductDTO;
import b2w.com.br.olodjinha.data.models.ProductsWrapper;
import b2w.com.br.olodjinha.ui.home.HomeContract;
import b2w.com.br.olodjinha.ui.home.HomePresenter;
import b2w.com.br.olodjinha.ui.productdetail.ProductDetailContract;
import b2w.com.br.olodjinha.ui.productdetail.ProductDetailPresenter;
import b2w.com.br.olodjinha.util.RxImmediateSchedulerRule;
import io.reactivex.Observable;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(JUnit4.class)
public class ProductDetailPresenterTest {

    @ClassRule
    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();

    private ProductDetailPresenter mProductDetailPresenter;

    @Mock
    private ProductDetailContract mProductDetailContract;

    @Mock
    private DataFacade mDataFacade;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mProductDetailPresenter = new ProductDetailPresenter(mDataFacade);
        mProductDetailPresenter.attachView(mProductDetailContract);
    }

    @Test
    public void verifyApiProductDetailResponse_Successful() {
        ProductDTO mProduct = new ProductDTO();

        doReturn(Observable.just(mProduct)).when(mDataFacade).getProductById(1);

        mProductDetailPresenter.getProductById(1);

        verify(mProductDetailContract).showProductDetails(mProduct);
    }
}
