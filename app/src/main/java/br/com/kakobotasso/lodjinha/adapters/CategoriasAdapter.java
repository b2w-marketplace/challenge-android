package br.com.kakobotasso.lodjinha.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.kakobotasso.lodjinha.R;
import br.com.kakobotasso.lodjinha.models.Banner;
import br.com.kakobotasso.lodjinha.models.Categoria;

public class CategoriasAdapter extends RecyclerView.Adapter {
    private List<Categoria> categorias;
    private Context context;

    public CategoriasAdapter(List<Categoria> categorias, Context context) {
        this.categorias = categorias;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_categoria, parent, false);

        BannersViewHolder holder = new BannersViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final BannersViewHolder holder = (BannersViewHolder) viewHolder;
        Categoria categoria = categorias.get(position);

        holder.descricao.setText(categoria.getDescricao());

        Picasso.with(context).load(categoria.getImagem()).into(holder.imagem, new Callback() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onError() {
            }
        });
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public class BannersViewHolder extends RecyclerView.ViewHolder {
        final ImageView imagem;
        final TextView descricao;

        public BannersViewHolder(View view) {
            super(view);
            imagem = (ImageView) view.findViewById(R.id.imagem_categoria);
            descricao = (TextView) view.findViewById(R.id.descricao_categoria);
        }

    }
}
