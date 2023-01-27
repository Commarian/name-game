package com.commarian.namegamet3.HomeMade.Extras;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.commarian.namegamet3.HomeMade.Classes.AppPreferences;
import com.commarian.namegamet3.HomeMade.Classes.BackgroundClass;
import com.commarian.namegamet3.HomeMade.Classes.LocaleHelper;
import com.commarian.namegamet3.HomeMade.Startup.initialize_app;
import com.commarian.namegamet3.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.SwitchCompat;

import static com.commarian.namegamet3.HomeMade.Classes.AnimHandler.animHandler;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.animatedBackgrounds;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.ir;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticAct;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.playSound;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.random_int_gen;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.random_long_gen;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.static_help;

public class SettingsActivity extends AppCompatActivity {
    AppPreferences _appPrefs;
    String lang = "";
    Button LangBtn;
    Button bugBtn;
    SwitchCompat background;
    Button howToBtn;
    SwitchCompat sound;
    AppCompatImageView backBtn;
    Button creditsBtn;
    TextView privacyBtn;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        staticAct = "SET";
        setContentView(R.layout.activity_settings);
        Backgrounds();
        LangBtn = findViewById(R.id.languageBtn);
        background = findViewById(R.id.switchBackground);
        bugBtn = findViewById(R.id.bugBtn);
        howToBtn = findViewById(R.id.howToPlayBtn);
        backBtn = findViewById(R.id.settings_back_btn);
        creditsBtn = findViewById(R.id.creditsBtn);
        privacyBtn = findViewById(R.id.privacyBtn);

        HowToPlayBtnPress();
        _appPrefs = new AppPreferences(getApplicationContext());
        String appSound = _appPrefs.getString("sound");
        sound = findViewById(R.id.switchSound);
        sound.setChecked(!appSound.equals("OFF"));
        if(_appPrefs.getString("background").equals("animate")){
            background.setChecked(true);
        }


        sound.setOnCheckedChangeListener((buttonView, isChecked) -> {
            playSound("click");
            if(isChecked){
                initialize_app.muted = false;
                _appPrefs.saveString("sound", "ON");
            }else{
                initialize_app.muted = true;
                _appPrefs.saveString("sound", "OFF");
            }
        });

        background.setOnCheckedChangeListener((buttonView, isChecked) -> {
            playSound("click");
            if(isChecked){
                animatedBackgrounds = true;
                _appPrefs.saveString("background", "animate");
            }else{
                animatedBackgrounds = false;
                _appPrefs.saveString("background", "static");
                bgran =false;
            }
            Backgrounds();
        });

        creditsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound("click");
                runOnUiThread(() -> {
                    LangBtn.setEnabled(false);
                    bugBtn.setEnabled(false);
                    background.setEnabled(false);
                    howToBtn.setEnabled(false);
                    creditsBtn.setEnabled(false);
                });
                Intent km = new Intent(SettingsActivity.this, Credits.class);
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                startActivity(km);
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            }
        });

        privacyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ko = new Intent(SettingsActivity.this, PrivacyPolicy.class);
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                startActivity(ko);
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            }
        });
        backBtn.setOnClickListener(v -> {
            playSound("click");
            finish();
        });

        if(_appPrefs.getString("lang") != null){
            lang = _appPrefs.getString("lang");
        }
        LanguageButtonPress();
        BugReportBtnPress();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LangBtn.setEnabled(true);
        bugBtn.setEnabled(true);
        background.setEnabled(true);
        howToBtn.setEnabled(true);
        creditsBtn.setEnabled(true);
    }

    private void LanguageButtonPress(){
        LangBtn.setOnClickListener(v -> {
            playSound("click");
            runOnUiThread(() -> {
                LangBtn.setEnabled(false);
                bugBtn.setEnabled(false);
                background.setEnabled(false);
                howToBtn.setEnabled(false);
                creditsBtn.setEnabled(false);
            });
            Intent km = new Intent(SettingsActivity.this, LanguageActivity.class);
            finish();
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            startActivity(km);
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
        });
    }

    private void BugReportBtnPress(){
        bugBtn.setOnClickListener(v -> {
            playSound("click");
            runOnUiThread(() -> {
                LangBtn.setEnabled(false);
                bugBtn.setEnabled(false);
                background.setEnabled(false);
                creditsBtn.setEnabled(false);
                howToBtn.setEnabled(false);
            });
            Intent km = new Intent(SettingsActivity.this, BugActivity.class);
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            startActivity(km);
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
        });
    }
    private void HowToPlayBtnPress(){
        howToBtn.setOnClickListener(v -> {
            playSound("click");
            runOnUiThread(() -> {
                LangBtn.setEnabled(false);
                bugBtn.setEnabled(false);
                background.setEnabled(false);
                creditsBtn.setEnabled(false);
                howToBtn.setEnabled(false);
            });
            static_help = "SettingsActivity";
            Intent km = new Intent(SettingsActivity.this, HelpActivity.class);
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            startActivity(km);
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
        });
    }

    Runnable bgrun0;
    Runnable bgrun1;
    Runnable bgrun2;
    Runnable bgrun3;
    Runnable bgrun4;
    Runnable bgrun5;
    Runnable bgrun6;
    long bgdur = 0;
    AppCompatImageView bg_0;
    AppCompatImageView bg_1;
    AppCompatImageView bg_2;
    AppCompatImageView bg_3;
    AppCompatImageView bg_4;
    AppCompatImageView bg_5;
    AppCompatImageView bg_6;
    Context con;
    boolean bgran = false;

    void Backgrounds(){
        bg_0 = findViewById(R.id.bg_0_set);
        bg_1 = findViewById(R.id.bg_2_set);
        bg_2 = findViewById(R.id.bg_3_set);
        bg_3 = findViewById(R.id.bg_4_set);
        bg_4 = findViewById(R.id.bg_6_set);
        bg_5 = findViewById(R.id.bg_7_set);
        bg_6 = findViewById(R.id.bg_8_set);
        con = this;
        BackgroundClass.SetBackground(this, findViewById(R.id.settings_main));
        //System.out.println("Backgrounds");
        if(!bgran){
            bgran = true;
            Handler bghand0 = new Handler();
            Handler bghand1 = new Handler();
            Handler bghand2 = new Handler();
            Handler bghand3 = new Handler();
            Handler bghand4 = new Handler();
            Handler bghand5 = new Handler();
            Handler bghand6 = new Handler();
            //System.out.println("!bgran");
            if(animatedBackgrounds){
                //System.out.println("animatedbackgrounds");
                bgrun0 = () -> {
                    if(staticAct.equals("SET")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_0.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_0);
                        bghand0.postDelayed(bgrun0, bgdur);
                    }
                };
                bgrun1 = () -> {
                    if(staticAct.equals("SET")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_1.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_1);
                        bghand1.postDelayed(bgrun1, bgdur);
                    }
                };
                bgrun2 = () -> {
                    if(staticAct.equals("SET")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_2.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_2);
                        bghand2.postDelayed(bgrun2, bgdur);
                    }
                };
                bgrun3 = () -> {
                    if(staticAct.equals("SET")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_3.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_3);
                        bghand3.postDelayed(bgrun3, bgdur);
                    }
                };
                bgrun4 = () -> {
                    if(staticAct.equals("SET")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_4.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_4);
                        bghand4.postDelayed(bgrun4, bgdur);
                    }
                };
                bgrun5 = () -> {
                    if(staticAct.equals("SET")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_5.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_5);
                        bghand5.postDelayed(bgrun5, bgdur);
                    }
                };
                bgrun6 = () -> {
                    if(staticAct.equals("SET")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_6.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_6);
                        bghand6.postDelayed(bgrun6, bgdur);
                    }
                };
                bghand0.postDelayed(bgrun0, 100);
                bghand1.postDelayed(bgrun1, 200);
                bghand2.postDelayed(bgrun2, 200);
                bghand3.postDelayed(bgrun3, 300);
                bghand4.postDelayed(bgrun4, 0);
                bghand5.postDelayed(bgrun5, 300);
                bghand6.postDelayed(bgrun6, 100);
            }else{
                //System.out.println("!animatedbackgrounds");
                bg_0.setVisibility(View.GONE);
                bg_1.setVisibility(View.GONE);
                bg_2.setVisibility(View.GONE);
                bg_3.setVisibility(View.GONE);
                bg_4.setVisibility(View.GONE);
                bg_5.setVisibility(View.GONE);
                bg_6.setVisibility(View.GONE);
                bgran = false;
            }
        }
    }
}