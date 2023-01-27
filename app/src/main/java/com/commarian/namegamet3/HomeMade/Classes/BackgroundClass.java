package com.commarian.namegamet3.HomeMade.Classes;

import android.content.Context;
import android.view.View;

import com.commarian.namegamet3.R;

public class BackgroundClass {

    public static void SetBackground(Context con, View bgView){
        AppPreferences _appPrefs = new AppPreferences(con);
        if(_appPrefs.getString("background").equals("static")){
            bgView.setBackgroundResource(R.drawable.background);
        }else if (_appPrefs.getString("background").equals("animate")){
            bgView.setBackgroundResource(R.color.background);
        }else{
            bgView.setBackgroundResource(R.drawable.background);
        }
    }
}
