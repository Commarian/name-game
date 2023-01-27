package com.commarian.namegamet3.HomeMade.Server;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.commarian.namegamet3.HomeMade.Classes.AppPreferences;
import com.commarian.namegamet3.HomeMade.Classes.BackgroundClass;
import com.commarian.namegamet3.HomeMade.Classes.LocaleHelper;
import com.commarian.namegamet3.HomeMade.Extras.HelpActivity;
import com.commarian.namegamet3.HomeMade.Startup.PublicPrivate;
import com.commarian.namegamet3.R;
import com.google.android.gms.ads.AdView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import static com.commarian.namegamet3.HomeMade.Classes.AnimHandler.animHandler;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.adRequest;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.animatedBackgrounds;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.difficulty;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.firebaseServer;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.gameLang;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.gameType;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.ir;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.lobbyName;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.private_game;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.public_game;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.soloBool;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticAct;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticRounds;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.tv1;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.tv2;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.tv3;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.tv4;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.tv5;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.tv6;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.playSound;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.random_int_gen;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.random_long_gen;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.static_help;

public class CreateActivity extends AppCompatActivity {

    AppCompatImageView backBtn;
    AppCompatImageView helpBtn;
    AppCompatImageView helper;
    AppCompatImageView diffHelpBtn;

    Button langBtn;
    Button diffBtn;
    Button startBtn;

    EditText ET;
    EditText roundsET;


    TextView TV;
    TextView roundsTV;


    FirebaseDatabase rdb = FirebaseDatabase.getInstance(firebaseServer);


    Handler handler = new Handler();


    

    AppPreferences _appPrefs;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);



        staticAct = "CA";

        AdView adView = (AdView) findViewById(R.id.adViewCreate);
        backBtn = findViewById(R.id.back_btn_pc);
        helpBtn = findViewById(R.id.help_btn_create);
        langBtn = findViewById(R.id.LangBtnPC);
        diffBtn = findViewById(R.id.DifficultyBtnPC);
        startBtn = findViewById(R.id.StartGameBtnPC);
        ET = findViewById(R.id.etCreateRoomName);
        roundsET = findViewById(R.id.roundsET);
        TV = findViewById(R.id.tvCreateRoomInfo);
        roundsTV = findViewById(R.id.roundsTV);
        helper = findViewById(R.id.helper_diff_pc);
        diffHelpBtn = findViewById(R.id.DifficultyButtonHELP);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        adView.loadAd(adRequest);

        tv1 = true;
        tv2 = true;
        tv3 = true;
        tv4 = true;
        tv5 = true;
        tv6 = true;

        _appPrefs= new AppPreferences(getApplicationContext());
        difficulty = _appPrefs.getString("DIFF");
        gameLang = _appPrefs.getString("gameLang");

        RoundsFunc();
        roundsET.setText(String.valueOf(staticRounds));
        Backgrounds();

        roundsET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                _appPrefs.saveString("Rounds", s.toString());
                RoundsFunc();
                //System.out.println("SAVING ROuNDS");
            }
        });


        switch (gameLang) {
            case "en":
                langBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.xml_join));
                langBtn.setText(R.string.english);
                startBtn.setEnabled(true);
                break;
            case "afr":
                langBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.xml_sw_acc));
                langBtn.setText(R.string.afrikaans);
                startBtn.setEnabled(true);
                break;
        }

        _appPrefs.saveString("runtime", "0");

        if(public_game){
            ET.setVisibility(View.GONE);
            TV.setVisibility(View.GONE);

            diffHelpBtn.setOnClickListener(v -> {
                playSound("click");
                static_help = "PublicDifficultyHelp";
                Intent i = new Intent(new Intent(CreateActivity.this, HelpActivity.class));
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                startActivity(i);
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            });
            helpBtn.setOnClickListener(v -> {
                playSound("click");
                static_help = "CreateActivityPublic";
                Intent i = new Intent(new Intent(CreateActivity.this, HelpActivity.class));
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                startActivity(i);
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            });

            langBtn.setOnClickListener(v -> {
            playSound("click");
            startBtn.setEnabled(true);
            switch (gameLang) {
                case "":
                    gameLang = "en";
                    _appPrefs.saveString("gameLang", gameLang);
                    langBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.xml_join));
                    langBtn.setText(R.string.english);
                case "en":
                    gameLang = "afr";
                    _appPrefs.saveString("gameLang", gameLang);
                    langBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.xml_sw_acc));
                    langBtn.setText(R.string.afrikaans);
                    break;
                case "afr":
                    gameLang = "en";
                    _appPrefs.saveString("gameLang", gameLang);
                    langBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.xml_join));
                    langBtn.setText(R.string.english);
                    break;
            }
        });
            startBtn.setOnClickListener(v -> {
                playSound("click");
                EnableButtons(false);
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.connecting, Snackbar.LENGTH_SHORT);
                snackbar.setTextColor(getResources().getColor(R.color.green_text));
                snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                snackbar.show();
                pubRoomCheck();
            });
            switch (difficulty) {
                case "Ez":
                case "Custom":
                    diffBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.xml_diff_med));
                    helper.setImageResource(R.drawable.helper_diff_med);
                    diffBtn.setText(R.string.medium_mode);
                    difficulty = "Med";
                    _appPrefs.saveString("DIFF", difficulty);
                    break;
                case "Med":
                    diffBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.xml_diff_med));
                    helper.setImageResource(R.drawable.helper_diff_med);
                    diffBtn.setText(R.string.medium_mode);
                    break;
                case "Hard":
                    diffBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.xml_diff_hard));
                    helper.setImageResource(R.drawable.helper_diff_hard);
                    diffBtn.setText(R.string.hardcore_mode);
                    break;
            }
            diffBtn.setOnClickListener(v -> {
                playSound("click");
                switch (difficulty) {
                    case "":
                    case "Hard":
                        difficulty = "Med";
                        _appPrefs.saveString("DIFF", difficulty);
                        break;
                    case "Med":
                        difficulty = "Hard";
                        _appPrefs.saveString("DIFF", difficulty);
                        break;
                }
                DifficultySwitch();
            });

        }else if(private_game){
            langBtn.setVisibility(View.GONE);

            diffHelpBtn.setOnClickListener(v -> {
                playSound("click");
                static_help = "PrivateDifficultyHelp";
                Intent i = new Intent(new Intent(CreateActivity.this, HelpActivity.class));
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                startActivity(i);
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            });
            helpBtn.setOnClickListener(v -> {
                playSound("click");
                static_help = "CreateActivityPrivate";
                Intent i = new Intent(new Intent(CreateActivity.this, HelpActivity.class));
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                startActivity(i);
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            });

            ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                startBtn.setEnabled(count > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
            });
            startBtn.setOnClickListener(v -> {
                playSound("click");
                EnableButtons(false);
                firebaseServer = "https://privat1.firebaseio.com/";
                rdb = FirebaseDatabase.getInstance(firebaseServer);
                if(this.getCurrentFocus() != null){
                    if(this.getCurrentFocus().getWindowToken()!= null){
                        InputMethodManager inputMethodManager = (InputMethodManager)this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
                    }
                }

                lobbyName = String.valueOf(ET.getText());
                if(!lobbyName.equals("")){
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.connecting, Snackbar.LENGTH_SHORT);
                    snackbar.setTextColor(getResources().getColor(R.color.green_text));
                    snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                    snackbar.show();
                    rdb.getReference().child(lobbyName).get().addOnSuccessListener(dataSnapshot -> {
                        if(!dataSnapshot.exists()){
                            gameType = "Private";
                            _appPrefs.saveString("DIFF", difficulty);
                            _appPrefs.saveString("gameLang", gameLang);
                            Intent i;
                            roundsET.setText(String.valueOf(staticRounds));
                            if(difficulty.equals("Custom")){
                                i = new Intent(CreateActivity.this, CustomHostActivity.class);
                            }else{
                                i = new Intent(CreateActivity.this, ServerActivity1.class);
                            }
                            finish();
                            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                            startActivity(i);
                            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);

                        }
                        else{
                            Snackbar snackbar1 = Snackbar.make(findViewById(android.R.id.content), R.string.room_name_taken, Snackbar.LENGTH_LONG);
                            snackbar1.setTextColor(getResources().getColor(R.color.red));
                            snackbar1.setBackgroundTint(getResources().getColor(R.color.snackback));
                            snackbar1.show();
                            EnableButtons(true);

                        }
                    }).addOnFailureListener(e -> {
                        System.out.println("Error " + e);
                        Snackbar snackbar12 = Snackbar.make(findViewById(android.R.id.content), R.string.failed_connect, Snackbar.LENGTH_LONG);
                        snackbar12.setTextColor(getResources().getColor(R.color.red));
                        snackbar12.setBackgroundTint(getResources().getColor(R.color.snackback));
                        snackbar12.show();
                        EnableButtons(true);
                    });

                }
                else{
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.eneter_valid_room, Snackbar.LENGTH_LONG);
                    snackbar.setTextColor(getResources().getColor(R.color.red));
                    snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                    snackbar.show();
                    EnableButtons(true);
                }

            });

            DifficultySwitch();
            diffBtn.setOnClickListener(v -> {
                playSound("click");
                switch (difficulty) {
                    case "":
                    case "Custom":
                        difficulty = "Ez";
                        _appPrefs.saveString("DIFF", difficulty);
                        break;
                    case "Ez":
                        difficulty = "Med";
                        _appPrefs.saveString("DIFF", difficulty);
                        break;
                    case "Med":
                        difficulty = "Hard";
                        _appPrefs.saveString("DIFF", difficulty);
                        break;
                    case "Hard":
                        difficulty = "Custom";
                        _appPrefs.saveString("DIFF", difficulty);
                        break;
                }
                DifficultySwitch();

            });
        }else if(soloBool){
            ET.setVisibility(View.GONE);
            TV.setVisibility(View.GONE);
            langBtn.setVisibility(View.VISIBLE);
            startBtn.setOnClickListener(v -> {
                playSound("click");
                EnableButtons(false);
                gameType = "Solo";
                _appPrefs.saveString("DIFF", difficulty);
                _appPrefs.saveString("gameLang", gameLang);
                Intent i;
                roundsET.setText(String.valueOf(staticRounds));
                i = new Intent(CreateActivity.this, ServerActivity1.class);
                finish();
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                startActivity(i);
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            });

            langBtn.setOnClickListener(v -> {
                playSound("click");
                startBtn.setEnabled(true);
                switch (gameLang) {
                    case "":
                        gameLang = "en";
                        _appPrefs.saveString("gameLang", gameLang);
                        langBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.xml_join));
                        langBtn.setText(R.string.english);
                    case "en":
                        gameLang = "afr";
                        _appPrefs.saveString("gameLang", gameLang);
                        langBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.xml_sw_acc));
                        langBtn.setText(R.string.afrikaans);
                        break;
                    case "afr":
                        gameLang = "en";
                        _appPrefs.saveString("gameLang", gameLang);
                        langBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.xml_join));
                        langBtn.setText(R.string.english);
                        break;
                }
            });
            DifficultySwitch();
            diffBtn.setOnClickListener(v -> {
                playSound("click");
                switch (difficulty) {
                    case "":
                    case "Custom":
                    case "Hard":
                        difficulty = "Ez";
                        _appPrefs.saveString("DIFF", difficulty);
                        break;
                    case "Ez":
                        difficulty = "Med";
                        _appPrefs.saveString("DIFF", difficulty);
                        break;
                    case "Med":
                        difficulty = "Hard";
                        _appPrefs.saveString("DIFF", difficulty);
                        break;
                }
                DifficultySwitch();
            });
            diffHelpBtn.setOnClickListener(v -> {
                playSound("click");
                static_help = "SoloDifficultyHelp";
                Intent i = new Intent(new Intent(CreateActivity.this, HelpActivity.class));
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                startActivity(i);
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            });
            helpBtn.setOnClickListener(v -> {
                playSound("click");
                static_help = "CreateActivitySolo";
                Intent i = new Intent(new Intent(CreateActivity.this, HelpActivity.class));
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                startActivity(i);
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            });
        } else{
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            finish();
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
        }

        backBtn.setOnClickListener(v -> {
            playSound("click");
            EnableButtons(false);
            Intent i = new Intent(new Intent(CreateActivity.this, PublicPrivate.class));
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            finish();
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            startActivity(i);
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
        });


    }

    int id = 0;
    private int RNG(){
        Random r = new Random();
        id = r.nextInt(99999);
        return id;
    }

    private void pubRoomCheck(){
        if(gameLang.equals("en")){
            firebaseServer = "https://puben.firebaseio.com/";
            rdb = FirebaseDatabase.getInstance(firebaseServer);
            lobbyName = "english_" + RNG();
        }else if(gameLang.equals("afr")){
            firebaseServer = "https://pubafr.firebaseio.com/";
            rdb = FirebaseDatabase.getInstance(firebaseServer);
            lobbyName = "afrikaans_" + RNG();
        }else{
            Snackbar snackbar1 = Snackbar.make(findViewById(android.R.id.content), R.string.please_choose_language, Snackbar.LENGTH_LONG);
            snackbar1.setTextColor(getResources().getColor(R.color.yellow_text));
            snackbar1.setBackgroundTint(getResources().getColor(R.color.snackback));
            snackbar1.show();
            EnableButtons(true);
        }
        handler.postDelayed(() -> {
            if(!gameLang.equals("")){
                rdb.getReference().child(lobbyName).get().addOnSuccessListener(dataSnapshot -> {
                    if(!dataSnapshot.exists()){
                        int tempDelay = 0;
                        if(difficulty.equals("Custom")){
                            tempDelay = 2200;
                            difficulty = "Med";
                        }
                        handler.postDelayed((Runnable) () -> {
                            _appPrefs.saveString("DIFF", difficulty);
                            _appPrefs.saveString("gameLang", gameLang);
                            gameType = "Public";
                            roundsET.setText(String.valueOf(staticRounds));
                            Intent i = new Intent(CreateActivity.this, ServerActivity1.class);
                            finish();
                            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                            startActivity(i);
                            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                        }, tempDelay);
                    }
                    else{
                        pubRoomCheck();
                        System.out.println("pubRoomCheck();");

                    }
                }).addOnFailureListener(e -> {
                    System.out.println("Error " + e);
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.failed_connect, Snackbar.LENGTH_LONG);
                    snackbar.setTextColor(getResources().getColor(R.color.red));
                    snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                    snackbar.show();
                    EnableButtons(true);
                });
            }
            else{
                Snackbar snackbar2 = Snackbar.make(findViewById(android.R.id.content), R.string.please_choose_language, Snackbar.LENGTH_LONG);
                snackbar2.setTextColor(getResources().getColor(R.color.yellow_text));
                snackbar2.setBackgroundTint(getResources().getColor(R.color.snackback));
                snackbar2.show();
                EnableButtons(true);
            }
        }, 500);
    }

    void EnableButtons(boolean E_or_D){
        runOnUiThread(() -> {
            TV.setEnabled(E_or_D);
            startBtn.setEnabled(E_or_D);
            langBtn.setEnabled(E_or_D);
            ET.setEnabled(E_or_D);
            backBtn.setEnabled(E_or_D);
            helpBtn.setEnabled(E_or_D);
            diffBtn.setEnabled(E_or_D);
            diffHelpBtn.setEnabled(E_or_D);
        });
    }

    //Checks if number of rounds exists and inputs it. Then saves it
    void RoundsFunc(){
        if( _appPrefs.getString("Rounds") != null && !_appPrefs.getString("Rounds").isEmpty() ){
            staticRounds = Integer.parseInt(_appPrefs.getString("Rounds"));
            if(staticRounds > 26){
                staticRounds = 26;
            }
            if( staticRounds < 1){
                staticRounds = 10;
            }
        }else if (_appPrefs.getString("Rounds").isEmpty() || _appPrefs.getString("Rounds") == null){
            staticRounds = 10;
        }
        _appPrefs.saveString("Rounds", String.valueOf(staticRounds));
    }

    void DifficultySwitch(){
        switch (difficulty) {
            case "Ez":
                diffBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.xml_diff_ez));
                helper.setImageResource(R.drawable.helper_diff_ez);
                if(!soloBool){
                    diffBtn.setText(R.string.easy_mode);
                }else{
                    diffBtn.setText(R.string.easy_mode_solo);
                }

                break;
            case "Med":
                diffBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.xml_diff_med));
                helper.setImageResource(R.drawable.helper_diff_med);
                if(!soloBool){
                    diffBtn.setText(R.string.medium_mode);
                }else{
                    diffBtn.setText(R.string.medium_mode_solo);
                }
                break;
            case "Hard":
                diffBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.xml_diff_hard));
                helper.setImageResource(R.drawable.helper_diff_hard);
                if(!soloBool){
                    diffBtn.setText(R.string.hardcore_mode);
                }else{
                    diffBtn.setText(R.string.hardcore_mode_solo);
                }

                break;
            case "Custom":
                diffBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.xml_diff_custom));
                helper.setImageResource(R.drawable.helper_custom);
                if(!soloBool){
                    diffBtn.setText(R.string.custom_mode);
                }else{
                    diffBtn.setText(R.string.medium_mode_solo);
                }

                break;
        }
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
        bg_0 = findViewById(R.id.bg_0_cr);
        bg_1 = findViewById(R.id.bg_2_cr);
        bg_2 = findViewById(R.id.bg_3_cr);
        bg_3 = findViewById(R.id.bg_4_cr);
        bg_4 = findViewById(R.id.bg_6_cr);
        bg_5 = findViewById(R.id.bg_7_cr);
        bg_6 = findViewById(R.id.bg_8_cr);
        con = this;
        BackgroundClass.SetBackground(this, findViewById(R.id.create_main_view));
        if(!bgran){
            bgran = true;
            Handler bghand0 = new Handler();
            Handler bghand1 = new Handler();
            Handler bghand2 = new Handler();
            Handler bghand3 = new Handler();
            Handler bghand4 = new Handler();
            Handler bghand5 = new Handler();
            Handler bghand6 = new Handler();
            if(animatedBackgrounds){
                bgrun0 = () -> {
                    if(staticAct.equals("CA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_0.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_0);
                        bghand0.postDelayed(bgrun0, bgdur);
                    }
                };
                bgrun1 = () -> {
                    if(staticAct.equals("CA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_1.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_1);
                        bghand1.postDelayed(bgrun1, bgdur);
                    }
                };
                bgrun2 = () -> {
                    if(staticAct.equals("CA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_2.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_2);
                        bghand2.postDelayed(bgrun2, bgdur);
                    }
                };
                bgrun3 = () -> {
                    if(staticAct.equals("CA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_3.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_3);
                        bghand3.postDelayed(bgrun3, bgdur);
                    }
                };
                bgrun4 = () -> {
                    if(staticAct.equals("CA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_4.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_4);
                        bghand4.postDelayed(bgrun4, bgdur);
                    }
                };
                bgrun5 = () -> {
                    if(staticAct.equals("CA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_5.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_5);
                        bghand5.postDelayed(bgrun5, bgdur);
                    }
                };
                bgrun6 = () -> {
                    if(staticAct.equals("CA")){
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