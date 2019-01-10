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

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        showToolbarBackButton();

        final ProductModel productModel = (ProductModel) getIntent().getSerializableExtra("product");
        setTitle(productModel.getName());

        ImageView productImg = findViewById(R.id.img_product);

        Picasso.with(this)
                .load(productModel.getImageURL())
                .placeholder(R.drawable.tag_menu)
                .into(productImg);

        TextView txtName = findViewById(R.id.txt_name);
        txtName.setText(productModel.getName());

        TextView txtOriginalPrice = findViewById(R.id.txt_original_price);
        txtOriginalPrice.setText("R$" + productModel.getOriginalPrice());

        TextView txtCurrentPrice = findViewById(R.id.txt_current_price);
        txtCurrentPrice.setText("R$" + productModel.getCurrentPrice());

        TextView txtDescription = findViewById(R.id.txt_description);
        Spanned formatedHtmlStr = Html.fromHtml(productModel.getDescription());
        txtDescription.setText(formatedHtmlStr);

        FloatingActionButton fab = findViewById(R.id.fab_reserve);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reserveProduct(productModel.getId());
            }
        });
    }

    //TODO call request from interactor
    private void reserveProduct(int productId) {
        Call<ReservationResponse> productsResponseCall = RetrofitInstance.getLodjinhaService().requestProductReservation(productId);

        productsResponseCall.enqueue(new Callback<ReservationResponse>() {
            @Override
            public void onResponse(Call<ReservationResponse> call, Response<ReservationResponse> response) {

                showDialog(response.body().getResult());

            }

            @Override
            public void onFailure(Call<ReservationResponse> call, Throwable t) {
            }
        });
    }

    //TODO     android.view.WindowManager$BadTokenException: Unable to add window -- token android.os.BinderProxy@8432922 is not valid; is your activity running?
    private void showDialog(String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(str)
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

    public void showToolbarBackButton() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
