package br.com.b2w.lodjinha.features.category

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.b2w.lodjinha.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_category.view.*
import kotlinx.android.synthetic.main.view_category_item.view.*

class CategoryView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val categories: MutableList<Category> = mutableListOf()
    private var categorySelectedListener: ((Category) -> Unit)? = null

    init {
        View.inflate(context, R.layout.view_category, this)
    }

    fun onCategorySelected(param: (Category) -> Unit) {
        categorySelectedListener = param
    }

    fun setCategories(categories: List<Category>) {
        this.categories.addAll(categories)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        with(categoriesRecyclerView) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CategoriesAdapter()
        }
    }

    inner class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder =
            CategoriesViewHolder(LayoutInflater.from(context).inflate(R.layout.view_category_item, parent, false))

        override fun getItemCount(): Int = categories.size

        override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
            val category = categories[position]
            holder.bind(category)
        }

        inner class CategoriesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

            fun bind(category: Category) {
                with(view) {
                    categoryNameTextView.text = category.description
                    Picasso.get()
                        .load(category.urlImagem)
                        //.placeholder(R.drawable.ic_exclamation_circle_solid)
                        .into(categoryImageView)
                    setOnClickListener { categorySelectedListener?.invoke(category) }
                }
            }

        }
    }
}