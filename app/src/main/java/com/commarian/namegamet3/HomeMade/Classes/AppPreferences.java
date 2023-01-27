package com.commarian.namegamet3.HomeMade.Classes;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {
    private static final String APP_SHARED_PREFS = AppPreferences.class.getSimpleName();
    private final SharedPreferences _sharedPrefs;
    private final SharedPreferences.Editor _prefsEditor;


    public AppPreferences(Context context) {
        this._sharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        this._prefsEditor = _sharedPrefs.edit();
    }


    public String getString(String key){
        return _sharedPrefs.getString(key, "");
    }

    public void saveString(String key, String text) {
        //System.out.println("Saving String : " + text + " in key " + key);
        _prefsEditor.putString(key, text);
        _prefsEditor.commit();
    }
}
