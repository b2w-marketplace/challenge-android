package b2w.com.br.olodjinha.util.screenflow;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import b2w.com.br.olodjinha.R;
import b2w.com.br.olodjinha.MainActivity;

public class FragmentFlowManager {

    public void showFragment(Fragment fragment, Context context) {
        FragmentTransaction transaction = ((MainActivity) context).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
