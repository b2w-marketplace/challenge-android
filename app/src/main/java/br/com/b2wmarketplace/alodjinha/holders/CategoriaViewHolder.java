package br.com.b2wmarketplace.alodjinha.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import br.com.b2wmarketplace.alodjinha.R;

public class CategoriaViewHolder extends RecyclerView.ViewHolder {
    private SimpleDraweeView imgFotoCategoria;
    private TextView txtDescricao;

    public CategoriaViewHolder(View view) {
        super(view);
        imgFotoCategoria = view.findViewById(R.id.imgFotoCategoria);
        txtDescricao = view.findViewById(R.id.txtNomeDescricao);
    }

    public void setFotoCategoria(String urlimagem) {
        imgFotoCategoria.setImageURI(urlimagem.replace("http://", "https://"));
    }

    public void setDescricao(String descricao){ txtDescricao.setText(descricao);}
}
