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
import br.com.bsavoini.lodjinha.R;
import br.com.bsavoini.lodjinha.adapters.ProductsAdapter;
import br.com.bsavoini.lodjinha.api.RetrofitInstance;
import br.com.bsavoini.lodjinha.api.model.BannersResponse;
import br.com.bsavoini.lodjinha.api.model.CategoriesResponse;
import br.com.bsavoini.lodjinha.api.model.ProductModel;
import br.com.bsavoini.lodjinha.api.model.ProductsResponse;
import br.com.bsavoini.lodjinha.catalog.CatalogActivity;
import br.com.bsavoini.lodjinha.adapters.BannerPagerAdapter;
import br.com.bsavoini.lodjinha.adapters.CategoriesAdapter;
import br.com.bsavoini.lodjinha.home.interfaces.BannerClickCallback;
import br.com.bsavoini.lodjinha.home.interfaces.CategoryClickCallback;
import br.com.bsavoini.lodjinha.product.ProductActivity;
import br.com.bsavoini.lodjinha.product.ProductClickCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements BannerClickCallback , CategoryClickCallback, ProductClickCallback {
    private ViewPager bannersViewPager;
    RecyclerView categoriesRecycler;
    RecyclerView bestSellersRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        bannersViewPager = view.findViewById(R.id.view_pager_banners);
        requestBanners();

        categoriesRecycler = view.findViewById(R.id.recyler_categories);
        LinearLayoutManager categoriesLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoriesRecycler.setLayoutManager(categoriesLayoutManager);

        requestCategories();

        bestSellersRecycler = view.findViewById(R.id.recyler_best_sellers);
        LinearLayoutManager bestSellerLayoutManager = new LinearLayoutManager(view.getContext());
        bestSellersRecycler.setLayoutManager(bestSellerLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.divider));
        bestSellersRecycler.addItemDecoration(dividerItemDecoration);

        requestBestSellers();

    }

    //TODO call request from interactor
    private void requestBanners() {
        Call<BannersResponse> bannersResponseCall = RetrofitInstance.getLodjinhaService().requestBanner();
        bannersResponseCall.enqueue(new Callback<BannersResponse>() {
            @Override
            public void onResponse(Call<BannersResponse> call, Response<BannersResponse> response) {

                bannersViewPager.setAdapter(new BannerPagerAdapter(response.body().getBannersArr(), HomeFragment.this));

            }

            @Override
            public void onFailure(Call<BannersResponse> call, Throwable t) {
            }
        });
    }

    //TODO call request from interactor
    private void requestCategories() {
        Call<CategoriesResponse> categoriesResponseCall = RetrofitInstance.getLodjinhaService().requestCategories();
        categoriesResponseCall.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {

                categoriesRecycler.setAdapter(new CategoriesAdapter(response.body().getCategoriesArr(), HomeFragment.this));

            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {
            }
        });
    }

    //TODO call request from interactor
    private void requestBestSellers() {
        Call<ProductsResponse> productsResponseCall = RetrofitInstance.getLodjinhaService().requestBestSellers();
        productsResponseCall.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {

                bestSellersRecycler.setAdapter(new ProductsAdapter(response.body().getProductsArr(), HomeFragment.this));
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
            }
        });
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
}
