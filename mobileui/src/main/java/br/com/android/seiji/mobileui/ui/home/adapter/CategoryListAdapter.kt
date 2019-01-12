package br.com.android.seiji.mobileui.ui.home.adapter

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.android.seiji.domain.model.Category
import br.com.android.seiji.mobileui.R
import com.squareup.picasso.Picasso

class CategoryListAdapter(
    var categoryList: List<Category>,
    private val onItemClickListener: (item: List<Category>, position: Int, view: View) -> Unit
) : RecyclerView.Adapter<CategoryListAdapter.CategoryListViewHolder>() {

    override fun getItemCount(): Int = categoryList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListViewHolder {
        return CategoryListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryListViewHolder, position: Int) {
        holder.bind(categoryList[position]) { view ->
            onItemClickListener(categoryList, position, view)
        }
    }

    open class CategoryListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val itemLayoutContainer = view.findViewById<ConstraintLayout>(R.id.layoutItemCategoryLayout)
        private val imageCategory = view.findViewById<ImageView>(R.id.imageCategory)
        private val textCategory = view.findViewById<TextView>(R.id.textCategory)

        open fun bind(category: Category, listener: (View) -> Unit) {
            textCategory.text = category.descricao

            Picasso.get()
                .load(category.urlImagem)
                .error(R.drawable.img_not_found_primary)
                .into(imageCategory)

            itemLayoutContainer.setOnClickListener { listener(itemLayoutContainer) }
        }
    }
}