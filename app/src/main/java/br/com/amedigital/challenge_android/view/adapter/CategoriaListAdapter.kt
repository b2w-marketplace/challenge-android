package br.com.amedigital.challenge_android.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.amedigital.challenge_android.R
import br.com.amedigital.challenge_android.models.entity.Categoria
import br.com.amedigital.challenge_android.view.ui.categoria.CategoriaActivity
import com.squareup.picasso.Picasso

class CategoriaListAdapter (private val categoriaArrayList: ArrayList<Categoria>): RecyclerView.Adapter<CategoriaListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.fragment_categoria_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = categoriaArrayList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.descricao.text = categoriaArrayList[position].descricao
        Picasso.with(holder.imgCategoria.context).load(categoriaArrayList[position].urlImagem).into(holder.imgCategoria)
        holder.view.setOnClickListener {
            val intent = Intent(holder.imgCategoria.context, CategoriaActivity::class.java)
            intent.putExtra("categoriaId", categoriaArrayList[position].id)
            intent.putExtra("categoriaNome", categoriaArrayList[position].descricao)
            holder.imgCategoria.context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val view : View = itemView
        val descricao: TextView = itemView.findViewById(R.id.categoriaDescricao)
        val imgCategoria: ImageView = itemView.findViewById(R.id.categoriaImage)
    }
}