package com.example.lesson_12_aidl;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class SPService extends Service {

    private IDataPassImpl mService;

    @Override
    public void onCreate() {
        super.onCreate();
        mService = new IDataPassImpl(new SPManager(getApplicationContext()));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mService;
    }

    @Override
    public void onDestroy() {
        mService = null;
        super.onDestroy();
    }
}
