package b2w.com.br.olodjinha.result;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import b2w.com.br.olodjinha.data.api.DataFacade;
import b2w.com.br.olodjinha.data.models.ProductDTO;
import b2w.com.br.olodjinha.data.models.ProductsWrapper;
import b2w.com.br.olodjinha.ui.queryresult.ResultContract;
import b2w.com.br.olodjinha.ui.queryresult.ResultPresenter;
import b2w.com.br.olodjinha.util.RxImmediateSchedulerRule;
import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(JUnit4.class)
public class ResultPresenterTest {

    @ClassRule
    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();

    private ResultPresenter mResultPresenter;

    @Mock
    private ResultContract mResultContract;

    @Mock
    private DataFacade mDataFacade;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mResultPresenter = new ResultPresenter(mDataFacade);
        mResultPresenter.attachView(mResultContract);
    }

    @Test
    public void verifyApiQueryProductsResponse_Successful() {
        ProductsWrapper productsWrapper = new ProductsWrapper();
        List<ProductDTO> productList = new ArrayList<>();
        productsWrapper.setData(productList);

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("categoriaId", 1);
        queryMap.put("offset", 1);
        queryMap.put("limit", 20);

        doReturn(Observable.just(productsWrapper)).when(mDataFacade).getProductsByCategory(queryMap);

        mResultPresenter.getProducts(1, 1);

        verify(mResultContract).showProducts(productList);
    }

    @Test
    public void verifyApiQueryProductsResponse_Failure() {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("categoriaId", 1);
        queryMap.put("offset", 1);
        queryMap.put("limit", 20);

        doReturn(Observable.error(new Exception())).when(mDataFacade).getProductsByCategory(queryMap);

        mResultPresenter.getProducts(1, 1);

        verify(mResultContract, never()).showProducts(any());
        verify(mResultContract).showError();
    }
}
