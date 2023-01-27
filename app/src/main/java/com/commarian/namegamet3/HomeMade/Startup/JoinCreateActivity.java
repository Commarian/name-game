package com.commarian.namegamet3.HomeMade.Startup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.commarian.namegamet3.HomeMade.Classes.AppPreferences;
import com.commarian.namegamet3.HomeMade.Classes.LocaleHelper;
import com.commarian.namegamet3.HomeMade.Client.JoinActivity;
import com.commarian.namegamet3.HomeMade.Extras.HelpActivity;
import com.commarian.namegamet3.HomeMade.Server.CreateActivity;
import com.commarian.namegamet3.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.create_game;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.join_game;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticAct;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.playSound;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.static_help;

public class JoinCreateActivity extends AppCompatActivity {
    AppCompatImageView helpBtn;
    AppCompatImageView backBtn;
    TextView joinBtn;
    TextView createBtn;

    TextView tut_info;
    Button tut_btn_next;
    Button tut_btn_stop;
    View blocking;
    ViewGroup rootView;
    AppPreferences _appPrefs;
    boolean help_game = false;
    Animation enlargeAnim;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_create);
        staticAct = "JCA";
        _appPrefs = new AppPreferences(getApplicationContext());
        joinBtn = findViewById(R.id.join_game_jc);
        createBtn= findViewById(R.id.create_game_jc);
        helpBtn= findViewById(R.id.help_btn_join_create);
        backBtn= findViewById(R.id.back_btn_join_create);
        tut_btn_next = findViewById(R.id.tut_btn_next3);
        tut_btn_stop = findViewById(R.id.tut_btn_stop3);
        tut_info = findViewById(R.id.tut_tv_info_main3);
        blocking = findViewById(R.id.blocking_view_main3);
        rootView = findViewById(R.id.joincreate_main);

        enlargeAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.mario);

        join_game = false;
        create_game = false;

        createBtn.setOnClickListener(v -> {
            playSound("click");
            create_game = true;
            set_buttons_disabled();

        });

        joinBtn.setOnClickListener(v -> {
            playSound("click");
            join_game = true;
            set_buttons_disabled();
        });

        backBtn.setOnClickListener(v -> {
            playSound("click");
            set_buttons_disabled();
        });

        helpBtn.setOnClickListener(v -> {
            help_game = true;
            playSound("click");
            set_buttons_disabled();
        });


    }

    private void set_buttons_disabled(){
        joinBtn.setEnabled(false);
        createBtn.setEnabled(false);
        helpBtn.setEnabled(false);
        backBtn.setEnabled(false);
        if(create_game){
            createBtn.startAnimation(enlargeAnim);
        }
        else if(join_game){
            joinBtn.startAnimation(enlargeAnim);
        }
        checkCreateJoin();
    }

    void checkCreateJoin(){
        if(create_game){
            Intent i = new Intent(new Intent(JoinCreateActivity.this, CreateActivity.class));
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            finish();
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            startActivity(i);
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
        }
        else if(join_game){
            Intent i = new Intent(new Intent(JoinCreateActivity.this, JoinActivity.class));
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            finish();
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            startActivity(i);
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
        }else if (help_game){
            static_help = "JoinCreateActivity";
            Intent i = new Intent(new Intent(JoinCreateActivity.this, HelpActivity.class));
            overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
            startActivity(i);
            overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
        }else{
            Intent i = new Intent(new Intent(JoinCreateActivity.this, PublicPrivate.class));
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            finish();
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            startActivity(i);
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
        }
    }
}