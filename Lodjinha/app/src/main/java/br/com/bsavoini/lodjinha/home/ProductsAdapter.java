package br.com.bsavoini.lodjinha.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.bsavoini.lodjinha.R;
import br.com.bsavoini.lodjinha.api.model.ProductModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {
    private List<ProductModel> productsArr;
    private RequestManager glideRequest;

    public ProductsAdapter(List<ProductModel> productsArr) {
        this.productsArr = productsArr;
    }

    @NonNull
    @Override
    public ProductsAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        glideRequest = Glide.with(viewGroup.getContext());
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product, viewGroup, false);
        return new ProductsAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ProductViewHolder viewHolder, int i) {
        ProductModel productModel = productsArr.get(viewHolder.getAdapterPosition());

        glideRequest.load(productModel.getImageURL()).into(viewHolder.productImg);

        viewHolder.nameTxt.setText(productModel.getName());
        viewHolder.originalPriceTxt.setText("De: " + productModel.getOriginalPrice());
        viewHolder.currentPriceTxt.setText("Por: " + productModel.getCurrentPrice());

    }

    @Override
    public int getItemCount() {
        return productsArr.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
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
        }
    }
}
