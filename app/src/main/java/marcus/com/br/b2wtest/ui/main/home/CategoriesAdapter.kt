package marcus.com.br.b2wtest.ui.main.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_category.view.*
import marcus.com.br.b2wtest.R
import marcus.com.br.b2wtest.model.data.CategoryData
import marcus.com.br.b2wtest.ui.BaseRecyclerAdapter

class CategoriesAdapter : BaseRecyclerAdapter<CategoryData>() {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))
    }

    override fun getItemCount(): Int {
        return recyclerList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(recyclerList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(categoryData: CategoryData) = with(itemView) {
            Picasso.get()
                .load(categoryData.urlImage)
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.vector_img_not_found)
                .into(itemCategoryImage)

            itemCategoryName.text = categoryData.description
        }
    }
}