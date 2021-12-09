package com.example.happynewyear.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityPreferences {

    private SharedPreferences mSharedPreferences;

    public SecurityPreferences(Context mContext){
        this.mSharedPreferences = mContext.getSharedPreferences("appData", Context.MODE_PRIVATE);
    }

    public void storeData(String key, String value){
        this.mSharedPreferences.edit().putString(key,value).apply();
    }

    public String getStoredData(String key){
        return this.mSharedPreferences.getString(key, "");
    }
}
