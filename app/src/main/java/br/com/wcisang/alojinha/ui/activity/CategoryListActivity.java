package br.com.wcisang.alojinha.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import br.com.wcisang.alojinha.R;
import br.com.wcisang.alojinha.databinding.ActivityCategoryListBinding;
import br.com.wcisang.alojinha.model.Category;
import br.com.wcisang.alojinha.model.Product;
import br.com.wcisang.alojinha.ui.adapter.ProductAdapter;
import br.com.wcisang.alojinha.util.EndlessScrollListener;
import br.com.wcisang.alojinha.viewmodel.CategoryListViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WCisang on 09/06/2018.
 */
public class CategoryListActivity extends AppCompatActivity {

    public static final String CATEGORY = "category";
    private CategoryListViewModel viewModel;

    @BindView(R.id.recyclerview_product)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private ProductAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCategoryListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_category_list);
        ButterKnife.bind(this);
        setupToolbar();
        Category category = (Category) getIntent().getSerializableExtra(CATEGORY);
        binding.setCategory(category);
        startViewModel(category);
        setupList(new ArrayList<>());
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void startViewModel(Category category) {
        viewModel = ViewModelProviders.of(this).get(CategoryListViewModel.class);
        viewModel.getListMutableLiveData().observe(this, getProductListObserver());
        viewModel.init(category);
    }

    private Observer<List<Product>> getProductListObserver() {
        return this::addMoreData;
    }

    private void setupList(List<Product> products) {
        if (products == null)
            return;
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(manager);
        adapter = new ProductAdapter(products, this::callDetail);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new EndlessScrollListener(manager) {
                                             @Override
                                             public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                                                 loadMore(totalItemsCount);
                                             }
                                         }
        );
    }

    private void addMoreData(List<Product> products) {
        if (products == null)
            return;
        progressBar.setVisibility(View.GONE);
        adapter.addToList(products);
    }

    private void callDetail(Product product) {
        Intent it = new Intent(this, ProductDetailActivity.class);
        it.putExtra(ProductDetailActivity.PRODUCT, product);
        startActivity(it);
    }

    private void loadMore(int offset) {
        viewModel.startService(offset);
    }
}
