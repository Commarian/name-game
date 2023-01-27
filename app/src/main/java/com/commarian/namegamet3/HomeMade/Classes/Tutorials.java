package com.commarian.namegamet3.HomeMade.Classes;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.commarian.namegamet3.R;

import java.util.ArrayList;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;

import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.tut_active;


public class Tutorials {
    private static int tut_int = 0;
    private static int view_int = 0;
    private static int max_int = 0;
    private static final ArrayList<View> views = new ArrayList<>();
    private static final String[] tutStringArray = new String[9];
    private static final boolean[] tutPosArray = new boolean[9];
    private static Drawable oldBackground;
    private static Drawable oldForeground;
    private static Animation fade_in_anim;
    private static Animation fade_out_anim;
    private static final Handler handler = new Handler();
    //private static AppPreferences _appPrefs;

    public static void TutorialFunction(Context con, View blocking, View tut_btn, View tut_btn2, TextView tut_info,
                                        View v_1, View v_2, View v_3, ViewGroup rootView, View v_4, int Act){

        fade_in_anim = AnimationUtils.loadAnimation(con, R.anim.fade_in);
        fade_out_anim = AnimationUtils.loadAnimation(con, R.anim.fade_out);

        tut_btn.setVisibility(View.VISIBLE);
        tut_btn.setAlpha(0.0f);
        tut_btn.animate().alpha(1.0f);
        tut_btn2.setVisibility(View.VISIBLE);
        tut_btn2.setAlpha(0.0f);
        tut_btn2.animate().alpha(1.0f);
        tut_info.setVisibility(View.VISIBLE);
        tut_info.setAlpha(0.0f);
        tut_info.animate().alpha(1.0f);

        blocking.setVisibility(View.VISIBLE);
        blocking.setAlpha(0.0f);
        blocking.animate().alpha(0.8f);


        //_appPrefs = new AppPreferences(con);
        view_int = 0;
        if(Act == 1){
            max_int = 3;
            tut_int = 0;
        }else if(Act == 2){
            max_int = 6;
            tut_int = 4;
        }else if(Act == 3){
            max_int = 8;
            tut_int = 7;
        }

        views.add(0, v_1);
        views.add(1, v_2);
        views.add(2, v_3);
        views.add(3, v_4);


        tutStringArray[0] = con.getString(R.string.tut_mess_0);
        tutStringArray[1] = con.getString(R.string.tut_mess_1);
        tutStringArray[2] = con.getString(R.string.tut_mess_2);
        tutStringArray[3] = con.getString(R.string.tut_mess_3);
        tutStringArray[4] = con.getString(R.string.tut_mess_4);
        tutStringArray[5] = con.getString(R.string.tut_mess_5);
        tutStringArray[6] = con.getString(R.string.tut_mess_6);
        tutStringArray[7] = con.getString(R.string.tut_mess_7);
        tutStringArray[8] = con.getString(R.string.tut_mess_8);

        tutPosArray[0] = true;
        tutPosArray[1] = true;
        tutPosArray[2] = true;
        tutPosArray[3] = true;
        tutPosArray[4] = true;
        tutPosArray[5] = true;
        tutPosArray[6] = false;
        tutPosArray[7] = true;
        tutPosArray[8] = true;

        final float verticalBiasWhenTrue = 0.15f;
        final float verticalBiasWhenFalse = 0.75f;



        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) tut_info.getLayoutParams();

        tut_active = true;

        BringTutThingsToFront(blocking, tut_btn, tut_btn2, tut_info);

        if(tutPosArray[tut_int]){
            params.topToBottom = views.get(view_int).getId();
            params.bottomToBottom = rootView.getId();
            params.verticalBias = verticalBiasWhenTrue;
        }else{
            params.bottomToTop = views.get(view_int).getId();
            params.topToTop = rootView.getId();
            params.verticalBias = verticalBiasWhenFalse;
        }

        tut_info.setLayoutParams(params);
        tut_info.setText(tutStringArray[tut_int]);

        AutoViewDisabler(rootView, views.get(view_int), tut_btn,tut_btn2 );
        HighlightView(con, views.get(view_int), true);

        tut_btn.setOnClickListener(view -> {
            if(tut_int < max_int){
                BringTutThingsToFront(blocking, tut_btn, tut_btn2, tut_info);
                HighlightView(con, views.get(view_int), false);
                tut_int++;
                view_int++;
                HighlightView(con, views.get(view_int), true);
                AutoViewDisabler(rootView, views.get(view_int), tut_btn, tut_btn2);
                tut_info.setText(tutStringArray[tut_int]);
                if(tutPosArray[tut_int]){
                    params.topToBottom = views.get(view_int).getId();
                    params.bottomToBottom = rootView.getId();
                    params.verticalBias = verticalBiasWhenTrue;
                }else{
                    params.bottomToTop = views.get(view_int).getId();
                    params.topToTop = rootView.getId();
                    params.verticalBias = verticalBiasWhenFalse;
                }
                tut_info.setLayoutParams(params);
            }else{
                HighlightView(con, views.get(view_int), false);
                EnableViews(rootView, blocking, tut_info, tut_btn, tut_btn2);
            }

        });

        tut_btn2.setOnClickListener(view -> {
            HighlightView(con, views.get(view_int), false);
            EnableViews(rootView, blocking, tut_info, tut_btn, tut_btn2);
        });
    }
    

    static void AutoViewDisabler(ViewGroup rootView, View view_1, View btn_ok, View btn_nok){
        for(int i=0; i<rootView.getChildCount(); i++){
            View view = rootView.getChildAt(i);

            if( view == view_1 || view == btn_ok || view == btn_nok){
                view.setEnabled(true);
            }else if (view.isClickable() && view.isEnabled()){
                view.setEnabled(false);
            }
        }
    }

    static void EnableViews(ViewGroup rootView, View blocking, View tut_info, View tut_btn, View tut_btn2){
        for(int i=0;i<rootView.getChildCount();i++){
            View view = rootView.getChildAt(i);
            view.setEnabled(true);
            tut_info.animate().alpha(0.0f);
            blocking.animate().alpha(0.0f);
            tut_btn.animate().alpha(0.0f);
            tut_btn2.animate().alpha(0.0f);
            handler.postDelayed(() -> {
                blocking.setVisibility(View.GONE);
                tut_info.setVisibility(View.GONE);
                tut_btn.setVisibility(View.GONE);
                tut_btn2.setVisibility(View.GONE);
            }, 500);

            tut_int = 0;
            tut_active = false;
        }
    }

    static void HighlightView(Context con, View view, boolean save){
        if(save){
            view.bringToFront();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                oldForeground= view.getForeground();
                view.setForeground(AppCompatResources.getDrawable(con, R.drawable.tut_box));
            }
            if(view instanceof EditText){
                oldBackground = view.getBackground();
                view.setBackground(AppCompatResources.getDrawable(con, R.drawable.tut_box));
            }
            view.startAnimation(fade_in_anim);
        }else{
            view.startAnimation(fade_out_anim);
            view.setEnabled(false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                view.setForeground(oldForeground);
            }
            if(view instanceof EditText){
                view.setBackground(oldBackground);
            }
        }
    }

    static void BringTutThingsToFront(View blocking, View okBtn, View nokBtn, View tutInfo){
        blocking.bringToFront();
        okBtn.startAnimation(fade_out_anim);
        okBtn.bringToFront();
        okBtn.startAnimation(fade_in_anim);
        nokBtn.startAnimation(fade_out_anim);
        nokBtn.bringToFront();
        nokBtn.startAnimation(fade_in_anim);
        tutInfo.startAnimation(fade_out_anim);
        tutInfo.bringToFront();
        tutInfo.startAnimation(fade_in_anim);
    }
}

