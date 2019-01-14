package b2w.com.alodjinha;


import android.graphics.Paint;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import b2w.com.alodjinha.Async.SendReservationAsync;
import b2w.com.alodjinha.Interface.StringAsyncResponse;
import b2w.com.alodjinha.Util.Common;

public class ShowProduct extends AppCompatActivity implements StringAsyncResponse {

    SendReservationAsync asyncTaskSend = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        LoadViews();

    }

    private void LoadViews() {

        ImageView product_image = (ImageView) findViewById(R.id.product_image);
        Picasso.with(ShowProduct.this)
                .load(Common.selectedProduct.getUrlImage())
                .into(product_image, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });


        ImageButton back_button = (ImageButton) findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        TextView product_name = (TextView) findViewById(R.id.product_name);
        product_name.setText( Common.selectedProduct.getName() );

        TextView original_price = (TextView) findViewById(R.id.original_price);
        TextView new_price = (TextView) findViewById(R.id.new_price);

        String price = getString(R.string.from_text) + " " + Common.selectedProduct.getOriginalPrice();

        if ( price.contains(".") ) price = price.replace(".", ",");
        else price = price + ",00";

        original_price.setText(price);
        original_price.setPaintFlags(original_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        price = getString(R.string.for_text) + " " + Common.selectedProduct.getNewPrice().replace(".", ",");
        new_price.setText(price);


        TextView product_description = (TextView) findViewById(R.id.product_description);
        product_description.setText( Html.fromHtml(Common.selectedProduct.getDescription() ) );

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.reservation_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://alodjinha.herokuapp.com/produto/" + Common.selectedProduct.getId();

                //new GetUrlContentTask().execute(url);

                asyncTaskSend = new SendReservationAsync(ShowProduct.this);
                asyncTaskSend.delegate = ShowProduct.this;
                asyncTaskSend.execute(url);

            }
        });

    }

    @Override
    public void processFinishString(String response) {

        if ( response.equals(getString(R.string.success_text)) ){
            Toast.makeText( this, getString(R.string.success_message), Toast.LENGTH_SHORT ).show();
            onBackPressed();
        }
        else{
            Toast.makeText( this, getString(R.string.fail_message), Toast.LENGTH_SHORT ).show();
        }

    }
}
