package br.com.lodjinha.alodjinha.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

import br.com.lodjinha.alodjinha.R;
import br.com.lodjinha.alodjinha.repository.api.ReservaProdutoApi;

/**
 * Created by douglasromano on 16/02/2017.
 */

public class DescricaoProdutoActivity extends AppCompatActivity {

    TextView txtDescNomeProduto;
    TextView txtDescPrecoDe;
    TextView txtDescPrecoPor;
    TextView txtDescDescricao;
    ImageView imgProduto;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_produto);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDesc);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Bundle bundle = getIntent().getExtras();

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatButtonReserva);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReservaProdutoApi api = new ReservaProdutoApi(DescricaoProdutoActivity.this, bundle.getInt("IdProduto"));

                api.execute();
            }
        });

        txtDescNomeProduto = (TextView) findViewById(R.id.txtDescNomeProduto);
        txtDescNomeProduto.setText(bundle.getString("NomeProduto"));

        txtDescPrecoDe = (TextView) findViewById(R.id.txtDescPrecoDe);
        txtDescPrecoDe.setText("De: "+String.valueOf(bundle.getDouble("PrecoDe")));

        txtDescPrecoPor = (TextView) findViewById(R.id.txtDescPrecoPor);
        txtDescPrecoPor.setText("Por "+String.valueOf(bundle.getDouble("PrecoPor")));

        String desc = bundle.getString("DescProduto");

        txtDescDescricao = (TextView) findViewById(R.id.txtDescDescricao);
        txtDescDescricao.setText(desc);

        Bitmap imgProdutoBmp = getBitmap(bundle.getString("ImgProduto"));

        imgProduto = (ImageView) findViewById(R.id.imgDescProduto);
        imgProduto.setImageBitmap(imgProdutoBmp);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static Bitmap getBitmap(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Bitmap d = BitmapFactory.decodeStream(is);
            is.close();
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}
