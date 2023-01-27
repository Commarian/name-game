package com.commarian.namegamet3.HomeMade.Server;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.commarian.namegamet3.HomeMade.Classes.BackgroundClass;
import com.commarian.namegamet3.HomeMade.Classes.LocaleHelper;
import com.commarian.namegamet3.HomeMade.DBs.AnimalsDBHelper;
import com.commarian.namegamet3.HomeMade.DBs.DiereDBHelper;
import com.commarian.namegamet3.HomeMade.DBs.NamesDBHelper;
import com.commarian.namegamet3.HomeMade.DBs.SurnamesDBHelper;
import com.commarian.namegamet3.HomeMade.DBs.TownsDBHelper;
import com.commarian.namegamet3.HomeMade.DBs.TownsMarkDBHelper;
import com.commarian.namegamet3.R;
import com.google.android.gms.ads.AdView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import static com.commarian.namegamet3.HomeMade.Classes.AnimHandler.animHandler;
import static com.commarian.namegamet3.HomeMade.Server.ServerActivity1.incScoreS;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.Profanity;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.adRequest;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.animatedBackgrounds;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar0code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar1code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar2code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar3code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar4code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar5code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.botHasAnimal;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.botHasName;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.botHasSurname;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.botHasTown;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.custom1;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.custom2;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.custom3;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.custom4;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.difficulty;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.firebaseServer;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.gameLang;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.ir;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.lobbyName;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.singleStaticDier;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.singleStaticDorp;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.singleStaticNaam;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.singleStaticVan;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.soloBool;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticAct;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticBotScore;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticLetter;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticSoloScore;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticUsername;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.stopCalled;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.user0old;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.user1old;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.user2old;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.user3old;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.user4old;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.user5old;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.userCount;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username0;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username1;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username2;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username3;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username4;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username5;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.playSound;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.random_int_gen;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.random_long_gen;

public class ServerScore extends AppCompatActivity {


    Boolean sure = true;
    String UNEval = "";


    NamesDBHelper namesDB = new NamesDBHelper(ServerScore.this);
    SurnamesDBHelper surnamesDB = new SurnamesDBHelper(ServerScore.this);
    AnimalsDBHelper animalsDB = new AnimalsDBHelper(ServerScore.this);
    TownsDBHelper townsDB = new TownsDBHelper(ServerScore.this);
    TownsMarkDBHelper townsMarkDB = new TownsMarkDBHelper(ServerScore.this);
    DiereDBHelper diereDB = new DiereDBHelper(ServerScore.this);





    DatabaseReference rdb = FirebaseDatabase.getInstance(firebaseServer).getReference(lobbyName);
    DatabaseReference cdb = FirebaseDatabase.getInstance("https://letsnamegame-default-rtdb.firebaseio.com").getReference(lobbyName);
    Map<String, Object> user0 = new HashMap<>();
    Map<String, Object> user1 = new HashMap<>();
    Map<String, Object> user2 = new HashMap<>();
    Map<String, Object> user3 = new HashMap<>();
    Map<String, Object> user4 = new HashMap<>();
    Map<String, Object> user5 = new HashMap<>();

    

    AlertDialog.Builder builder ;
    Animation vibrateAnim;

    TextView tvUN ;
    TextView tvScore ;
    TextView tvNaam ;
    TextView tvNaam2 ;
    TextView tvVan;
    TextView tvVan2;
    TextView tvDier ;
    TextView tvDier2 ;
    TextView tvDorp;
    TextView tvDorp2;
    TextView tvWait;

    AppCompatImageView leaveBtn;

    ProgressBar PB;

    Button nextUserBtn;



    ImageButton yesNaam;
    ImageButton yesVan;
    ImageButton yesDier;
    ImageButton yesDorp;
    ImageButton noNaam;
    ImageButton noVan;
    ImageButton noDier;
    ImageButton noDorp;

    AppCompatImageView ivAvatar;
    AppCompatImageView helperCast;

    Handler handler = new Handler();

    int nextUserPressed = 1;
    int uiRun = 0;
    int run = 0;
    int localScore = 0;
    int refreshDelay = 0;

    int alphaWeak =  50;
    int alphaStrong = 255;

    boolean u0Fin;
    boolean u1Fin;
    boolean u2Fin;
    boolean u3Fin;
    boolean u4Fin;
    boolean u5Fin;
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    char[] charArray;


    int user0Retry, user1Retry, user2Retry, user3Retry, user4Retry, user5Retry;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_score);


        user0.put("Naam", singleStaticNaam);
        user0.put("Van", singleStaticVan);
        user0.put("Dier", singleStaticDier);
        user0.put("Dorp", singleStaticDorp);

        charArray = alphabet.toCharArray();
        CheckUsernames();
        staticAct = "SS";
        Backgrounds();
        stopCalled = false;
        u0Fin = false;
        u1Fin = false;
        u2Fin = false;
        u3Fin = false;
        u4Fin = false;
        u5Fin = false;

        user0Retry = 0;
        user1Retry = 0;
        user2Retry = 0;
        user3Retry = 0;
        user4Retry = 0;
        user5Retry = 0;
        refreshDelay = 2000;

        vibrateAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.vib);
        leaveBtn = findViewById(R.id.leave_game_server_score);
        nextUserBtn= findViewById(R.id.nextUserBtnS);
        PB = findViewById(R.id.PBSS);
        tvUN = findViewById(R.id.tvUNS);
        tvScore = findViewById(R.id.tvUNS2);
        tvNaam = findViewById(R.id.tvASNaam);
        tvNaam2 = findViewById(R.id.tvASNaam2);
        tvVan = findViewById(R.id.tvASVan);
        tvVan2 = findViewById(R.id.tvASVan2);
        tvDier = findViewById(R.id.tvASDier);
        tvDier2 = findViewById(R.id.tvASDier2);
        tvDorp = findViewById(R.id.tvASDorp);
        tvDorp2 = findViewById(R.id.tvASDorp2);
        tvWait = findViewById(R.id.tvWaitS);
        helperCast = findViewById(R.id.helper_cast_vote_server);
        ivAvatar = findViewById(R.id.avatarSS);


        //Ads
        AdView adView = (AdView) findViewById(R.id.adViewSS);
        adView.loadAd(adRequest);


        yesNaam = findViewById(R.id.correctNaamBtn);
        yesVan = findViewById(R.id.correctVanBtn);
        yesDier= findViewById(R.id.correctDierBtn);
        yesDorp = findViewById(R.id.correctDorpBtn);
        noNaam = findViewById(R.id.wrongNaamBtn);
        noVan = findViewById(R.id.wrongVanBtn);
        noDier = findViewById(R.id.wrongDierBtn);
        noDorp = findViewById(R.id.wrongDorpBtn);

        if(!soloBool){
           SetGameState();
            if(difficulty.equals("Custom")){
                tvNaam2.setText(custom1);
                tvVan2.setText(custom2);
                tvDier2.setText(custom3);
                tvDorp2.setText(custom4);
            }
        }else{
            MarkUserInput();
            username0 = staticUsername;
            user0.put("Naam", singleStaticNaam);
            user0.put("Van", singleStaticVan);
            user0.put("Dier", singleStaticDier);
            user0.put("Dorp", singleStaticDorp);
            user1.put("Naam", "");
            user1.put("Van", "");
            user1.put("Dier", "");
            user1.put("Dorp", "");
            SetupBot();
            CheckIfBotDone();
        }


        run = 0;

        sure = true;


        PB.setVisibility(View.VISIBLE);
        tvWait.setVisibility(View.VISIBLE);
        tvUN.setVisibility(View.INVISIBLE);
        tvNaam.setVisibility(View.INVISIBLE);
        tvNaam2.setVisibility(View.INVISIBLE);
        tvVan.setVisibility(View.INVISIBLE);
        tvVan2.setVisibility(View.INVISIBLE);
        tvDier.setVisibility(View.INVISIBLE);
        tvDier2.setVisibility(View.INVISIBLE);
        tvDorp.setVisibility(View.INVISIBLE);
        tvDorp2.setVisibility(View.INVISIBLE);
        nextUserBtn.setVisibility(View.INVISIBLE);
        helperCast.setVisibility(View.INVISIBLE);
        waitClients();
        builder = new AlertDialog.Builder(this, R.style.AlertDialogThemeCustom);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                builder.setCancelable(true);
                builder.setTitle(R.string.leave_title);
                builder.setMessage(R.string.leave_game_return_main);
                builder.setPositiveButton(R.string.yes,
                        (dialog, which) -> {
                            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), getString(R.string.leaving_game), Snackbar.LENGTH_SHORT);
                            snackbar.setTextColor(getResources().getColor(R.color.yellow_text));
                            snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                            snackbar.show();
                                rdb.removeValue().addOnCompleteListener(task1 -> cdb.removeValue().addOnCompleteListener(task -> {
                                    finish();
                                    //System.out.println("Deleted");
                                }));
                        });
                builder.setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss());
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
        leaveBtn.setOnClickListener(v -> {
            builder.setCancelable(true);
            builder.setTitle(R.string.leave_title);
            builder.setMessage(R.string.leave_game_return_main);
            builder.setPositiveButton(R.string.yes,
                    (dialog, which) -> {
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), getString(R.string.leaving_game), Snackbar.LENGTH_SHORT);
                        snackbar.setTextColor(getResources().getColor(R.color.yellow_text));
                        snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                        snackbar.show();
                        rdb.removeValue().addOnCompleteListener(task1 -> cdb.removeValue().addOnCompleteListener(task -> {
                            finish();
                            //System.out.println("Deleted");
                        }));
                    });
            builder.setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        ButtonListeners();

    }


    private void waitClients(){
        if(userCount < 2 && !soloBool){
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), getString(R.string.leaving_game_no_players), Snackbar.LENGTH_SHORT);
            snackbar.setTextColor(getResources().getColor(R.color.yellow_text));
            snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
            snackbar.show();
            rdb.removeValue().addOnCompleteListener(task1 -> cdb.removeValue().addOnCompleteListener(task -> finish()));
        }else{
            if(soloBool){
                handler.postDelayed(this::NextUserButton, 700);
            }else{
                handler.postDelayed(this::getAnswers,1800);
                handler.postDelayed(this::NextUserButton, 500);
                handler.postDelayed(this::deleteStop,3100);
            }
        }
    }


    private void getAnswers(){
        //System.out.println("user 0 = " + user0);
        //System.out.println("user 1 = " + user1);
        //System.out.println("user 2 = " + user2);
        //System.out.println("user 3 = " + user3);
        //System.out.println("user 4 = " + user4);
        //System.out.println("user 5 = " + user5);
        if(!soloBool){
            //System.out.println("User0fin = " + u0Fin);
            if(!username0.equals("") && !username0.equals(staticUsername) && !u0Fin && user0Retry < 4){
                rdb.child(username0).get().addOnSuccessListener(ds -> {
                    if(ds.hasChild("Naam")){
                        if(ds.child("Naam").getValue() != null){
                            if(!ds.child("Naam").getValue().toString().equals(user0old.get("Naam"))){
                                u0Fin = true;
                                user0.put("Naam", ds.child("Naam").getValue().toString());
                                user0.put("Van", ds.child("Van").getValue().toString());
                                user0.put("Dier", ds.child("Dier").getValue().toString());
                                user0.put("Dorp", ds.child("Dorp").getValue().toString());
                                updateUI(username0, user0);
                                user0old.putAll(user0);
                                ivAvatar.setImageResource(avatar0code);
                            }else{
                                handler.postDelayed(this::getAnswers,refreshDelay);
                                user0Retry++;
                            }
                        }else{
                            handler.postDelayed(this::getAnswers,refreshDelay);
                            user0Retry++;
                        }
                    }else{
                        handler.postDelayed(this::getAnswers,refreshDelay);
                        user0Retry++;
                    }
                }).addOnFailureListener(e -> handler.postDelayed(this::getAnswers,refreshDelay));
            }else
            if(!username1.equals("") && !username1.equals(staticUsername) && !u1Fin && user1Retry < 4){
                rdb.child(username1).get().addOnSuccessListener(ds -> {
                    if(ds.hasChild("Naam")){
                        if(ds.child("Naam").getValue() != null){
                            if(!ds.child("Naam").getValue().toString().equals(user1old.get("Naam"))){
                                u1Fin = true;
                                user1.put("Naam", ds.child("Naam").getValue().toString());
                                user1.put("Van", ds.child("Van").getValue().toString());
                                user1.put("Dier", ds.child("Dier").getValue().toString());
                                user1.put("Dorp", ds.child("Dorp").getValue().toString());
                                updateUI(username1, user1);
                                user1old.putAll(user1);
                                ivAvatar.setImageResource(avatar1code);
                            }else{
                                handler.postDelayed(this::getAnswers,refreshDelay);
                                user1Retry++;
                            }
                        }else{
                            handler.postDelayed(this::getAnswers,refreshDelay);
                            user1Retry++;
                        }
                    }else{
                        handler.postDelayed(this::getAnswers,refreshDelay);
                        user1Retry++;
                    }
                }).addOnFailureListener(e -> handler.postDelayed(this::getAnswers,refreshDelay));
            }else
            if(!username2.equals("") && !username2.equals(staticUsername) && !u2Fin && user2Retry < 4){
                rdb.child(username2).get().addOnSuccessListener(ds -> {
                    if(ds.hasChild("Naam")){
                        if(ds.child("Naam").getValue() != null){
                            if(!ds.child("Naam").getValue().toString().equals(user2old.get("Naam"))){
                                u2Fin = true;
                                user2.put("Naam", ds.child("Naam").getValue().toString());
                                user2.put("Van", ds.child("Van").getValue().toString());
                                user2.put("Dier", ds.child("Dier").getValue().toString());
                                user2.put("Dorp", ds.child("Dorp").getValue().toString());
                                updateUI(username2, user2);
                                user2old.putAll(user2);
                                ivAvatar.setImageResource(avatar2code);
                            }else{
                                handler.postDelayed(this::getAnswers,refreshDelay);
                                user2Retry++;
                            }
                        }else{
                            handler.postDelayed(this::getAnswers,refreshDelay);
                            user2Retry++;
                        }
                    }else{
                        handler.postDelayed(this::getAnswers,refreshDelay);
                        user2Retry++;
                    }
                }).addOnFailureListener(e -> handler.postDelayed(this::getAnswers,refreshDelay));
            }else
            if(!username3.equals("") && !username3.equals(staticUsername) && !u3Fin && user3Retry < 4){
                rdb.child(username3).get().addOnSuccessListener(ds -> {
                    if(ds.hasChild("Naam")){
                        if(ds.child("Naam").getValue() != null){
                            if(!ds.child("Naam").getValue().toString().equals(user3old.get("Naam"))){
                                u3Fin = true;
                                user3.put("Naam", ds.child("Naam").getValue().toString());
                                user3.put("Van", ds.child("Van").getValue().toString());
                                user3.put("Dier", ds.child("Dier").getValue().toString());
                                user3.put("Dorp", ds.child("Dorp").getValue().toString());
                                updateUI(username3, user3);
                                user3old.putAll(user3);
                                ivAvatar.setImageResource(avatar3code);
                            }else{
                                handler.postDelayed(this::getAnswers,refreshDelay);
                                user3Retry++;
                            }
                        }else{
                            handler.postDelayed(this::getAnswers,refreshDelay);
                            user3Retry++;
                        }
                    }else{
                        handler.postDelayed(this::getAnswers,refreshDelay);
                        user3Retry++;
                    }
                }).addOnFailureListener(e -> handler.postDelayed(this::getAnswers,refreshDelay));
            }else
            if(!username4.equals("")&& !username4.equals(staticUsername) && !u4Fin && user4Retry < 4){
                rdb.child(username4).get().addOnSuccessListener(ds -> {
                    if(ds.hasChild("Naam")){
                        if(ds.child("Naam").getValue() != null){
                            if(!ds.child("Naam").getValue().toString().equals(user4old.get("Naam"))){
                                u4Fin = true;
                                user4.put("Naam", ds.child("Naam").getValue().toString());
                                user4.put("Van", ds.child("Van").getValue().toString());
                                user4.put("Dier", ds.child("Dier").getValue().toString());
                                user4.put("Dorp", ds.child("Dorp").getValue().toString());
                                updateUI(username4, user4);
                                user4old.putAll(user4);
                                ivAvatar.setImageResource(avatar4code);
                            }else{
                                handler.postDelayed(this::getAnswers,refreshDelay);
                                user4Retry++;
                            }
                        }else{
                            handler.postDelayed(this::getAnswers,refreshDelay);
                            user4Retry++;
                        }
                    }else{
                        handler.postDelayed(this::getAnswers,refreshDelay);
                        user4Retry++;
                    }
                }).addOnFailureListener(e -> handler.postDelayed(this::getAnswers,refreshDelay));
            }else
            if(!username5.equals("")&& !username5.equals(staticUsername) && !u5Fin && user5Retry < 4){
                rdb.child(username5).get().addOnSuccessListener(ds -> {
                    if(ds.hasChild("Naam")){
                        if(ds.child("Naam").getValue() != null){
                            if(!ds.child("Naam").getValue().toString().equals(user5old.get("Naam"))){
                                u5Fin = true;
                                user5.put("Naam", ds.child("Naam").getValue().toString());
                                user5.put("Van", ds.child("Van").getValue().toString());
                                user5.put("Dier", ds.child("Dier").getValue().toString());
                                user5.put("Dorp", ds.child("Dorp").getValue().toString());
                                updateUI(username5, user5);
                                user5old.putAll(user5);
                                ivAvatar.setImageResource(avatar5code);
                            }else{
                                handler.postDelayed(this::getAnswers,refreshDelay);
                                user5Retry++;
                            }
                        }else{
                            handler.postDelayed(this::getAnswers,refreshDelay);
                            user5Retry++;
                        }
                    }else{
                        handler.postDelayed(this::getAnswers,refreshDelay);
                        user5Retry++;
                    }
                }).addOnFailureListener(e -> handler.postDelayed(this::getAnswers,refreshDelay));
            }else{
                GoToNextAct();
            }
        }else{
            System.out.println(username0);
            System.out.println(username1);
            System.out.println(username2);
            System.out.println(username3);
            System.out.println(username4);
            System.out.println(username5);
            System.out.println(user0Retry);
            System.out.println(user1Retry);
            System.out.println(user2Retry);
            System.out.println(user3Retry);
            System.out.println(user4Retry);
            System.out.println(user5Retry);
            u1Fin = true;
            updateUI(username1, user1);
            ivAvatar.setImageResource(avatar1code);
        }
    }


    private void deleteStop(){
        handler.postDelayed(() -> rdb.child("S").removeValue().addOnSuccessListener(aVoid -> {
        }).addOnFailureListener(e -> {
            System.out.println("Failed to delete S, retrying");
            handler.postDelayed(ServerScore.this::deleteStop, 250);
        }), 2000);
    }


    private void updateUI(String UN, @NonNull Map<String, Object> map){
        int animation_delay = 750;
        String naam = String.valueOf(map.get("Naam"));
        System.out.println("Map inside updateui " + map);
        String van = String.valueOf(map.get("Van"));
        String dier = String.valueOf(map.get("Dier"));
        String dorp = String.valueOf(map.get("Dorp"));

        boolean naam_empty = false;
        boolean van_empty = false;
        boolean dier_empty = false;
        boolean dorp_empty = false;

        tvNaam.setTextColor(getResources().getColor(R.color.white));
        tvVan.setTextColor(getResources().getColor(R.color.white));
        tvDorp.setTextColor(getResources().getColor(R.color.white));
        tvDier.setTextColor(getResources().getColor(R.color.white));

        System.out.println("UN == " + UN);
        if(uiRun == 0){
            uiRun = 5;
            PB.setVisibility(View.INVISIBLE);
            tvWait.setVisibility(View.INVISIBLE);
            tvUN.setVisibility(View.VISIBLE);
            tvNaam.setVisibility(View.VISIBLE);
            tvNaam2.setVisibility(View.VISIBLE);
            tvVan.setVisibility(View.VISIBLE);
            tvVan2.setVisibility(View.VISIBLE);
            tvDier.setVisibility(View.VISIBLE);
            tvDier2.setVisibility(View.VISIBLE);
            tvDorp.setVisibility(View.VISIBLE);
            tvDorp2.setVisibility(View.VISIBLE);
            nextUserBtn.setVisibility(View.VISIBLE);
            helperCast.setVisibility(View.VISIBLE);
            ivAvatar.setVisibility(View.VISIBLE);
            yesNaam.setVisibility(View.VISIBLE);
            yesVan.setVisibility(View.VISIBLE);
            yesDorp.setVisibility(View.VISIBLE);
            yesDier.setVisibility(View.VISIBLE);
            noNaam.setVisibility(View.VISIBLE);
            noVan.setVisibility(View.VISIBLE);
            noDier.setVisibility(View.VISIBLE);
            noDorp.setVisibility(View.VISIBLE);
        }
        if(naam.length() < 2 || naam.equals("null") || Profanity(naam)){
            naam_empty = true;
            if(!Profanity(naam)){
                naam = randomString();
            }else{
                naam = naam.replace(naam.charAt(naam.length() -1), "*".charAt(0));
                naam = naam.replace(naam.charAt(naam.length() -2), "*".charAt(0));
                naam = naam.replace(naam.charAt(naam.length() -3), "*".charAt(0));
            }
            yesNaam.setVisibility(View.INVISIBLE);
            noNaam.setVisibility(View.INVISIBLE);
            tvNaam.setTextColor(getResources().getColor(R.color.red_text));
        }else{
            System.out.println("CheckSameWord(1, naam, UN = " + CheckSameWord(1, naam, UN));
            if(CheckSameWord(1, naam, UN)){
                yesNaam.setVisibility(View.INVISIBLE);
                noNaam.setVisibility(View.INVISIBLE);
                tvNaam.setTextColor(getResources().getColor(R.color.blue_text));
                localScore++;
            }else if (DBScan(naam, String.valueOf(staticLetter), 0)){
                yesNaam.setVisibility(View.VISIBLE);
                yesNaam.setEnabled(true);
                noNaam.setVisibility(View.VISIBLE);
                noNaam.setEnabled(true);
                handler.postDelayed(() -> {
                    tvNaam.startAnimation(vibrateAnim);
                    yesNaam.callOnClick();
                    tvNaam.setTextColor(getResources().getColor(R.color.correct_db_text));
                }, animation_delay);
                animation_delay = 1250;
            }else{
                yesNaam.setVisibility(View.VISIBLE);
                yesNaam.setEnabled(true);
                noNaam.setVisibility(View.VISIBLE);
                noNaam.setEnabled(true);
            }
        }
        if(van.length() < 2|| van.equals("null") || Profanity(van)){
            van_empty = true;
            if(!Profanity(van)){
                van = randomString();
            }else{
                van = van.replace(van.charAt(van.length() -1), "*".charAt(0));
                van = van.replace(van.charAt(van.length() -2), "*".charAt(0));
                van = van.replace(van.charAt(van.length() -3), "*".charAt(0));
            }
            yesVan.setVisibility(View.INVISIBLE);
            noVan.setVisibility(View.INVISIBLE);
            tvVan.setTextColor(getResources().getColor(R.color.red_text));
        }else{
            if(CheckSameWord(2, van, UN)){
                yesVan.setVisibility(View.INVISIBLE);
                noVan.setVisibility(View.INVISIBLE);
                tvVan.setTextColor(getResources().getColor(R.color.blue_text));
                localScore++;
            }else if (DBScan(van, String.valueOf(staticLetter), 1)){
                yesVan.setVisibility(View.VISIBLE);
                yesVan.setEnabled(true);
                noVan.setVisibility(View.VISIBLE);
                noVan.setEnabled(true);
                handler.postDelayed(() -> {
                    tvVan.startAnimation(vibrateAnim);
                    yesVan.callOnClick();
                    tvVan.setTextColor(getResources().getColor(R.color.correct_db_text));
                }, animation_delay);
                animation_delay = 1750;
            }else{
                yesVan.setVisibility(View.VISIBLE);
                yesVan.setEnabled(true);
                noVan.setVisibility(View.VISIBLE);
                noVan.setEnabled(true);
            }
        }
        if(dier.length() < 2|| dier.equals("null")|| Profanity(dier)){
            dier_empty = true;
            if(!Profanity(dier)){
                dier = randomString();
            }else{
                dier = dier.replace(dier.charAt(dier.length() -1), "*".charAt(0));
                dier = dier.replace(dier.charAt(dier.length() -2), "*".charAt(0));
                dier = dier.replace(dier.charAt(dier.length() -3), "*".charAt(0));
            }
            yesDier.setVisibility(View.INVISIBLE);
            noDier.setVisibility(View.INVISIBLE);
            tvDier.setTextColor(getResources().getColor(R.color.red_text));
        }else{
            if(CheckSameWord(3, dier, UN)){
                yesDier.setVisibility(View.INVISIBLE);
                noDier.setVisibility(View.INVISIBLE);
                tvDier.setTextColor(getResources().getColor(R.color.blue_text));
                localScore++;
            }
            else{
                yesDier.setVisibility(View.VISIBLE);
                yesDier.setEnabled(true);
                noDier.setVisibility(View.VISIBLE);
                noDier.setEnabled(true);
                if(gameLang.equals("en")){
                    if (DBScan(dier, String.valueOf(staticLetter), 2)){
                        handler.postDelayed(() -> {
                            yesDier.callOnClick();
                            tvDier.setTextColor(getResources().getColor(R.color.correct_db_text));
                            tvDier.startAnimation(vibrateAnim);
                        }, animation_delay);
                        animation_delay = 2250;
                    }
                }else{
                    if (DBScan(dier, String.valueOf(staticLetter), 4)){
                        handler.postDelayed(() -> {
                            tvDier.startAnimation(vibrateAnim);
                            yesDier.callOnClick();
                            tvDier.setTextColor(getResources().getColor(R.color.correct_db_text));
                        }, animation_delay);
                        animation_delay = 2250;
                    }
                }
            }
        }
        if(dorp.length() < 2|| dorp.equals("null") || Profanity(dorp)){
            dorp_empty = true;
            if(!Profanity(dorp)){
                dorp = randomString();
            }else{
                dorp = dorp.replace(dorp.charAt(dorp.length() -1), "*".charAt(0));
                dorp = dorp.replace(dorp.charAt(dorp.length() -2), "*".charAt(0));
                dorp = dorp.replace(dorp.charAt(dorp.length() -3), "*".charAt(0));
            }
            yesDorp.setVisibility(View.INVISIBLE);
            noDorp.setVisibility(View.INVISIBLE);
            tvDorp.setTextColor(getResources().getColor(R.color.red_text));
        }else{
            if(CheckSameWord(4, dorp, UN)){
                yesDorp.setVisibility(View.INVISIBLE);
                noDorp.setVisibility(View.INVISIBLE);
                tvDorp.setTextColor(getResources().getColor(R.color.blue_text));
                localScore++;
            }else if (DBScan(dorp, String.valueOf(staticLetter), 3)){
                yesDorp.setVisibility(View.VISIBLE);
                yesDorp.setEnabled(true);
                noDorp.setVisibility(View.VISIBLE);
                noDorp.setEnabled(true);
                handler.postDelayed(() -> {
                    yesDorp.callOnClick();
                    tvDorp.setTextColor(getResources().getColor(R.color.correct_db_text));
                    tvDorp.startAnimation(vibrateAnim);
                }, animation_delay);
            }else{
                yesDorp.setVisibility(View.VISIBLE);
                yesDorp.setEnabled(true);
                noDorp.setVisibility(View.VISIBLE);
                noDorp.setEnabled(true);
            }
        }
        tvUN.setText(UN);
        tvNaam.setText(naam);
        tvVan.setText(van);
        tvDier.setText(dier);
        tvDorp.setText(dorp);
        if(naam_empty && van_empty && dier_empty && dorp_empty ){
            handler.postDelayed(this::NextUser,700);
        }
    }
    public String randomString(){
        String a = getString(R.string.oops);
        String b = getString(R.string.hard);
        String c = getString(R.string.maybe);
        String d = getString(R.string.trying);
        String e = getString(R.string.help);
        String f = getString(R.string.tooslow);
        String g = getString(R.string.imnew);
        String h = getString(R.string.notime);
        String j = getString(R.string.quick);
        String k = getString(R.string.tt);
        String l = getString(R.string.getthere);
        String m = getString(R.string.nextgame);
        String n = getString(R.string.woeps);
        String o = getString(R.string.waps);
        String p = getString(R.string.ouch);
        String q = getString(R.string.wow);
        String r = getString(R.string.pooh);
        String s = getString(R.string.ahem);
        String t = getString(R.string.hello);

        String[] array = {a,b,c,d,e,f,g,h,j,k,l,m,n,o,p,q,r,s,t};
        Random xx = new Random();
        return array[xx.nextInt(19)];
    }



    public void NextUserButton(){
        nextUserBtn.setOnClickListener(v -> {
            playSound("click");
            NextUser();
        });
    }


    public void DisableToggleButtons(){
        runOnUiThread(() -> {
            yesNaam.setVisibility(View.INVISIBLE);
            yesVan.setVisibility(View.INVISIBLE);
            yesDorp.setVisibility(View.INVISIBLE);
            yesDier.setVisibility(View.INVISIBLE);
            noNaam.setVisibility(View.INVISIBLE);
            noVan.setVisibility(View.INVISIBLE);
            noDier.setVisibility(View.INVISIBLE);
            noDorp.setVisibility(View.INVISIBLE);
        });
    }

    private void Score(String UNLocal){
        if(!soloBool){
            if(localScore != 0) {
                incScoreS(true, localScore, UNLocal, "score", false);
            }
        }else{
            staticBotScore = staticBotScore + localScore;
        }
        localScore = 0;
    }

    boolean CheckSameWord(int wordType, String word, String UN){
        String comp = "";
        if(wordType == 1){
            comp = "Naam";
        }else if(wordType == 2){
            comp = "Van";
        }else if(wordType == 3){
            comp = "Dier";
        }else if(wordType == 4){
            comp = "Dorp";
        }else{
            System.out.println("ERROR +  Wordtype != 1-4 ClientScore");
        }
        System.out.println("Checksameword ++ " + wordType + word + UN);
        if(!user0.isEmpty()){
            if(!username0.equals(UN) && String.valueOf(user0.get(comp)).equalsIgnoreCase(word)){
                return true;
            }
        }
        if(!user1.isEmpty()){
            if(!username1.equals(UN) && String.valueOf(user1.get(comp)).equalsIgnoreCase(word)){
                return true;
            }
        }
        if(!user2.isEmpty()){
            if(!username2.equals(UN) && String.valueOf(user2.get(comp)).equalsIgnoreCase(word)){
                return true;
            }
        }
        if(!user3.isEmpty()){
            if(!username3.equals(UN) && String.valueOf(user3.get(comp)).equalsIgnoreCase(word)){
                return true;
            }
        }
        if(!user4.isEmpty()){
            if(!username4.equals(UN) && String.valueOf(user4.get(comp)).equalsIgnoreCase(word)){
                return true;
            }
        }
        if(!user5.isEmpty()){
            return !username5.equals(UN) && String.valueOf(user5.get(comp)).equalsIgnoreCase(word);
        }
        return false;
    }

    private void GoToNextAct(){
        if(sure){
            sure = false;
            user0.clear();
            user1.clear();
            user2.clear();
            user3.clear();
            user4.clear();
            user5.clear();
            uiRun = 0;
            nextUserPressed = 0;
            DisableToggleButtons();
            incScoreS(true,1, "R", "R", true);
            Intent sicko = new Intent(ServerScore.this, ServerScoreboard.class);
            finish();
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            startActivity(sicko);
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
        }
    }


    private void ButtonListeners(){
        //Naam----------------------------------------------------------------------------
        yesNaam.setOnClickListener(v -> {
            playSound("click");
            runOnUiThread(() -> {
                tvNaam.setTextColor(getResources().getColor(R.color.green_text));
                noNaam.setEnabled(true);
                noNaam.setImageAlpha(alphaStrong);
                yesNaam.setEnabled(false);
                yesNaam.setImageAlpha(alphaWeak);
            });
            localScore++;
            localScore++;
            if(tvNaam.getText().length() > 6){
                localScore++;
            }
        });
        noNaam.setOnClickListener(v -> {
            playSound("click");
            if(!yesNaam.isEnabled()){
                localScore--;
                if(tvNaam.getText().length() > 6){
                    localScore--;
                }
                localScore--;}
            runOnUiThread(() -> {
                tvNaam.setTextColor(getResources().getColor(R.color.red_text));
                noNaam.setEnabled(false);
                noNaam.setImageAlpha(alphaWeak);
                yesNaam.setEnabled(true);
                yesNaam.setImageAlpha(alphaStrong);
            });

        });
        //Van----------------------------------------------------------------------------
        yesVan.setOnClickListener(v -> {
            playSound("click");
            runOnUiThread(() -> {
                tvVan.setTextColor(getResources().getColor(R.color.green_text));
                noVan.setEnabled(true);
                noVan.setImageAlpha(alphaStrong);
                yesVan.setEnabled(false);
                yesVan.setImageAlpha(alphaWeak);
            });
            localScore++;
            localScore++;
            if(tvVan.getText().length() > 6){
                localScore++;
            }
        });
        noVan.setOnClickListener(v -> {
            playSound("click");
            if(!yesVan.isEnabled()){
                localScore--;
                if(tvVan.getText().length() > 6){
                    localScore--;
                }
                localScore--;}
            runOnUiThread(() -> {
                tvVan.setTextColor(getResources().getColor(R.color.red_text));
                noVan.setEnabled(false);
                noVan.setImageAlpha(alphaWeak);
                yesVan.setEnabled(true);
                yesVan.setImageAlpha(alphaStrong);
            });
        });
        //Dier----------------------------------------------------------------------------
        yesDier.setOnClickListener(v -> {
            playSound("click");
            runOnUiThread(() -> {
                tvDier.setTextColor(getResources().getColor(R.color.green_text));
                noDier.setEnabled(true);
                noDier.setImageAlpha(alphaStrong);
                yesDier.setEnabled(false);
                yesDier.setImageAlpha(alphaWeak);
            });
            localScore++;
            if(tvDier.getText().length() > 6){
                localScore++;
            }
            localScore++;
        });
        noDier.setOnClickListener(v -> {
            playSound("click");
            if(!yesDier.isEnabled()){
                localScore--;
                if(tvDier.getText().length() > 6){
                    localScore--;
                }
                localScore--;}
            runOnUiThread(() -> {
                tvDier.setTextColor(getResources().getColor(R.color.red_text));
                noDier.setEnabled(false);
                noDier.setImageAlpha(alphaWeak);
                yesDier.setEnabled(true);
                yesDier.setImageAlpha(alphaStrong);
            });

        });
        //Dorp----------------------------------------------------------------------------
        yesDorp.setOnClickListener(v -> {
            playSound("click");
            runOnUiThread(() -> {
                tvDorp.setTextColor(getResources().getColor(R.color.green_text));
                noDorp.setEnabled(true);
                noDorp.setImageAlpha(alphaStrong);
                yesDorp.setEnabled(false);
                yesDorp.setImageAlpha(alphaWeak);
            });
            localScore++;
            localScore++;
            if(tvDorp.getText().length() > 6){
                localScore++;
            }
        });
        noDorp.setOnClickListener(v -> {
            playSound("click");
            if(!yesDorp.isEnabled()){
                localScore--;
                if(tvDorp.getText().length() > 6){
                    localScore--;
                }
                localScore--;}
            runOnUiThread(() -> {
                tvDorp.setTextColor(getResources().getColor(R.color.red_text));
                noDorp.setEnabled(false);
                noDorp.setImageAlpha(alphaWeak);
                yesDorp.setEnabled(true);
                yesDorp.setImageAlpha(alphaStrong);
            });

        });
        //----------------------------------------------------------------------------
    }


    void SetGameState(){
        rdb.child("A").setValue("C").addOnSuccessListener(aVoid -> System.out.println("Success writing Game state"))
                .addOnFailureListener(e -> handler.postDelayed(this::SetGameState, 2000));
    }

    void CheckUsernames(){
        if (username0.length() < 2 && !user0.isEmpty()){
            user0.clear();
        }else if (username1.length() < 2  && !user1.isEmpty()){
            System.out.println("CLEARING USER1 inside CHECKUSERNAMES");
            user1.clear();
        }else if (username2.length() < 2  && !user2.isEmpty()){
            user2.clear();
        }else if (username3.length() < 2  && !user3.isEmpty()){
            user3.clear();
        }else if (username4.length() < 2 && !user4.isEmpty()){
            user4.clear();
        }else if (username5.length() < 2  && !user5.isEmpty()){
            user5.clear();
        }
            if(staticAct.equals("SS")) {
            handler.postDelayed(this::CheckUsernames, 500);
        }
    }

    void NextUser(){
        if(staticAct.equalsIgnoreCase("SS")){
            if((!yesNaam.isEnabled() || !noNaam.isEnabled() || yesNaam.getVisibility() == View.INVISIBLE)
                    && (!yesVan.isEnabled() || !noVan.isEnabled() || yesVan.getVisibility() == View.INVISIBLE)
                    && (!yesDorp.isEnabled() || !noDorp.isEnabled() || yesDorp.getVisibility() == View.INVISIBLE )
                    && (!yesDier.isEnabled() || !noDier.isEnabled() || yesDier.getVisibility() == View.INVISIBLE)){
                noNaam.setImageAlpha(alphaStrong);
                yesNaam.setImageAlpha(alphaStrong);
                noDorp.setImageAlpha(alphaStrong);
                yesDorp.setImageAlpha(alphaStrong);
                noDier.setImageAlpha(alphaStrong);
                yesDier.setImageAlpha(alphaStrong);
                noVan.setImageAlpha(alphaStrong);
                yesVan.setImageAlpha(alphaStrong);
                UNEval = tvUN.getText().toString();
                if(!soloBool){
                    if(UNEval.equals(username0)) {
                        Score(username0);
                        getAnswers();
                    }else if(UNEval.equals(username1)) {
                        Score(username1);
                        getAnswers();
                    }else if(UNEval.equals(username2)) {
                        Score(username2);
                        getAnswers();
                    }else if(UNEval.equals(username3)) {
                        Score(username3);
                        getAnswers();
                    }else if(UNEval.equals(username4)) {
                        Score(username4);
                        getAnswers();
                    }else if(UNEval.equals(username5)) {
                        Score(username5);
                        getAnswers();
                    }
                    runOnUiThread(() -> nextUserBtn.setEnabled(false));
                    handler.postDelayed(() -> runOnUiThread(() -> nextUserBtn.setEnabled(true)),1000);
                }else{
                    Score(username1);
                    GoToNextAct();
                }
            }else{
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.please_vote_first, Snackbar.LENGTH_SHORT);
                snackbar.setTextColor(getResources().getColor(R.color.yellow_text));
                snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                snackbar.show();
            }
        }
    }

    void MarkUserInput() {
        handler.postDelayed(() -> {
            if (soloBool) {
                if(singleStaticNaam.length() < 2){
                    singleStaticNaam = "ZZZZZZZZZZZZ";
                }
                if(singleStaticVan.length() < 2){
                    singleStaticVan = "ZZZZZZZZZZZZ";
                }
                if(singleStaticDier.length() < 2){
                    singleStaticDier = "ZZZZZZZZZZZZ";
                }
                if(singleStaticDorp.length() < 2){
                    singleStaticDorp = "ZZZZZZZZZZZZ";
                }
                if (DBScan(singleStaticNaam, String.valueOf(staticLetter), 0)) {
                    if (!singleStaticNaam.equalsIgnoreCase(user1.get("Naam").toString())) {
                        if (singleStaticNaam.length() > 6) {
                            staticSoloScore++;
                        }
                        staticSoloScore++;
                    }
                    staticSoloScore++;
                }
                if (DBScan(singleStaticVan, String.valueOf(staticLetter), 1)) {
                    if (!singleStaticVan.equalsIgnoreCase(user1.get("Van").toString())) {
                        if (singleStaticVan.length() > 6) {
                            staticSoloScore++;
                        }
                        staticSoloScore++;
                    }
                    staticSoloScore++;
                }
                System.out.println("Starting Scan DB TOWNS");
                if (DBScan(singleStaticDorp, String.valueOf(staticLetter), 3)) {
                    if (!singleStaticDorp.equalsIgnoreCase(user1.get("Dorp").toString())) {
                        if (singleStaticDorp.length() > 6) {
                            staticSoloScore++;
                        }
                        staticSoloScore++;
                    }
                    staticSoloScore++;
                }
                System.out.println("Done Scanning DB TOWNS");
                if(gameLang.equals("en")){
                    if (DBScan(singleStaticDier, String.valueOf(staticLetter), 2)) {
                        if (!singleStaticDier.equalsIgnoreCase(user1.get("Dier").toString())) {
                            if (singleStaticDier.length() > 6) {
                                staticSoloScore++;
                            }
                            staticSoloScore++;
                        }
                        staticSoloScore++;
                    }
                }else{
                    if (DBScan(singleStaticDier, String.valueOf(staticLetter), 4)) {
                        if (!singleStaticDier.equalsIgnoreCase(user1.get("Dier").toString())) {
                            if (singleStaticDier.length() > 6) {
                                staticSoloScore++;
                            }
                            staticSoloScore++;
                        }
                        staticSoloScore++;
                    }
                }

                System.out.println("Staticsoloscore = " + staticSoloScore);
            }
        }, 800);
    }

    private void CheckIfBotDone(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getAnswers();
            }
        }, 2000);
    }



    boolean DBScan(String what, String letter, int type){
        if(type == 0){
            for (int i=0; i< 26; i++) {
                if(charArray[i] == staticLetter){
                    return  namesDB.SQLExist(what, letter);
                }
            }
        }else if(type == 1){
            for (int i=0; i< 26; i++) {
                if(charArray[i] == staticLetter){
                    return  surnamesDB.SQLExist(what, letter);
                }
            }
        }else if(type == 2){
            for (int i=0; i< 26; i++) {
                if(charArray[i] == staticLetter){
                    return  animalsDB.SQLExist(what, letter);
                }
            }
        }else if(type == 3){
            for (int i=0; i< 26; i++) {
                if(charArray[i] == staticLetter){
                    return townsMarkDB.SQLExist(what, letter );
                }
            }
        }else if(type == 4){
            for (int i=0; i< 26; i++) {
                if(charArray[i] == staticLetter){
                    return  diereDB.SQLExist(what, letter);
                }
            }
            return false;
        }else{
            return false;
        }
        return false;
    }

    void SetupBot(){
        Random rand = new Random();
        String randNaam;
        String randVan;
        String randDier;
        String randDorp;
        for (int i=0; i< 26; i++) {
            if(charArray[i] == staticLetter){
                System.out.println("charArray[i]" + charArray[i]);
                System.out.println("staticLetter" + staticLetter);
                char tt = staticLetter;
                int naam;
                int van;
                int dier;
                int dorp;
                if(tt == 'A' || tt == 'C'|| tt == 'D'|| tt == 'J'|| tt == 'K'|| tt == 'M'|| tt == 'S' || tt == 'T'|| tt == 'B'){
                    naam = rand.nextInt(869);
                }else{
                    naam = rand.nextInt(645);
                }
                if(tt == 'B' ||tt == 'S' || tt == 'H'|| tt == 'M') {
                    van = rand.nextInt(571);
                }else if(tt == 'C' || tt == 'W' || tt == 'R' || tt == 'G' || tt == 'D'){
                    van = rand.nextInt(347);
                }else if(tt == 'P' || tt == 'K' || tt == 'L' || tt == 'T' || tt == 'A'|| tt == 'F'){
                    van = rand.nextInt(249);
                }else if (tt == 'Z' || tt == 'I' || tt == 'Y' || tt == 'U' || tt == 'Q'|| tt == 'X'){
                    van = rand.nextInt(29);
                }else{
                    van = rand.nextInt(134);
                }
                if(gameLang.equals("en")){
                    if(tt == 'B' ||tt == 'S' ||tt == 'C') {
                        dier = rand.nextInt(151);
                    }else{
                        dier = rand.nextInt(94);
                    }
                }else{
                    if(tt == 'B' ||tt == 'S') {
                        dier = rand.nextInt(37);
                    }else if (tt == 'G'||tt == 'K'||tt == 'R'||tt == 'W'||tt == 'V'){
                        dier = rand.nextInt(28);
                    }else{
                        dier = rand.nextInt(10);
                    }
                }

                 if (tt == 'B'|| tt == 'C'){
                    dorp = rand.nextInt(99);
                }else if (tt == 'M' ||tt == 'A'||tt == 'S' ){
                    dorp = rand.nextInt(47);
                }else{
                    dorp = rand.nextInt(37);
                }
                if(botHasName){
                    randNaam = namesDB.GetRand(i+1, naam);
                    user1.put("Naam", randNaam);
                }
                if(botHasSurname){
                    randVan = surnamesDB.GetRand(i+1, van);
                    user1.put("Van", randVan);
                }
                if(gameLang.equals("en")){
                    if(botHasAnimal){
                        randDier = animalsDB.GetRand(i+1, dier);
                        user1.put("Dier", randDier);
                    }
                }else{
                    if(botHasAnimal){
                        randDier = diereDB.GetRand(i+1, dier);
                        user1.put("Dier", randDier);
                    }
                }
                if(botHasTown){
                    randDorp = townsDB.GetRand(i+1, dorp);
                    user1.put("Dorp", randDorp);
                    System.out.println("user1 = " + user1);
                }
            }
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
        bg_0 = findViewById(R.id.bg_0_ss);
        bg_1 = findViewById(R.id.bg_2_ss);
        bg_2 = findViewById(R.id.bg_3_ss);
        bg_3 = findViewById(R.id.bg_4_ss);
        bg_4 = findViewById(R.id.bg_6_ss);
        bg_5 = findViewById(R.id.bg_7_ss);
        bg_6 = findViewById(R.id.bg_8_ss);
        con = this;
        BackgroundClass.SetBackground(con, findViewById(R.id.ss_mv));
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
                    if(staticAct.equals("SS")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_0.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_0);
                        bghand0.postDelayed(bgrun0, bgdur);
                    }
                };
                bgrun1 = () -> {
                    if(staticAct.equals("SS")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_1.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_1);
                        bghand1.postDelayed(bgrun1, bgdur);
                    }
                };
                bgrun2 = () -> {
                    if(staticAct.equals("SS")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_2.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_2);
                        bghand2.postDelayed(bgrun2, bgdur);
                    }
                };
                bgrun3 = () -> {
                    if(staticAct.equals("SS")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_3.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_3);
                        bghand3.postDelayed(bgrun3, bgdur);
                    }
                };
                bgrun4 = () -> {
                    if(staticAct.equals("SS")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_4.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_4);
                        bghand4.postDelayed(bgrun4, bgdur);
                    }
                };
                bgrun5 = () -> {
                    if(staticAct.equals("SS")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_5.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_5);
                        bghand5.postDelayed(bgrun5, bgdur);
                    }
                };
                bgrun6 = () -> {
                    if(staticAct.equals("SS")){
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