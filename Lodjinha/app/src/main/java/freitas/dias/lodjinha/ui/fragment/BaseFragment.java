package freitas.dias.lodjinha.ui.fragment;

import android.support.v4.app.Fragment;

import org.greenrobot.eventbus.EventBus;

public class BaseFragment extends Fragment {

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        try {
            EventBus.getDefault().unregister(this);
        } finally {
            super.onStop();
        }
    }
}
