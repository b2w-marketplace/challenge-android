package com.abmm.b2w.alodjinha;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

public class LodjinhaApp extends Application {

    private static LodjinhaApp appInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        appInstance = this;
    }

    public static LodjinhaApp getInstance() { return appInstance; }

    public <T> void callActivity(Class<T> internalActivity) {
        this.callActivity(internalActivity, null);
    }

    public <T> void callActivity(Class<T> internalActivity, Bundle extras) {
        Intent intent = new Intent(this, internalActivity);
        if (null != extras) {
            intent.putExtras(extras);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }

}
