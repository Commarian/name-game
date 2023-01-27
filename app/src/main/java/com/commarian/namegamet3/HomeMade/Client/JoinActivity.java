package com.commarian.namegamet3.HomeMade.Client;

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
import com.commarian.namegamet3.HomeMade.Server.CreateActivity;
import com.commarian.namegamet3.HomeMade.Startup.PublicPrivate;
import com.commarian.namegamet3.R;
import com.google.android.gms.ads.AdView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

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
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticAct;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticUsername;
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

public class JoinActivity extends AppCompatActivity {

    FirebaseDatabase rdb = FirebaseDatabase.getInstance(firebaseServer);

    final Handler handler = new Handler();

    


    TextView TV;
    Button startBtn;
    Button langBtn;
    EditText etLobName;
    AppPreferences _appPrefs;
    AppCompatImageView backBtn;
    AppCompatImageView helpBtn;

    Button diffBtn;
    AppCompatImageView helper;
    AppCompatImageView helperLang;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        staticAct = "JA";
        Backgrounds();
        AdView adView = (AdView) findViewById(R.id.adViewJoin);
        startBtn = findViewById(R.id.FindGameBtnJoin);
        langBtn = findViewById(R.id.LangBtnJoin);
        etLobName = findViewById(R.id.ETJoin);
        TV = findViewById(R.id.TVJoin);
        backBtn = findViewById(R.id.back_btn_join);
        helpBtn = findViewById(R.id.help_btn_join);
        helper = findViewById(R.id.helper_diff_join);
        helperLang = findViewById(R.id.helper_lang_join);
        diffBtn = findViewById(R.id.DifficultyBtnJoin);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        _appPrefs= new AppPreferences(getApplicationContext());

        difficulty = _appPrefs.getString("DIFF");
        tv1 = true;
        tv2 = true;
        tv3 = true;
        tv4 = true;
        tv5 = true;
        tv6 = true;

        backBtn.setOnClickListener(v -> {
            playSound("click");
            EnableButtons(false);
            Intent i = new Intent(JoinActivity.this, PublicPrivate.class);
            finish();
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            startActivity(i);
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
        });
        adView.loadAd(adRequest);

        gameLang = _appPrefs.getString("gameLang");




        if(public_game){
            helpBtn.setOnClickListener(v -> {
                playSound("click");
                static_help = "JoinActivityPublic";
                Intent i = new Intent(new Intent(JoinActivity.this, HelpActivity.class));
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                startActivity(i);
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            });
            diffBtn.setVisibility(View.GONE);
            TV.setVisibility(View.GONE);
            etLobName.setVisibility(View.GONE);
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
                case "Rush":
                    diffBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.xml_ready));
                    helper.setImageResource(R.drawable.helper_rush);
                    diffBtn.setText(R.string.rush_mode);
                    break;
            }
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
                EnableButtons(false);
                playSound("click");
                if(this.getCurrentFocus() != null){
                    if(this.getCurrentFocus().getWindowToken()!= null){
                        InputMethodManager inputMethodManager = (InputMethodManager)this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
                    }
                }
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.searching_for_games, Snackbar.LENGTH_SHORT);
                snackbar.setTextColor(getResources().getColor(R.color.yellow_text));
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
                case "Rush":
                    diffBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.xml_ready));
                    helper.setImageResource(R.drawable.helper_rush);
                    diffBtn.setText(R.string.rush_mode);
                    break;
            }
            diffBtn.setOnClickListener(v -> {
                playSound("click");
                switch (difficulty) {
                    case "":
                    case "Rush":
                        difficulty = "Med";
                        _appPrefs.saveString("DIFF", difficulty);
                        break;
                    case "Med":
                        difficulty = "Hard";
                        _appPrefs.saveString("DIFF", difficulty);
                        break;
                    case "Hard":
                        difficulty = "Rush";
                        _appPrefs.saveString("DIFF", difficulty);
                        break;
                }
                switch (difficulty) {
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
                    case "Rush":
                        diffBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.xml_ready));
                        helper.setImageResource(R.drawable.helper_rush);
                        diffBtn.setText(R.string.rush_mode);
                        break;
                }
            });

            //PRIVATE
        }else if(private_game){
            helpBtn.setOnClickListener(v -> {
                playSound("click");
                static_help = "JoinActivityPrivate";
                Intent i = new Intent(new Intent(JoinActivity.this, HelpActivity.class));
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                startActivity(i);
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            });
            langBtn.setVisibility(View.GONE);
            diffBtn.setVisibility(View.GONE);
            helper.setVisibility(View.GONE);
            helperLang.setVisibility(View.GONE);
            etLobName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    handler.postDelayed(() -> {
                        if(staticAct.equals("JA")){
                            startBtn.setEnabled(count > 0);
                        }
                    }, 500);

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
                lobbyName = String.valueOf(etLobName.getText());
                if(!lobbyName.equals("")){
                    {
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.connecting, Snackbar.LENGTH_SHORT);
                        snackbar.setTextColor(getResources().getColor(R.color.green_text));
                        snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                        snackbar.show();
                    }
                    rdb.getReference().child(lobbyName).get().addOnSuccessListener(task -> {
                        if(task.exists()){
                            boolean nametaken = task.hasChild(staticUsername);
                            boolean full;
                            if(Integer.parseInt(task.child("X").getValue().toString()) > 5){
                                full = true;
                                //System.out.println("X IS MORE THAN 5");
                            }else{
                                full = false;
                                //System.out.println("X IS NOT MORE THAN 5");
                            }
                            if(full){
                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.sorry_game_full, Snackbar.LENGTH_LONG);
                                snackbar.setTextColor(getResources().getColor(R.color.red));
                                snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                                snackbar.show();
                                EnableButtons(true);
                            }else
                            if(!nametaken){
                                gameType = "Private";
                                Intent i = new Intent(JoinActivity.this, ClientActivity1.class);
                                finish();
                                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                                startActivity(i);
                                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                            }else{
                                staticUsername = staticUsername + " (1)";
                                if(task.hasChild(staticUsername)){
                                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.username_taken, Snackbar.LENGTH_LONG);
                                    snackbar.setTextColor(getResources().getColor(R.color.red));
                                    snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                                    snackbar.show();
                                    handler.postDelayed(this::finish,500);
                                }else{
                                    gameType = "Private";
                                    Intent i = new Intent(JoinActivity.this, ClientActivity1.class);
                                    finish();
                                    overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                                    startActivity(i);
                                    overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                                }
                            }
                        }else{
                            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.room_not_exist, Snackbar.LENGTH_LONG);
                            snackbar.setTextColor(getResources().getColor(R.color.red));
                            snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                            snackbar.show();
                            EnableButtons(true);
                        }
                    }).addOnFailureListener(e -> {
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.failed_connect, Snackbar.LENGTH_LONG);
                        snackbar.setTextColor(getResources().getColor(R.color.red));
                        snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                        snackbar.show();
                        EnableButtons(true);
                    });
                }
                else{
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.eneter_valid_room, Snackbar.LENGTH_LONG);
                    snackbar.setTextColor(getResources().getColor(R.color.yellow_text));
                    snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                    snackbar.show();
                    EnableButtons(true);
                }
            });

        }else{
            finish();
        }

    }


    private void pubRoomCheck(){
        if(gameLang.equals("en")){
            firebaseServer = "https://puben.firebaseio.com/";
            rdb = FirebaseDatabase.getInstance(firebaseServer);
            lobbyName = "english_";
            _appPrefs.saveString("gameLang", gameLang);
        }else if(gameLang.equals("afr")){
            lobbyName = "afrikaans_";
            firebaseServer = "https://pubafr.firebaseio.com/";
            rdb = FirebaseDatabase.getInstance(firebaseServer);
            _appPrefs.saveString("gameLang", gameLang);
        }else{
            Snackbar snackbar1 = Snackbar.make(findViewById(android.R.id.content), R.string.please_choose_language, Snackbar.LENGTH_LONG);
            snackbar1.setTextColor(getResources().getColor(R.color.yellow_text));
            snackbar1.setBackgroundTint(getResources().getColor(R.color.snackback));
            snackbar1.show();
            EnableButtons(true);
        }
        if(!lobbyName.equals("") && !gameLang.equals("")){
            if(lobbyName.startsWith("english_")){
                rdb.getReference().get().addOnSuccessListener(dataSnapshot -> {
                    if(dataSnapshot.getValue() == null){
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.sorry_no_game_found, Snackbar.LENGTH_LONG);
                        snackbar.setTextColor(getResources().getColor(R.color.red));
                        snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                        snackbar.show();
                        //System.out.println("Going to create because datasnapshot == null eng");
                        CreatePublicGame();
                    }else{
                        try{
                            long childCount = dataSnapshot.getChildrenCount();
                            int repeatCount = 0;
                            //System.out.println("ChildCount = " + childCount);
                            for(DataSnapshot child : dataSnapshot.getChildren()){
                                repeatCount++;
                                //System.out.println("RepeatCount = " + repeatCount);
                                if(child != null && !child.toString().equals("")){
                                    if(child.getKey()!=null && !child.getKey().equals("")){
                                        String getKey = child.getKey();
                                        if(getKey.startsWith("english_")){
                                            if (child.getValue() !=null  && !child.getValue().toString().equals("")){
                                                String valString = child.getValue().toString();
                                                if(valString.contains("X=1") ||valString.contains("X=2")
                                                        ||valString.contains("X=3") ||valString.contains("X=4") ||valString.contains("X=5")) {
                                                    if (Cont(valString, getKey)) {
                                                        JoinGame();
                                                        return;
                                                    }else{
                                                        if(repeatCount >= childCount){
                                                            //System.out.println("Going to create because returned false in Cont");
                                                            CreatePublicGame();
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            EnableButtons(true);
                        }
                    }
                }).addOnFailureListener(e -> {
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.failed_connect, Snackbar.LENGTH_LONG);
                    snackbar.setTextColor(getResources().getColor(R.color.red));
                    snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                    snackbar.show();
                    EnableButtons(true);
                });
            }else if(lobbyName.startsWith("afrikaans_")){
                rdb.getReference().get().addOnSuccessListener(dataSnapshot -> {
                    if(dataSnapshot.getValue() == null){
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.sorry_no_game_found, Snackbar.LENGTH_LONG);
                        snackbar.setTextColor(getResources().getColor(R.color.red));
                        snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                        snackbar.show();
                        //System.out.println("Going to create because datasnapshot == null afr");
                        CreatePublicGame();
                    }else{
                        try{
                            long childCount = dataSnapshot.getChildrenCount();
                            int repeatCount = 0;
                           // System.out.println("ChildCount = " + childCount);
                            for(DataSnapshot child : dataSnapshot.getChildren()){
                                repeatCount++;
                             //   System.out.println("RepeatCount = " + repeatCount);
                                if(child != null && !child.toString().equals("")){
                                    if(child.getKey()!=null && !child.getKey().equals("")){
                                        String getKey = child.getKey();
                                        if(getKey.startsWith("afrikaans_")){
                                            if (child.getValue() !=null  && !child.getValue().toString().equals("")){
                                                String valString = child.getValue().toString();
                                                if(valString.contains("X=1") ||valString.contains("X=2")
                                                        ||valString.contains("X=3") ||valString.contains("X=4") ||valString.contains("X=5")) {
                                                    if (Cont(valString, getKey)) {
                                                        JoinGame();
                                                        return;
                                                    }else{
                                                        if(repeatCount >= childCount){
                                                          //  System.out.println("Going to create because returned false in Cont");
                                                            CreatePublicGame();
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            EnableButtons(true);
                        }
                    }
                }).addOnFailureListener(e -> {
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.failed_connect, Snackbar.LENGTH_LONG);
                    snackbar.setTextColor(getResources().getColor(R.color.red));
                    snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                    snackbar.show();
                    EnableButtons(true);
                });

            }

        }
        else{
            Snackbar snackbar2 = Snackbar.make(findViewById(android.R.id.content), R.string.please_choose_language, Snackbar.LENGTH_LONG);
            snackbar2.setTextColor(getResources().getColor(R.color.yellow_text));
            snackbar2.setBackgroundTint(getResources().getColor(R.color.snackback));
            snackbar2.show();
            EnableButtons(true);
        }
    }

    private boolean Cont(String string, String key){
        //System.out.println("CONT STRING = " + string + "CONT KEY = " + key);
        int startIndex = string.indexOf("T=1") +2;
        int endIndex = startIndex +13;
        String gameTime = string.substring(startIndex, endIndex);
        long currentTime = System.currentTimeMillis();
        try {
            long gameTimeLong = Long.parseLong(gameTime);
          //  System.out.println("Current Time = " + currentTime);
           // System.out.println("Game Time = " + gameTime);
            //System.out.println("Current Time - Game Time = " + (currentTime - gameTimeLong));
            if ((currentTime - gameTimeLong) < (long) 7200000){
               // System.out.println("Time If is true");
                if(!string.contains("X=6") && !string.contains("E=Exit") && !string.contains(staticUsername)){
                    lobbyName = key;
                    gameType = "Public";
                   // System.out.println("Returning true in cont 0");
                    return true;
                }
            }else{
              //  S//ystem.out.println("Returning false in cont 0");
                return false;
            }
           // System.out.println("Returning false in cont 1");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Returning false in cont 2");
            return false;
        }
    }

    void EnableButtons(boolean E_or_D){
        runOnUiThread(() -> {
            TV.setEnabled(E_or_D);
            startBtn.setEnabled(E_or_D);
            langBtn.setEnabled(E_or_D);
            etLobName.setEnabled(E_or_D);
            backBtn.setEnabled(E_or_D);
            helpBtn.setEnabled(E_or_D);
            diffBtn.setEnabled(E_or_D);
        });
    }

    void CreatePublicGame(){
        Intent i = new Intent(JoinActivity.this, CreateActivity.class);
        overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
        finish();
        overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
        startActivity(i);
        overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
    }

    void JoinGame(){
        Intent i = new Intent(JoinActivity.this, ClientActivity1.class);
        overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
        finish();
        overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
        startActivity(i);
        overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
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
        bg_0 = findViewById(R.id.bg_0_ja);
        bg_1 = findViewById(R.id.bg_2_ja);
        bg_2 = findViewById(R.id.bg_3_ja);
        bg_3 = findViewById(R.id.bg_4_ja);
        bg_4 = findViewById(R.id.bg_6_ja);
        bg_5 = findViewById(R.id.bg_7_ja);
        bg_6 = findViewById(R.id.bg_8_ja);
        con = this;
        BackgroundClass.SetBackground(con, findViewById(R.id.join_mv));
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
                    if(staticAct.equals("JA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_0.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_0);
                        bghand0.postDelayed(bgrun0, bgdur);
                    }
                };
                bgrun1 = () -> {
                    if(staticAct.equals("JA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_1.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_1);
                        bghand1.postDelayed(bgrun1, bgdur);
                    }
                };
                bgrun2 = () -> {
                    if(staticAct.equals("JA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_2.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_2);
                        bghand2.postDelayed(bgrun2, bgdur);
                    }
                };
                bgrun3 = () -> {
                    if(staticAct.equals("JA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_3.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_3);
                        bghand3.postDelayed(bgrun3, bgdur);
                    }
                };
                bgrun4 = () -> {
                    if(staticAct.equals("JA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_4.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_4);
                        bghand4.postDelayed(bgrun4, bgdur);
                    }
                };
                bgrun5 = () -> {
                    if(staticAct.equals("JA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_5.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_5);
                        bghand5.postDelayed(bgrun5, bgdur);
                    }
                };
                bgrun6 = () -> {
                    if(staticAct.equals("JA")){
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