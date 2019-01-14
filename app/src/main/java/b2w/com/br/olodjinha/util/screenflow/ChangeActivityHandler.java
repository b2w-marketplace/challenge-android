package b2w.com.br.olodjinha.util.screenflow;

import android.app.Activity;
import android.content.Intent;

import java.io.Serializable;

import b2w.com.br.olodjinha.R;

public class ChangeActivityHandler {

    public void startActivity(Activity currentActivity, Class<?> newActivity){
        Intent intent = new Intent(currentActivity, newActivity);
        currentActivity.startActivity(intent);
    }

    public void startActivityWithExtra(Activity currentActivity, Class<?> newActivity, Serializable serializable) {
        Intent intent = new Intent(currentActivity, newActivity);
        intent.putExtra("infos", serializable);
        currentActivity.startActivity(intent);
        currentActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    public void startActivityWithExtras(Activity currentActivity, Class<?> newActivity, Serializable serializable1, Serializable serializable2) {
        Intent intent = new Intent(currentActivity, newActivity);
        intent.putExtra("infos", serializable1);
        intent.putExtra("infos2", serializable2);
        currentActivity.startActivity(intent);
        currentActivity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    public void startPopupActivityWithExtra(Activity currentActivity, Class<?> newActivity, Serializable serializable) {
        Intent intent = new Intent(currentActivity, newActivity);
        intent.putExtra("infos", serializable);
        currentActivity.startActivity(intent);
        currentActivity.overridePendingTransition(R.anim.slide_up, R.anim.fade_out);
    }

    public void startPopupActivityWithExtra(Activity currentActivity, Class<?> newActivity) {
        Intent intent = new Intent(currentActivity, newActivity);
        currentActivity.startActivity(intent);
        currentActivity.overridePendingTransition(R.anim.slide_up, R.anim.fade_out);
    }

    public void startPopupActivityWithExtras(Activity currentActivity, Class<?> newActivity, Serializable serializable1, Serializable serializable2) {
        Intent intent = new Intent(currentActivity, newActivity);
        intent.putExtra("infos", serializable1);
        intent.putExtra("infos2", serializable2);
        currentActivity.startActivity(intent);
        currentActivity.overridePendingTransition(R.anim.slide_up, R.anim.fade_out);
    }
}
