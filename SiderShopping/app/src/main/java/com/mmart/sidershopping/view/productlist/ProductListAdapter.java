package com.mmart.sidershopping.view.productlist;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmart.sidershopping.ItemClickListener;
import com.mmart.sidershopping.R;
import com.mmart.sidershopping.model.entity.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ProductListAdapter extends PagedListAdapter<Product, ProductListAdapter.ProductViewHolder> {

    private Context context;
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private static ItemClickListener itemClickListener;

    protected ProductListAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item_list, viewGroup, false);

        ProductViewHolder viewHolder = new ProductListAdapter.ProductViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product movie = getItem(position);

        if (movie != null) {

            holder.bindTo(movie);
        }
    }

    private static DiffUtil.ItemCallback<Product> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Product>() {
                @Override
                public boolean areItemsTheSame(@NonNull Product product, @NonNull Product t1) {
                    return product.getId() == t1.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Product product, @NonNull Product t1) {
                    return product.equals(t1);
                }

            };

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        ImageView image;
        TextView name;
        TextView oldPrice;
        TextView newPrice;
        Product currentProduct;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.product_image);
            name = itemView.findViewById(R.id.product_name);
            newPrice = itemView.findViewById(R.id.product_new_price);
            oldPrice = itemView.findViewById(R.id.product_old_price);

            itemView.setOnClickListener(this);

        }

        public void bindTo(Product product) {
            currentProduct = product;

            Picasso.get()
                    .load(product.getUrlImagem())
                    .fit()
                    .centerCrop()
                    .into(image);

            name.setText(product.getNome());
            oldPrice.setText("De: " + df2.format(product.getPrecoDe()));
            newPrice.setText("Por " + df2.format(product.getPrecoPor()));
        }

        @Override
        public void onClick(View view) {
            if(itemClickListener != null) {
                itemClickListener.onItemClick(currentProduct);
            }
        }
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
