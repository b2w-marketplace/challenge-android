package br.com.bsavoini.lodjinha.product;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.bsavoini.lodjinha.R;
import br.com.bsavoini.lodjinha.api.model.ProductModel;
import com.squareup.picasso.Picasso;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ProductModel productModel = (ProductModel) getIntent().getSerializableExtra("product");
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


    }
}
