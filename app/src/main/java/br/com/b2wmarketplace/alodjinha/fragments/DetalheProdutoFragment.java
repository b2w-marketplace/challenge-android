package br.com.b2wmarketplace.alodjinha.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.b2wmarketplace.alodjinha.MainActivity;
import br.com.b2wmarketplace.alodjinha.R;
import br.com.b2wmarketplace.alodjinha.common.BackPressedFragment;
import br.com.b2wmarketplace.alodjinha.common.ErroWs;
import br.com.b2wmarketplace.alodjinha.network.AsyncTaskCompleteListener;
import br.com.b2wmarketplace.alodjinha.network.WS;
import br.com.b2wmarketplace.alodjinha.util.Util;


public class DetalheProdutoFragment extends BackPressedFragment {
    private ImageView imgVoltar;
    private ConstraintLayout constraintLayout;
    private SimpleDraweeView imgFotoProduto;
    private TextView txtNomeDescricao;
    private TextView txtDe;
    private TextView txtPor;
    private TextView txtNome;
    private TextView txtDescricao;
    private FloatingActionButton fabReservar;
    private ProgressBar pbProgresso;

    private int id;
    private boolean clicoubotao;

    public DetalheProdutoFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalhe_produto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inicializa(view);

        Bundle bundle;
        bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt("id");
        }
        carregaProdutoWs();

        imgVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        fabReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!clicoubotao) {
                    clicoubotao = true;
                    pbProgresso.setVisibility(View.VISIBLE);
                    reservaProdutoWs();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).callBackPressed();
        }
    }

    private void inicializa(View view) {
        imgVoltar = view.findViewById(R.id.imgVoltar);
        constraintLayout = view.findViewById(R.id.constraintLayout);
        imgFotoProduto = view.findViewById(R.id.imgFotoProduto);
        txtNomeDescricao = view.findViewById(R.id.txtNomeDescricao);
        txtDe = view.findViewById(R.id.txtDe);
        txtPor = view.findViewById(R.id.txtPor);
        txtNome = view.findViewById(R.id.txtNome);
        txtDescricao = view.findViewById(R.id.txtDescricao);
        fabReservar = view.findViewById(R.id.fabReservar);
        pbProgresso = view.findViewById(R.id.pbProgresso);
    }

    public static DetalheProdutoFragment newInstance() {
        return new DetalheProdutoFragment();
    }

    private void carregaProdutoWs(){
        new WS(new AsyncTaskCompleteListener<String>() {
            @Override
            public void onTaskComplete(String result) {
                retornoWs(result, false);
            }
        }, "0", "produto/" + id).execute();
    }

    private void reservaProdutoWs(){
        new WS(new AsyncTaskCompleteListener<String>() {
            @Override
            public void onTaskComplete(String result) {
                retornoWs(result, true);
            }
        }, "1", "produto/" + id).execute();
    }

    public void retornoWs(String response, boolean reservaproduto) {
        try {
            switch (response) {
                case "Erro":
                    if (reservaproduto){
                        reservaProdutoWs(response);
                    }else {
                        ErroWs.retornaErro(getContext(), pbProgresso);
                    }
                    break;
                default:
                    if (reservaproduto){
                        reservaProdutoWs(response);
                    }else {
                        carregaProdutoWs(response);
                    }
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            ErroWs.retornaErro(getContext(), pbProgresso);
        }
        if (reservaproduto) fabReservar.setClickable(true);
    }



    private void carregaProdutoWs(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        try {
            imgFotoProduto.setImageURI(jsonObject.getString("urlImagem"));
            if (getContext() != null) {
                txtNomeDescricao.setText((getContext().getString(R.string.nome_descricao_produto, jsonObject.getString("nome"), jsonObject.getString("descricao"))));
                txtDe.setText(getContext().getString(R.string.preco_de, Util.formatarDecimal(jsonObject.getDouble("precoDe"))));
                Util.riscaTextView(txtDe);
                txtPor.setText(getContext().getString(R.string.preco_por, Util.formatarDecimal(jsonObject.getDouble("precoPor"))));
            }
            txtNome.setText(jsonObject.getString("nome"));
            txtDescricao.setText(Html.fromHtml(jsonObject.getString("descricao")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        pbProgresso.setVisibility(View.GONE);
        constraintLayout.setVisibility(View.VISIBLE);
    }

    private void reservaProdutoWs(final String response) {
        pbProgresso.setVisibility(View.GONE);

        AlertDialog alerta;
        if (getContext() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            if (response.equals("Erro")) {
                builder.setMessage(getContext().getString(R.string.erro_reserva_produto));
            }else{
                builder.setMessage(getContext().getString(R.string.produto_reservado));
            }
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    clicoubotao = false;
                    if (!response.equals("Erro")) {
                        onBackPressed();
                    }
                }
            });
            alerta = builder.create();
            alerta.setCanceledOnTouchOutside(false);
            alerta.show();
        }
    }
}
