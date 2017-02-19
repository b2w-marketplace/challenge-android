package br.com.kakobotasso.lodjinha.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.kakobotasso.lodjinha.R;
import br.com.kakobotasso.lodjinha.models.Banner;

public class BannersAdapter extends RecyclerView.Adapter {
    private List<Banner> banners;
    private Context context;

    public BannersAdapter(List<Banner> banners, Context context) {
        this.banners = banners;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_banner, parent, false);

        BannersViewHolder holder = new BannersViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final BannersViewHolder holder = (BannersViewHolder) viewHolder;
        Banner banner = banners.get(position);

        Picasso.with(context).load(banner.getUrlImagem()).into(holder.imagem, new Callback() {
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
        return banners.size();
    }

    public class BannersViewHolder extends RecyclerView.ViewHolder {
        final ImageView imagem;

        public BannersViewHolder(View view) {
            super(view);
            imagem = (ImageView) view.findViewById(R.id.imagem_banner);
        }

    }
}
