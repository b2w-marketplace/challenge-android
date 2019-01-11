package br.com.bsavoini.lodjinha.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import br.com.bsavoini.lodjinha.R;
import br.com.bsavoini.lodjinha.adapters.BannerPagerAdapter;
import br.com.bsavoini.lodjinha.adapters.CategoriesAdapter;
import br.com.bsavoini.lodjinha.adapters.ProductsAdapter;
import br.com.bsavoini.lodjinha.api.model.BannerModel;
import br.com.bsavoini.lodjinha.api.model.CategoryModel;
import br.com.bsavoini.lodjinha.api.model.ProductModel;
import br.com.bsavoini.lodjinha.callbacks.BannerClickCallback;
import br.com.bsavoini.lodjinha.callbacks.CategoryClickCallback;
import br.com.bsavoini.lodjinha.callbacks.ProductClickCallback;
import br.com.bsavoini.lodjinha.catalog.CatalogActivity;
import br.com.bsavoini.lodjinha.product.ProductActivity;

import java.util.List;

public class HomeFragment extends Fragment implements HomeContract.HomeView,
        BannerClickCallback,
        CategoryClickCallback,
        ProductClickCallback {

    private View viewCreated;
    private ViewPager bannersViewPager;
    private RecyclerView categoriesRecycler;
    private RecyclerView bestSellersRecycler;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.viewCreated = view;

        HomeContract.HomePresenter presenter = new HomePresenterImpl(this, new HomeInteractorImpl());
        presenter.initViews();

        presenter.requestBanners();
        presenter.requestCategories();
        presenter.requestBestSellers();
    }


    @Override
    public void onClickBanner(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        getActivity().startActivity(intent);
    }

    @Override
    public void onClickCategory(String categoryName, int categoryId) {
        Intent intent = new Intent(getActivity(), CatalogActivity.class);
        intent.putExtra("categoryName", categoryName);
        intent.putExtra("categoryId", categoryId);
        getActivity().startActivity(intent);
    }

    @Override
    public void onClickProduct(ProductModel productModel) {
        Intent intent = new Intent(getActivity(), ProductActivity.class);
        intent.putExtra("product", productModel);
        startActivity(intent);
    }

    @Override
    public void initViewPager() {
        bannersViewPager = viewCreated.findViewById(R.id.view_pager_banners);
        progressBar = viewCreated.findViewById(R.id.progress_bar);
    }

    @Override
    public void initRecyclerViews() {
        categoriesRecycler = viewCreated.findViewById(R.id.recyler_categories);
        LinearLayoutManager categoriesLayoutManager = new LinearLayoutManager(viewCreated.getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoriesRecycler.setLayoutManager(categoriesLayoutManager);

        bestSellersRecycler = viewCreated.findViewById(R.id.recyler_best_sellers);
        LinearLayoutManager bestSellerLayoutManager = new LinearLayoutManager(viewCreated.getContext());
        bestSellersRecycler.setLayoutManager(bestSellerLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(viewCreated.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(viewCreated.getContext(), R.drawable.divider));
        bestSellersRecycler.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void updateBannersAdapter(List<BannerModel> bannersArr) {
        bannersViewPager.setAdapter(new BannerPagerAdapter(bannersArr, this));
    }

    @Override
    public void updateCategoriesAdapter(List<CategoryModel> categoriesArr) {
        categoriesRecycler.setAdapter(new CategoriesAdapter(categoriesArr, this));
    }

    @Override
    public void updateProductAdapter(List<ProductModel> productsArr) {
        bestSellersRecycler.setAdapter(new ProductsAdapter(productsArr, this));
    }

    @Override
    public void showErrorMsg() {
        Toast.makeText(getActivity(), "Ops! Sem conexão. Tente novamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
