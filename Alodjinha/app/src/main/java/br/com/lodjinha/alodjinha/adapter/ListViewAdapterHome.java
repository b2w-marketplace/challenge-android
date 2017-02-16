package br.com.lodjinha.alodjinha.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.lodjinha.alodjinha.R;
import br.com.lodjinha.alodjinha.model.Produto;

public class ListViewAdapterHome extends ArrayAdapter<Produto> implements View.OnClickListener {

    private ArrayList<Produto> dataSet;
    Context mContext;

    private static class ViewHolder {
        ImageView imgProdutoAdapter;
        TextView txtTituloAdapter;
        TextView txtPrecoDe;
        TextView txtPrecoPor;
        ImageView imgSetaAbrir;
    }



    public ListViewAdapterHome(ArrayList<Produto> data, Context context) {
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
        // Get the data item for this position
        Produto produto = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {


            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.linha_lista_home, parent, false);

            viewHolder.txtPrecoPor = (TextView) convertView.findViewById(R.id.txtPrecoPor);
            viewHolder.txtTituloAdapter = (TextView) convertView.findViewById(R.id.txtTituloAdapter);
            viewHolder.txtPrecoDe = (TextView) convertView.findViewById(R.id.txtPrecoDe);
            viewHolder.imgProdutoAdapter = (ImageView) convertView.findViewById(R.id.imgProdutoAdapter);
            viewHolder.imgSetaAbrir = (ImageView) convertView.findViewById(R.id.imgSetaAbrir);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        //Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        //result.startAnimation(animation);
        lastPosition = position;


        viewHolder.txtPrecoPor.setText(String.valueOf(produto.getPrecoPor()));
        viewHolder.txtPrecoDe.setText(String.valueOf(produto.getPrecoDe()));
        viewHolder.txtTituloAdapter.setText(produto.getNome() + ", " + produto.getDescricao());
        viewHolder.imgProdutoAdapter.setImageBitmap(produto.getImgProduto());


        return convertView;
    }

}