package br.com.amedigital.challenge_android.view.adapter

import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.amedigital.challenge_android.R
import br.com.amedigital.challenge_android.models.entity.Produto
import br.com.amedigital.challenge_android.view.ui.product_detail.ProductDetailActivity
import com.squareup.picasso.Picasso

class CategoriaActivityProdutoListAdapter (private val produtoArrayList: ArrayList<Produto>): RecyclerView.Adapter<CategoriaActivityProdutoListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.activity_categoria_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = produtoArrayList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.descricao.text = produtoArrayList[position].nome
        holder.precoOriginal.text = "De: " + produtoArrayList[position].precoDe.toString()
        holder.precoOriginal.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.precoAtual.text = "Por: " + produtoArrayList[position].precoPor.toString()
        Picasso.with(holder.produtoImage.context).load(produtoArrayList[position].urlImagem).into(holder.produtoImage)
        holder.view.setOnClickListener {
            val intent = Intent(holder.produtoImage.context, ProductDetailActivity::class.java)
            intent.putExtra("produto", produtoArrayList[position])
            holder.view.context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val view : View = itemView


        val descricao: TextView = itemView.findViewById(R.id.produtoDescricao)
        val produtoImage: ImageView = itemView.findViewById(R.id.produtoImage)

        val precoOriginal: TextView = itemView.findViewById(R.id.precoOriginal)

        val precoAtual: TextView = itemView.findViewById(R.id.precaAtual)
    }
}