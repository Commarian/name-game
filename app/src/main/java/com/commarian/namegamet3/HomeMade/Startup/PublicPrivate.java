package com.commarian.namegamet3.HomeMade.Startup;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.commarian.namegamet3.HomeMade.Classes.AppPreferences;
import com.commarian.namegamet3.HomeMade.Classes.Tutorials;
import com.commarian.namegamet3.HomeMade.Client.JoinActivity;
import com.commarian.namegamet3.HomeMade.Server.CreateActivity;
import com.commarian.namegamet3.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.private_game;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.public_game;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.soloBool;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticAct;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.playSound;

public class PublicPrivate extends AppCompatActivity {

    AppPreferences _appPrefs;
    TextView publicBtn;
    TextView privateBtn;
    TextView soloBtn;
    AlertDialog.Builder builder ;

    TextView tut_info;
    Button tut_btn_next;
    Button tut_btn_stop;
    View blocking;
    ViewGroup rootView;
    Handler handler;

    Animation enlargeAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_private);

        handler = new Handler();
        tut_btn_next = findViewById(R.id.tut_btn_next_pp);
        tut_btn_stop = findViewById(R.id.tut_btn_stop_pp);
        tut_info = findViewById(R.id.tut_tv_info_pp);
        blocking = findViewById(R.id.blocking_view_pp);
        rootView = findViewById(R.id.pubpriv_main);

        staticAct = "PP";
        _appPrefs = new AppPreferences(getApplicationContext());
        builder = new AlertDialog.Builder(this, R.style.AlertDialogThemeCustom);
        publicBtn = findViewById(R.id.public_room_btn_txt);
        privateBtn = findViewById(R.id.private_room_btn_txt);
        soloBtn = findViewById(R.id.solo_room_btn_txt);

        private_game = false;
        public_game = false;
        soloBool = false;

        enlargeAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.mario);

        handler.postDelayed(this::TutorialStart,100);

        publicBtn.setOnClickListener(v -> {
            public_game = true;
            set_buttons_enabled(0);
            playSound("click");

        });
        privateBtn.setOnClickListener(v -> {
            private_game = true;
            set_buttons_enabled(1);
            playSound("click");

        });
        soloBtn.setOnClickListener(v -> {
            soloBool = true;
            playSound("click");
            if(_appPrefs.getString("soloDialogShowed") != null && !_appPrefs.getString("soloDialogShowed").isEmpty()){
                set_buttons_enabled(2);
            }else{
                _appPrefs.saveString("soloDialogShowed", "Yes");
                builder = new AlertDialog.Builder(this, R.style.AlertDialogThemeCustom);
                builder.setCancelable(false);
                builder.setTitle(R.string.solo_dialogue_title);
                builder.setMessage(R.string.solo_dialogue_message);
                builder.setPositiveButton(R.string.continue_button,
                        (dialog, which) -> set_buttons_enabled(2));
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }
    void checkCreateJoin(){
        if(private_game){
            Intent i = new Intent(new Intent(PublicPrivate.this, JoinCreateActivity.class));
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            finish();
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            startActivity(i);
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
        }else if(public_game){
            Intent i = new Intent(new Intent(PublicPrivate.this, JoinActivity.class));
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            finish();
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            startActivity(i);
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
        }else if(soloBool){
            Intent i = new Intent(new Intent(PublicPrivate.this, CreateActivity.class));
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            finish();
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            startActivity(i);
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
        }else{
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            finish();
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
        }

    }

    private void set_buttons_enabled(int x){
        publicBtn.setEnabled(false);
        privateBtn.setEnabled(false);
        soloBtn.setEnabled(false);
        if(x == 0){
            publicBtn.startAnimation(enlargeAnim);
        }
        else if(x == 1){
            privateBtn.startAnimation(enlargeAnim);
        }
        else if(x == 2){
            soloBtn.startAnimation(enlargeAnim);
        }
        handler.postDelayed(this::checkCreateJoin, 100);

    }

    void TutorialStart(){
        if(_appPrefs.getString("First Time").contains("Main")){
            Tutorials.TutorialFunction(this, blocking, tut_btn_next, tut_btn_stop, tut_info, publicBtn, privateBtn, soloBtn, rootView, null, 2);
            _appPrefs.saveString("First Time", "ThirdFourthFifth");
        }
    }

}