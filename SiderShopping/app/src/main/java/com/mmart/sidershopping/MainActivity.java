package com.mmart.sidershopping;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String[] imageUrls = new String[]{
            "https://cdn.pixabay.com/photo/2016/11/11/23/34/cat-1817970_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/12/21/12/26/glowworm-3031704_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/12/24/09/09/road-3036620_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/11/07/00/07/fantasy-2925250_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/10/10/15/28/butterfly-2837589_960_720.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, imageUrls);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager, true);

        viewPager.setAdapter(adapter);

        setCategories();
        setBestSellers();
    }

    private void setBestSellers() {
        RecyclerView bestSellerList = (RecyclerView) findViewById(R.id.best_seller_list);
        LinearLayoutManager layoutManagerBestSeller = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        bestSellerList.setLayoutManager(layoutManagerBestSeller);

        ArrayList<BestSeller> bestSellers = new ArrayList<BestSeller>();

        bestSellers.add(new BestSeller(1,
                "Produto 1",
                "De: 9999,99",
                "Por: 9998,99",
                "https://images-americanas.b2w.io/produtos/01/00/item/133204/8/133204824_1GG.jpg"));

        bestSellers.add(new BestSeller(1,
                "Produto 1",
                "De: 9999,99",
                "Por: 9998,99",
                "https://images-americanas.b2w.io/produtos/01/00/item/133204/8/133204824_1GG.jpg"));

        bestSellers.add(new BestSeller(1,
                "Produto 1",
                "De: 9999,99",
                "Por: 9998,99",
                "https://images-americanas.b2w.io/produtos/01/00/item/133204/8/133204824_1GG.jpg"));

        bestSellers.add(new BestSeller(1,
                "Produto 1",
                "De: 9999,99",
                "Por: 9998,99",
                "https://images-americanas.b2w.io/produtos/01/00/item/133204/8/133204824_1GG.jpg"));

        bestSellers.add(new BestSeller(1,
                "Produto 1",
                "De: 9999,99",
                "Por: 9998,99",
                "https://images-americanas.b2w.io/produtos/01/00/item/133204/8/133204824_1GG.jpg"));

        BestSellerAdapter bestSellerAdapter = new BestSellerAdapter(this, bestSellers);
        bestSellerList.setAdapter(bestSellerAdapter);
    }

    private void setCategories() {
        ArrayList<Category> categories = new ArrayList<Category>();

        RecyclerView lista = (RecyclerView) findViewById(R.id.lista);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        lista.setLayoutManager(layoutManager);

        categories.add(new Category(1, "Categoria 1", "https://images-americanas.b2w.io/produtos/01/00/item/133204/8/133204824_1GG.jpg"));
        categories.add(new Category(2, "Categoria 2", "https://images-americanas.b2w.io/produtos/01/00/item/133204/8/133204824_1GG.jpg"));
        categories.add(new Category(3, "Categoria 3", "https://images-americanas.b2w.io/produtos/01/00/item/133204/8/133204824_1GG.jpg"));
        categories.add(new Category(4, "Categoria 4", "https://images-americanas.b2w.io/produtos/01/00/item/133204/8/133204824_1GG.jpg"));
        categories.add(new Category(5, "Categoria 5", "https://images-americanas.b2w.io/produtos/01/00/item/133204/8/133204824_1GG.jpg"));
        categories.add(new Category(6, "Categoria 6", "https://images-americanas.b2w.io/produtos/01/00/item/133204/8/133204824_1GG.jpg"));

        CategoryAdapter categoryAdapter = new CategoryAdapter(categories, this);
        lista.setAdapter(categoryAdapter);
    }
}
