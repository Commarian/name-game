package com.commarian.namegamet3.HomeMade.Classes;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.commarian.namegamet3.R;

public class AnimHandler {
    static boolean right = true;

    public static void animHandler(Context context, long duration, View view) {
        //System.out.println("ANIM HANDLER = "+ duration+ view);
        Animation moveAnim;
        if(right){
            moveAnim = AnimationUtils.loadAnimation(context, R.anim.move_right);
            view.setRotationY(180f);
            right = false;
        }else{
            moveAnim = AnimationUtils.loadAnimation(context, R.anim.move_left);
            view.setRotationY(0f);
            right = true;
        }
        moveAnim.setDuration(duration);
        view.startAnimation(moveAnim);
        view.setVisibility(View.VISIBLE);
    }
}
