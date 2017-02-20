package freitas.dias.lodjinha.ui.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import freitas.dias.lodjinha.R;
import freitas.dias.lodjinha.adapter.CategoriesAdapter;
import freitas.dias.lodjinha.adapter.ProductsAdapter;
import freitas.dias.lodjinha.api.model.Banner;
import freitas.dias.lodjinha.api.model.Banners;
import freitas.dias.lodjinha.api.model.Categories;
import freitas.dias.lodjinha.api.model.Category;
import freitas.dias.lodjinha.api.model.Product;
import freitas.dias.lodjinha.api.model.Products;
import freitas.dias.lodjinha.controller.BannerController;
import freitas.dias.lodjinha.controller.CategoriesController;
import freitas.dias.lodjinha.controller.ProductsSoldController;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    public static final String TAG = HomeFragment.class.getSimpleName();

    private LinearLayout linearLayoutLoadingViewPager;
    private LinearLayout linearLayoutViewPager;
    private CarouselView carouselView;

    private RecyclerView recyclerViewCategories;
    private RecyclerView recyclerViewProducts;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linearLayoutLoadingViewPager = (LinearLayout) view.findViewById(R.id.linearHeaderProgress);
        linearLayoutViewPager = (LinearLayout) view.findViewById(R.id.linearViewPager);
        carouselView = (CarouselView) view.findViewById(R.id.carouselView);

        linearLayoutLoadingViewPager.setVisibility(View.VISIBLE);
        linearLayoutViewPager.setVisibility(View.GONE);


        recyclerViewCategories = (RecyclerView) view.findViewById(R.id.recyclerView_categories);
        recyclerViewProducts = (RecyclerView) view.findViewById(R.id.recyclerView_product);

        BannerController bannerController = new BannerController();
        bannerController.getListBanners();

       CategoriesController categoriesController = new CategoriesController();
        categoriesController.getListCategories();

        ProductsSoldController productsSoldController = new ProductsSoldController();
        productsSoldController.getListCategoriesSold();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventBanner(Banners banners) {

        if(banners != null){
            final List<Banner> bannerList = banners.getBanners();

            linearLayoutLoadingViewPager.setVisibility(View.GONE);
            linearLayoutViewPager.setVisibility(View.VISIBLE);

            final ImageListener imageListener = new ImageListener() {
                @Override
                public void setImageForPosition(int position, ImageView imageView) {
                    Picasso.with(getActivity())
                            .load(bannerList.get(position).getUrlImage())
                            .into(imageView);
                    imageView.setTag(bannerList.get(position).getLinkImage());
                    imageView.setOnClickListener(HomeFragment.this);
                }
            };

            carouselView.setImageListener(imageListener);
            carouselView.setPageCount(bannerList.size());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventCategory(Categories categories) {

        if(categories != null){

            List<Category> categoryList = categories.getCategories();

            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerViewCategories.setHasFixedSize(true);
            recyclerViewCategories.setLayoutManager(layoutManager);
            recyclerViewCategories.setItemAnimator(new DefaultItemAnimator());

            CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getActivity(), categoryList);
            recyclerViewCategories.setAdapter(categoriesAdapter);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventProduct(Products products) {
        if(products != null){

            List<Product> productList = products.getProducts();

            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            recyclerViewProducts.setHasFixedSize(true);
            recyclerViewProducts.setLayoutManager(layoutManager);
            recyclerViewProducts.setItemAnimator(new DefaultItemAnimator());

            ProductsAdapter categoriesAdapter = new ProductsAdapter(getActivity(), productList);
            recyclerViewProducts.setAdapter(categoriesAdapter);
        }
    }

    @Subscribe
    public void onMessageEvent(Throwable throwable) {
        Toast.makeText(getActivity(), R.string.failure, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if(view instanceof ImageView){
            ImageView imageView = (ImageView) view;
            String url = (String) imageView.getTag();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
    }
}
