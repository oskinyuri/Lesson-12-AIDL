package com.example.lesson_12_aidl;

import android.content.Context;
import android.content.SharedPreferences;

public class SPManager {

    private String SERVICE_SP = "SERVICE";
    private String SERVICE_DATA = "SERVICE_DATA";


    private Context mContext;

    public SPManager(Context context) {
        mContext = context;
    }

    public void setData(String data) {
        SharedPreferences preferences = mContext.getSharedPreferences(SERVICE_SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SERVICE_DATA, data);
        editor.apply();
    }

    public String getData() {
        SharedPreferences preferences = mContext.getSharedPreferences(SERVICE_SP, Context.MODE_PRIVATE);
        return preferences.getString(SERVICE_DATA, "No data");
    }
}
