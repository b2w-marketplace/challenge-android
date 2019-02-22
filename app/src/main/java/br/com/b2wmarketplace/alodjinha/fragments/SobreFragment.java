package br.com.b2wmarketplace.alodjinha.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.b2wmarketplace.alodjinha.MainActivity;
import br.com.b2wmarketplace.alodjinha.R;

public class SobreFragment extends Fragment {
    private  Toolbar toolbar;

    public SobreFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sobre, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inicializa(view);

        toolbar.setTitle(R.string.titulo_sobre);

        if (getActivity() != null) {
            ((MainActivity) getActivity()).setMenu(toolbar);
        }
    }

    private void inicializa(View view) {
        toolbar = view.findViewById(R.id.toolbar);
    }

    public static SobreFragment newInstance() {
        return new SobreFragment();
    }
}
