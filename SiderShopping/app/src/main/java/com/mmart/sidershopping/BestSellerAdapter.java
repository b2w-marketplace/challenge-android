package com.mmart.sidershopping;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BestSellerAdapter extends RecyclerView.Adapter<BestSellerAdapter.ViewHolder> {

    private ArrayList<BestSeller> mBestSellers;
    private Context mContext;

    public BestSellerAdapter(Context context, ArrayList<BestSeller> bestSellers) {
        this.mContext = context;
        this.mBestSellers = bestSellers;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.best_seller_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final BestSeller bestSeller = mBestSellers.get(position);

        Picasso.get()
                .load(bestSeller.getmImage())
                .fit()
                .centerCrop()
                .into(holder.image);

        holder.name.setText(bestSeller.getmName());
        holder.oldPrice.setText(bestSeller.getmOldPrice());
        holder.newPrice.setText(bestSeller.getmNewPrice());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d(TAG, "onClick: clicked on an image: " + mNames.get(position));
                Toast.makeText(mContext, bestSeller.getmName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBestSellers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        TextView oldPrice;
        TextView newPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.best_seller_image);
            name = itemView.findViewById(R.id.best_seller_name);
            newPrice = itemView.findViewById(R.id.best_seller_price_for);
            oldPrice = itemView.findViewById(R.id.best_seller_price_until);

        }
    }
}
