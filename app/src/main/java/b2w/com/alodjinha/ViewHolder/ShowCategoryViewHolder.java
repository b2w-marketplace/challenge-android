package b2w.com.alodjinha.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import b2w.com.alodjinha.Interface.ItemClickListener;
import b2w.com.alodjinha.R;

public class ShowCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView image_category;
    public TextView name_category;
    public RelativeLayout relative_layout_category;
    private ItemClickListener itemClickListener;


    public ShowCategoryViewHolder(View itemView) {
        super(itemView);
        name_category = (TextView) itemView.findViewById(R.id.name_category);
        image_category = (ImageView) itemView.findViewById(R.id.image_category);

        relative_layout_category = (RelativeLayout) itemView.findViewById(R.id.relative_layout_category);

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;

    }

    public void setInvisible() {

        RecyclerView.LayoutParams param = (RecyclerView.LayoutParams) itemView.getLayoutParams();

        relative_layout_category.setVisibility(View.GONE);
        param.height = 0;
        param.width = 0;

    }

}