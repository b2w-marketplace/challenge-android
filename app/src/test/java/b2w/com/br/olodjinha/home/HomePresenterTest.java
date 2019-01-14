package b2w.com.br.olodjinha.home;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import b2w.com.br.olodjinha.util.RxImmediateSchedulerRule;
import b2w.com.br.olodjinha.data.api.DataFacade;
import b2w.com.br.olodjinha.data.models.BannerDTO;
import b2w.com.br.olodjinha.data.models.BannerWrapper;
import b2w.com.br.olodjinha.data.models.CategoryDTO;
import b2w.com.br.olodjinha.data.models.CategoryWrapper;
import b2w.com.br.olodjinha.data.models.ProductDTO;
import b2w.com.br.olodjinha.data.models.ProductsWrapper;
import b2w.com.br.olodjinha.ui.home.HomeContract;
import b2w.com.br.olodjinha.ui.home.HomePresenter;
import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(JUnit4.class)
public class HomePresenterTest {

    @ClassRule
    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();

    private HomePresenter mHomePresenter;

    @Mock
    private HomeContract mHomeContract;

    @Mock
    private DataFacade mDataFacade;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mHomePresenter = new HomePresenter(mDataFacade);
        mHomePresenter.attachView(mHomeContract);
    }

    @Test
    public void verifyApiBannerResponse_Successful() {
        BannerWrapper bannerWrapper = new BannerWrapper();
        List<BannerDTO> bannerList = new ArrayList<>();
        bannerWrapper.setData(bannerList);

        doReturn(Observable.just(bannerWrapper)).when(mDataFacade).getBanners();

        mHomePresenter.getBanners();

        verify(mHomeContract).showBanners(bannerList);
    }

    @Test
    public void verifyApiCategoryResponse_Successful() {
        CategoryWrapper categoryWrapper = new CategoryWrapper();
        List<CategoryDTO> categoryList = new ArrayList<>();
        categoryWrapper.setData(categoryList);

        doReturn(Observable.just(categoryWrapper)).when(mDataFacade).getCategories();

        mHomePresenter.getCategories();

        verify(mHomeContract).showCategories(categoryList);
    }

    @Test
    public void verifyApiBestSellersResponse_Successful() {
        ProductsWrapper productsWrapper = new ProductsWrapper();
        List<ProductDTO> productList = new ArrayList<>();
        productsWrapper.setData(productList);

        doReturn(Observable.just(productsWrapper)).when(mDataFacade).getBestSellers();

        mHomePresenter.getBestSellers();

        verify(mHomeContract).showBestSellers(productList);
    }

    @Test
    public void verifyApiBannerResponse_Failure() {
        doReturn(Observable.error(new Exception())).when(mDataFacade).getBanners();

        mHomePresenter.getBanners();

        verify(mHomeContract, never()).showBanners(any());
        verify(mHomeContract).showError();
    }

    @Test
    public void verifyApiCategoriesResponse_Failure() {
        doReturn(Observable.error(new Exception())).when(mDataFacade).getCategories();

        mHomePresenter.getCategories();

        verify(mHomeContract, never()).showCategories(any());
        verify(mHomeContract).showError();
    }

    @Test
    public void verifyApiBestSellersResponse_Failure() {
        doReturn(Observable.error(new Exception())).when(mDataFacade).getBestSellers();

        mHomePresenter.getBestSellers();

        verify(mHomeContract, never()).showBestSellers(any());
        verify(mHomeContract).showError();
    }

}
