package b2w.com.alodjinha.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import b2w.com.alodjinha.Interface.ItemClickListener;
import b2w.com.alodjinha.R;

public class ShowProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView image_product;
    public TextView name_product, original_price, new_price;
    public RelativeLayout relative_layout_product;
    private ItemClickListener itemClickListener;


    public ShowProductViewHolder(View itemView) {
        super(itemView);
        name_product = (TextView) itemView.findViewById(R.id.name_product);
        original_price = (TextView) itemView.findViewById(R.id.original_price);
        new_price = (TextView) itemView.findViewById(R.id.new_price);

        image_product = (ImageView) itemView.findViewById(R.id.image_product);

        relative_layout_product = (RelativeLayout) itemView.findViewById(R.id.relative_layout_product);

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        itemClickListener.onClick( view, getAdapterPosition(), false );
    }

    public void setItemClickListener(ItemClickListener itemClickListener ){
        this.itemClickListener = itemClickListener;

    }

    public void setInvisible( ){

        RecyclerView.LayoutParams param = (RecyclerView.LayoutParams)itemView.getLayoutParams();

        relative_layout_product.setVisibility(View.GONE);
        param.height = 0;
        param.width = 0;

    }






}