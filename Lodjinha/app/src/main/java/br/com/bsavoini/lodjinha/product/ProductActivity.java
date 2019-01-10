package br.com.bsavoini.lodjinha.product;

import android.content.DialogInterface;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.bsavoini.lodjinha.R;
import br.com.bsavoini.lodjinha.api.RetrofitInstance;
import br.com.bsavoini.lodjinha.api.model.ProductModel;
import br.com.bsavoini.lodjinha.api.model.ProductsResponse;
import br.com.bsavoini.lodjinha.api.model.ReservationResponse;
import br.com.bsavoini.lodjinha.catalog.CatalogActivity;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.lang.reflect.Type;

public class ProductActivity extends AppCompatActivity implements ProductContract.ProductView {
    private ProductContract.ProductPresenter presenter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        presenter = new ProductPresenterImpl(ProductActivity.this, new ProductInteractorImpl());
        presenter.initViews();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void enableToolbarBackButton() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public ProductModel retrieveProductModel() {
        return (ProductModel) getIntent().getSerializableExtra("product");
    }

    @Override
    public void bindTollbarTitleWithProduct(String name) {
        setTitle(name);
    }

    @Override
    public void bindProductImageView(String imageUrl) {
        ImageView productImg = findViewById(R.id.img_product);

        Picasso.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.tag_menu)
                .into(productImg);
    }

    @Override
    public void bindProductNameTextView(String name) {
        TextView txtName = findViewById(R.id.txt_name);
        txtName.setText(name);
    }

    @Override
    public void bindOriginalPriceTextView(String price) {
        TextView txtOriginalPrice = findViewById(R.id.txt_original_price);
        txtOriginalPrice.setText(price);
    }

    @Override
    public void bindCurrentPriceTextView(String price) {
        TextView txtCurrentPrice = findViewById(R.id.txt_current_price);
        txtCurrentPrice.setText(price);
    }

    @Override
    public void bindProductDescriptionTextView(String description) {
        TextView txtDescription = findViewById(R.id.txt_description);
        Spanned formatedHtmlStr = Html.fromHtml(description);
        txtDescription.setText(formatedHtmlStr);
    }

    @Override
    public void initFab(final int productId) {
        fab = findViewById(R.id.fab_reserve);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.requestProductReservation(productId);
            }
        });
    }

    @Override
    public void disableFabClick() {
        fab.setEnabled(false);
    }

    @Override
    public void enableFabClick() {
        fab.setEnabled(true);
    }

    //TODO     android.view.WindowManager$BadTokenException: Unable to add window -- token android.os.BinderProxy@8432922 is not valid; is your activity running?
    @Override
    public void showSucessDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ProductActivity.this.finish();
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        ProductActivity.this.finish();
                    }
                })
                .show();
    }

    //TODO     android.view.WindowManager$BadTokenException: Unable to add window -- token android.os.BinderProxy@8432922 is not valid; is your activity running?
    @Override
    public void showErrorDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
