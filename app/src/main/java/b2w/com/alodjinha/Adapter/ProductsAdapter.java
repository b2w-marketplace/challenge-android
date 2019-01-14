package b2w.com.alodjinha.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import b2w.com.alodjinha.Interface.ItemClickListener;
import b2w.com.alodjinha.Model.Product;
import b2w.com.alodjinha.R;
import b2w.com.alodjinha.ShowProduct;
import b2w.com.alodjinha.Util.Common;
import b2w.com.alodjinha.ViewHolder.ShowProductViewHolder;

public class ProductsAdapter extends RecyclerView.Adapter<ShowProductViewHolder> {

    private Context context;
    private List<Product> listProducts;

    public ProductsAdapter(Context context, List<Product> listProducts) {
        this.context = context;
        this.listProducts = listProducts;
    }

    @Override
    public ShowProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate( R.layout.layout_product, parent, false );

        return new ShowProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShowProductViewHolder holder, final int position) {

        final Product product = listProducts.get(position);

        Picasso.with( context ).load( product.getUrlImage() ).into( holder.image_product );
        holder.name_product.setText( product.getName() );


        String price = context.getString(R.string.from_text) + " " + product.getOriginalPrice();

        if ( price.contains(".") ) price = price.replace(".", ",");
        else price = price + ",00";


        holder.original_price.setText( price );
        holder.original_price.setPaintFlags(holder.new_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        price = context.getString(R.string.for_text) + " " + product.getNewPrice().replace(".", ",");
        holder.new_price.setText(price);


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                Intent intent = new Intent( context, ShowProduct.class );
                Common.selectedProduct = product;
                context.startActivity( intent );

            }
        });


    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }

    public void removeItem( int position ){
        listProducts.remove(position);
    }

    public void restoreItem( Product item, int position ){
        listProducts.add(position, item);
    }

    public Product getItem(int position){
        return listProducts.get(position);
    }

}
