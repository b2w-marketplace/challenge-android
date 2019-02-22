package br.com.b2wmarketplace.alodjinha.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import br.com.b2wmarketplace.alodjinha.MainActivity;
import br.com.b2wmarketplace.alodjinha.R;
import br.com.b2wmarketplace.alodjinha.holders.ProdutoViewHolder;
import br.com.b2wmarketplace.alodjinha.models.Produto;
import br.com.b2wmarketplace.alodjinha.util.Util;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoViewHolder> {
    private List<Produto> listaProdutos;
    private Context context;
    private FragmentManager fM;

    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;

    public ProdutoAdapter(ArrayList<Produto> listaProdutos, Context context, FragmentManager fM) {
        this.listaProdutos = listaProdutos;
        this.context = context;
        this.fM = fM;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        switch (i) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_produto, parent, false));
            case VIEW_TYPE_LOADING:
                return new FooterHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_loading, parent, false));
            default:
                return null ;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ProdutoViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == listaProdutos.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return listaProdutos == null ? 0 : listaProdutos.size();
    }

    private void add(Produto response) {
        listaProdutos.add(response);
        notifyItemInserted(listaProdutos.size() - 1);
    }

    public void addAll(List<Produto> listaProduto) {
        for (Produto response : listaProduto) {
            add(response);
        }
    }

    private void remove(Produto produto) {
        int position = listaProdutos.indexOf(produto);
        if (position > -1) {
            listaProdutos.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void addLoading() {
        isLoaderVisible = true;
        add(new Produto());
    }

    public void removeLoading() {
        isLoaderVisible = false;
        int position = listaProdutos.size() - 1;
        Produto produto = getItem(position);
        if (produto != null) {
            listaProdutos.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    private Produto getItem(int position) {
        return listaProdutos.get(position);
    }

    public class ViewHolder extends ProdutoViewHolder {
        private SimpleDraweeView imgFotoProduto;
        private TextView txtNomeDescricao;
        private TextView txtDe;
        private TextView txtPor;

        ViewHolder(@NonNull View view) {
            super(view);
            imgFotoProduto = view.findViewById(R.id.imgFotoProduto);
            imgFotoProduto = view.findViewById(R.id.imgFotoProduto);
            txtNomeDescricao = view.findViewById(R.id.txtNomeDescricao);
            txtDe = view.findViewById(R.id.txtDe);
            txtPor = view.findViewById(R.id.txtPor);

            ButterKnife.bind(this, view);
        }

        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);
            Produto item = listaProdutos.get(position);

            final int id = item.getId();

            imgFotoProduto.setImageURI(item.getUrlimagem().replace("http://", "https://"));
            txtNomeDescricao.setText(itemView.getResources().getString(R.string.nome_descricao_produto, item.getNome(), item.getDescricao()));
            txtDe.setText(itemView.getResources().getString(R.string.preco_de, Util.formatarDecimal(item.getPrecode())));
            Util.riscaTextView(txtDe);
            txtPor.setText(itemView.getResources().getString(R.string.preco_por, Util.formatarDecimal(item.getPrecopor())));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (fM.findFragmentByTag("categoriaproduto") != null) {
                        ((MainActivity) context).abreDetalheProduto(id, true);
                    }else{
                        ((MainActivity) context).abreDetalheProduto(id, false);
                    }
                }
            });
        }
    }

    public class FooterHolder extends ProdutoViewHolder {

        @BindView(R.id.pbProgressoLoading)
        ProgressBar pbProgressoLoading;


        FooterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {

        }

    }
}