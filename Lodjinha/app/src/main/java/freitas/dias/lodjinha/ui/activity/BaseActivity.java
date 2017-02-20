package freitas.dias.lodjinha.ui.activity;


import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    protected void replaceFragment(@IdRes int containerViewId, @NonNull Fragment fragment,
                                   @NonNull String fragmentTag, @Nullable String backStackStateName) {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(containerViewId, fragment, fragmentTag).addToBackStack(backStackStateName)
                .commit();
    }
}
