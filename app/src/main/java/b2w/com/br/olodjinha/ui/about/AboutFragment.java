package b2w.com.br.olodjinha.ui.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import b2w.com.br.olodjinha.R;
import b2w.com.br.olodjinha.MainActivity;

public class AboutFragment extends Fragment {

    public static Fragment getInstance() {
        return new AboutFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_about, container, false);

        initActionBar();

        return rootview;
    }

    private void initActionBar() {
        ((MainActivity) getContext()).setAppBar(this);
    }

}
