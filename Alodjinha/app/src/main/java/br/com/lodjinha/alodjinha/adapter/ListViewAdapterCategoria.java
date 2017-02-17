package br.com.lodjinha.alodjinha.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

import br.com.lodjinha.alodjinha.R;
import br.com.lodjinha.alodjinha.model.Produto;


public class ListViewAdapterCategoria extends ArrayAdapter<Produto> implements View.OnClickListener {

    private ArrayList<Produto> dataSet;
    Context mContext;

    private static class ViewHolder {
        ImageView imgProdutoAdapter;
        TextView txtTituloAdapter;
        TextView txtPrecoDe;
        TextView txtPrecoPor;
        ImageView imgSetaAbrir;
    }



    public ListViewAdapterCategoria(ArrayList<Produto> data, Context context) {
        super(context, R.layout.linha_lista_home, data);
        this.dataSet = data;
        this.mContext = context;

    }


    @Override
    public void onClick(View v) {


        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Produto produto =(Produto)object;




        switch (v.getId())
        {
//            case R.id.item_info:
//
//                Snackbar.make(v, "Release date " +home.getFeature(), Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
//
//                break;
        }


    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Produto produto = getItem(position);
        ListViewAdapterCategoria.ViewHolder viewHolder;

        final View result;

        if (convertView == null) {


            viewHolder = new ListViewAdapterCategoria.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.linha_lista_categoria, parent, false);

            viewHolder.txtPrecoPor = (TextView) convertView.findViewById(R.id.txtPrecoPorCategoria);
            viewHolder.txtTituloAdapter = (TextView) convertView.findViewById(R.id.txtTituloCategoriaAdapter);
            viewHolder.txtPrecoDe = (TextView) convertView.findViewById(R.id.txtPrecoDeCategoria);
            viewHolder.imgProdutoAdapter = (ImageView) convertView.findViewById(R.id.imgProdutoCategoriaAdapter);
            viewHolder.imgSetaAbrir = (ImageView) convertView.findViewById(R.id.imgSetaAbrirCategoria);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ListViewAdapterCategoria.ViewHolder) convertView.getTag();
            result = convertView;
        }

        lastPosition = position;


        viewHolder.txtPrecoPor.setText(String.valueOf(produto.getPrecoPor()));
        viewHolder.txtPrecoDe.setText(String.valueOf(produto.getPrecoDe()));
        viewHolder.txtTituloAdapter.setText(produto.getNome() + ", " + produto.getDescricao());
        viewHolder.imgProdutoAdapter.setImageBitmap(produto.getImgProduto());


        return convertView;
    }

}