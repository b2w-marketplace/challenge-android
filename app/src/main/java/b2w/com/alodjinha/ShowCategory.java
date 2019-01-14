package b2w.com.alodjinha;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import b2w.com.alodjinha.Adapter.ProductsAdapter;
import b2w.com.alodjinha.Async.GetProductUrlAsync;
import b2w.com.alodjinha.Interface.ProductAsyncResponse;
import b2w.com.alodjinha.Model.Product;
import b2w.com.alodjinha.Util.Common;

public class ShowCategory extends AppCompatActivity implements ProductAsyncResponse {

    private GetProductUrlAsync asyncTaskProduct = null;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private LinearLayoutManager linearLayoutManager;
    private ProductsAdapter adapter;
    private ProgressBar progressBar;

    private List<Product> listProducts = new ArrayList<>();
    private int offset = 0;
    private Boolean isScrolling = false;
    private int currentItems, totalItems, scrollOutItems;
    private int offsetSize = 20;

    private boolean loadMore = true;

    String token = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_category);

        LoadViews();

        LoadScreen();


        getData();

    }

    private void LoadViews() {

        if ( getSupportActionBar() != null ){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(Common.selectedCategory.getDescription());
        }

        progressBar = (ProgressBar) findViewById(R.id.progress);

        recyclerView = (RecyclerView) findViewById(R.id.products_list_recycler);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager( linearLayoutManager );
        adapter = new ProductsAdapter( this, listProducts );
        recyclerView.setAdapter(adapter);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }


    private void GetCategoryProducts( int offset ) {

        String url = "https://alodjinha.herokuapp.com/produto?offset=" + offset
                + "&limit=" + (offset + offsetSize)
                + "&categoriaId=" + Common.selectedCategory.getId();

        asyncTaskProduct = new GetProductUrlAsync(this);
        asyncTaskProduct.delegate = this;
        asyncTaskProduct.execute(url);

    }

    @Override
    public void processFinishProduct(final List<Product> products){

        listProducts.addAll(products);
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);

        offset += offsetSize;

        if ( products.size() < offsetSize ) loadMore = false;

    }


    private void LoadScreen(){


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItems = linearLayoutManager.getChildCount();
                totalItems = linearLayoutManager.getItemCount();
                scrollOutItems = linearLayoutManager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItems + scrollOutItems == totalItems))
                {
                    isScrolling = false;
                    getData();
                }

            }
        });

    }

    private void getData() {

        if ( loadMore )
            progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if ( loadMore ){

                    GetCategoryProducts( offset );
                }

                progressBar.setVisibility(View.GONE);


            }
        }, 1000);

    }

}
