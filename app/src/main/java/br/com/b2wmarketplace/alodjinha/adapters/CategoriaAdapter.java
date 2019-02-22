package br.com.b2wmarketplace.alodjinha.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.b2wmarketplace.alodjinha.MainActivity;
import br.com.b2wmarketplace.alodjinha.R;
import br.com.b2wmarketplace.alodjinha.holders.CategoriaViewHolder;
import br.com.b2wmarketplace.alodjinha.models.Categoria;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaViewHolder> {
    private List<Categoria> listaCategoria;
    private Context context;

    public CategoriaAdapter(ArrayList<Categoria> listaCategoria, Context context) {
        this.listaCategoria = listaCategoria;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_categoria, parent, false);

        return new CategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        Categoria categoria = listaCategoria.get(position);
        final int idcategoria = categoria.getId();
        final String descricao = categoria.getDescricao();
        holder.setFotoCategoria(categoria.getUrlimagem());
        holder.setDescricao(descricao);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).abreCategoriaProduto (idcategoria, descricao);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listaCategoria.size();
    }
}