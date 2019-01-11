package br.com.bsavoini.lodjinha.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.bsavoini.lodjinha.R;
import br.com.bsavoini.lodjinha.api.model.ProductModel;
import br.com.bsavoini.lodjinha.callbacks.ProductClickCallback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {
    private List<ProductModel> productsArr;
    private Picasso picassoInstance;
    private ProductClickCallback productClickCallback;

    public ProductsAdapter(List<ProductModel> productsArr, ProductClickCallback productClickCallback) {
        this.productsArr = productsArr;
        this.productClickCallback = productClickCallback;
    }

    @NonNull
    @Override
    public ProductsAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        picassoInstance = Picasso.with(viewGroup.getContext());
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product, viewGroup, false);
        return new ProductsAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ProductViewHolder viewHolder, int i) {
        ProductModel productModel = productsArr.get(viewHolder.getAdapterPosition());

        picassoInstance.load(productModel.getImageURL())
                .placeholder(R.drawable.logo_navbar)
                .into(viewHolder.productImg);

        viewHolder.nameTxt.setText(productModel.getName());
        viewHolder.originalPriceTxt.setText("De: " + productModel.getOriginalPrice());
        viewHolder.currentPriceTxt.setText("Por: " + productModel.getCurrentPrice());

    }

    @Override
    public int getItemCount() {
        return productsArr.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView productImg;
        private TextView nameTxt;
        private TextView originalPriceTxt;
        private TextView currentPriceTxt;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImg = itemView.findViewById(R.id.img_product);
            nameTxt = itemView.findViewById(R.id.txt_name);
            originalPriceTxt = itemView.findViewById(R.id.txt_original_price);
            currentPriceTxt = itemView.findViewById(R.id.txt_current_price);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ProductModel productModel = productsArr.get(getAdapterPosition());
            productClickCallback.onClickProduct(productModel);
        }
    }
}