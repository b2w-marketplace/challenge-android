package br.com.b2wmarketplace.alodjinha.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.b2wmarketplace.alodjinha.R;
import br.com.b2wmarketplace.alodjinha.holders.BannerViewHolder;
import br.com.b2wmarketplace.alodjinha.models.Banner;

public class BannerAdapter extends RecyclerView.Adapter<BannerViewHolder> {
    private List<Banner> listaBanner;
    private Context context;

    public BannerAdapter(ArrayList<Banner> listaBanner, Context context) {
        this.listaBanner = listaBanner;
        this.context = context;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_banner, parent, false);

        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        Banner banner = listaBanner.get(position);
        holder.setFotoBanner(banner.getUrlimagem());
    }

    @Override
    public int getItemCount() {
        return listaBanner.size();
    }
}