package br.com.b2wmarketplace.alodjinha.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class ProdutoViewHolder extends RecyclerView.ViewHolder{

    public ProdutoViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    protected abstract void clear();

    public void onBind(int position) {
        clear();
    }

}