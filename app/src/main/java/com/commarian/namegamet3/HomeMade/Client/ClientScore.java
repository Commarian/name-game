package com.commarian.namegamet3.HomeMade.Client;

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
import android.widget.Toast;

import com.commarian.namegamet3.HomeMade.Classes.BackgroundClass;
import com.commarian.namegamet3.HomeMade.Classes.LocaleHelper;
import com.commarian.namegamet3.HomeMade.DBs.AnimalsDBHelper;
import com.commarian.namegamet3.HomeMade.DBs.DiereDBHelper;
import com.commarian.namegamet3.HomeMade.DBs.NamesDBHelper;
import com.commarian.namegamet3.HomeMade.DBs.SurnamesDBHelper;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import static com.commarian.namegamet3.HomeMade.Classes.AnimHandler.animHandler;
import static com.commarian.namegamet3.HomeMade.Client.ClientActivity1.incScoreC;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.Profanity;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.adRequest;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.animatedBackgrounds;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar0code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar1code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar2code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar3code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar4code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar5code;
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
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticAct;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticLetter;
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

public class ClientScore extends AppCompatActivity {
    DatabaseReference rdb = FirebaseDatabase.getInstance(firebaseServer).getReference(lobbyName);
    //FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> user0 = new HashMap<>();
    Map<String, Object> user1 = new HashMap<>();
    Map<String, Object> user2 = new HashMap<>();
    Map<String, Object> user3 = new HashMap<>();
    Map<String, Object> user4 = new HashMap<>();
    Map<String, Object> user5 = new HashMap<>();

    NamesDBHelper namesDB = new NamesDBHelper(ClientScore.this);
    SurnamesDBHelper surnamesDB = new SurnamesDBHelper(ClientScore.this);
    AnimalsDBHelper animalsDB = new AnimalsDBHelper(ClientScore.this);
    TownsMarkDBHelper townsMarkDB = new TownsMarkDBHelper(ClientScore.this);
    DiereDBHelper diereDB = new DiereDBHelper(ClientScore.this);


    Animation vibrateAnim;

    Boolean check = true;
    Boolean spamCatch = true;

    

    TextView tvUN ;
    TextView tvNaam ;
    TextView tvNaam2 ;
    TextView tvVan;
    TextView tvVan2;
    TextView tvDier ;
    TextView tvDier2 ;
    TextView tvDorp;
    TextView tvDorp2;
    TextView tvWait;
    TextView tvScore;


    ProgressBar PB;

    Handler handler = new Handler();
    Button nextUserBtn;

    ImageButton yesNaam;
    ImageButton yesVan;
    ImageButton yesDier;
    ImageButton yesDorp;
    ImageButton noNaam;
    ImageButton noVan;
    ImageButton noDier;
    ImageButton noDorp;

    char[] charArray;

    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    boolean u0Fin;
    boolean u1Fin;
    boolean u2Fin;
    boolean u3Fin;
    boolean u4Fin;
    boolean u5Fin;

    int refreshDelay = 0;

    int user0Retry, user1Retry, user2Retry, user3Retry, user4Retry, user5Retry;


    String UNEval = "";

    AppCompatImageView ivAvatar;
    AppCompatImageView leaveBtn;
    AppCompatImageView helperCast;

    AlertDialog.Builder builder ;

    int userCountLocal = 0;
    int uiRun = 0;
    int localScore = 0;


    Boolean fixable = true;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_score);
        charArray = alphabet.toCharArray();
        staticAct = "CS";
        stopCalled = false;
        Backgrounds();

        //System.out.println("Username1 = "  + username1);
        //System.out.println("Username2 = "  + username2);
        //System.out.println("Username3 = "  + username3);
        //System.out.println("Username4 = "  + username4);

        user0.put("Naam", singleStaticNaam);
        user0.put("Van", singleStaticVan);
        user0.put("Dier", singleStaticDier);
        user0.put("Dorp", singleStaticDorp);
        //System.out.println("user 0  = "  + user0);
        AdView adView = findViewById(R.id.adViewCS);

        adView.loadAd(adRequest);

        leaveBtn = findViewById(R.id.leave_game_client_score);

        refreshDelay = 2000;

        nextUserBtn= findViewById(R.id.nextUserBtnC);
        PB = findViewById(R.id.PBCS);
        tvUN = findViewById(R.id.tvUNC);
        tvNaam = findViewById(R.id.tvACNaam);
        tvNaam2 = findViewById(R.id.tvACNaam2);
        tvVan = findViewById(R.id.tvACVan);
        tvVan2 = findViewById(R.id.tvACVan2);
        tvDier = findViewById(R.id.tvACDier);
        tvDier2 = findViewById(R.id.tvACDier2);
        tvDorp = findViewById(R.id.tvACDorp);
        tvDorp2 = findViewById(R.id.tvACDorp2);
        tvWait = findViewById(R.id.tvWaitC);
        tvScore = findViewById(R.id.tvUNC2);
        helperCast = findViewById(R.id.helper_cast_vote_client);

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

        //Checking debugging
        //System.out.println("username0 " + username0);
        //System.out.println("username1 " + username1);
        //System.out.println("username2 " + username2);


        yesNaam = findViewById(R.id.correctNaamBtnC);
        yesVan = findViewById(R.id.correctVanBtnC);
        yesDier= findViewById(R.id.correctDierBtnC);
        yesDorp = findViewById(R.id.correctDorpBtnC);
        noNaam = findViewById(R.id.wrongNaamBtnC);
        noVan = findViewById(R.id.wrongVanBtnC);
        noDier = findViewById(R.id.wrongDierBtnC);
        noDorp = findViewById(R.id.wrongDorpBtnC);


        ivAvatar = findViewById(R.id.avatarCS);
        CheckUsernames();

        vibrateAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.vib);

        fixable = true;
        check = true;
        spamCatch = true;

        if(difficulty.equals("Custom")){
            tvNaam2.setText(custom1);
            tvVan2.setText(custom2);
            tvDier2.setText(custom3);
            tvDorp2.setText(custom4);
        }

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
        ButtonListeners();

        GameAliveWatchDog();

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
                            //incScoreC(false, 1, "X", "X", true);
                            rdb.child(staticUsername).removeValue().addOnCompleteListener(task -> {
                                user0.clear();
                                user1.clear();
                                user2.clear();
                                user3.clear();
                                user4.clear();
                                user5.clear();
                                userCountLocal = 0;
                                uiRun = 0;
                                DisableToggleButtons();
                                finish();
                            });
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
                        //incScoreC(false, 1, "X", "X", true);
                        rdb.child(staticUsername).removeValue().addOnCompleteListener(task -> {
                            user0.clear();
                            user1.clear();
                            user2.clear();
                            user3.clear();
                            user4.clear();
                            user5.clear();
                            userCountLocal = 0;
                            uiRun = 0;
                            DisableToggleButtons();
                            finish();
                        });
                    });
            builder.setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        });

    }

    private void waitClients(){
        if(userCount < 2 ){
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), getString(R.string.leaving_game_no_players), Snackbar.LENGTH_SHORT);
            snackbar.setTextColor(getResources().getColor(R.color.yellow_text));
            snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
            snackbar.show();
            finish();
        }else{
            handler.postDelayed(this::getAnswers,1800);
            handler.postDelayed(this::NextUserButton, 500);
        }
    }


    private void getAnswers(){
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
            System.out.println("users = " + username0);
            System.out.println("users = " + username1);
            System.out.println("users = " + username2);
            System.out.println("users = " + username3);
            System.out.println("users = " + username4);
            System.out.println("users = " + username5);
            System.out.println("retries = " + user0Retry);
            System.out.println("retries = " + user1Retry);
            System.out.println("retries = " + user2Retry);
            System.out.println("retries = " + user3Retry);
            System.out.println("retries = " + user4Retry);
            System.out.println("retries = " + user5Retry);
            System.out.println("finished = " + u0Fin);
            System.out.println("finished = " + u1Fin);
            System.out.println("finished = " + u2Fin);
            System.out.println("finished = " + u3Fin);
            System.out.println("finished = " + u4Fin);
            System.out.println("finished = " + u5Fin);
            GoToNextAct();
        }
    }


    private void updateUI(String UN, Map<String, Object> map){
        int animation_delay = 750;
        String naam = String.valueOf(map.get("Naam"));
        String van = String.valueOf(map.get("Van"));
        String dier = String.valueOf(map.get("Dier"));
        String dorp = String.valueOf(map.get("Dorp"));

        tvNaam.setTextColor(getResources().getColor(R.color.white));
        tvVan.setTextColor(getResources().getColor(R.color.white));
        tvDorp.setTextColor(getResources().getColor(R.color.white));
        tvDier.setTextColor(getResources().getColor(R.color.white));

        boolean naam_empty = false;
        boolean van_empty = false;
        boolean dier_empty = false;
        boolean dorp_empty = false;

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

            yesNaam.setVisibility(View.VISIBLE);
            yesNaam.setEnabled(true);
            yesVan.setVisibility(View.VISIBLE);
            yesVan.setEnabled(true);
            yesDorp.setVisibility(View.VISIBLE);
            yesDorp.setEnabled(true);
            yesDier.setVisibility(View.VISIBLE);
            yesDier.setEnabled(true);
            noNaam.setVisibility(View.VISIBLE);
            noNaam.setEnabled(true);
            noVan.setVisibility(View.VISIBLE);
            noVan.setEnabled(true);
            noDier.setVisibility(View.VISIBLE);
            noDier.setEnabled(true);
            noDorp.setVisibility(View.VISIBLE);
            noDorp.setEnabled(true);
            ivAvatar.setVisibility(View.VISIBLE);
            ivAvatar.setEnabled(true);
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
                    System.out.println("Successfully went into dbscan");
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




    public void NextUserButton(){
        nextUserBtn.setOnClickListener(v -> {
            playSound("click");
            NextUser();
        });
    }


    private void Score(String UNLocal){
        if(localScore != 0){
            incScoreC(true, localScore, UNLocal, "score", false);
        }
        localScore = 0;
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

    boolean CheckSameWord(int wordType, String word, String UN){
        System.out.println("user 0 = " + user0);
        System.out.println("user 1 = " + user1);
        System.out.println("user 2 = " + user2);
        System.out.println("user 3 = " + user3);
        System.out.println("user 4 = " + user4);
        System.out.println("user 5 = " + user5);
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
        if(check){
            check = false;
            user0.clear();
            user1.clear();
            user2.clear();
            user3.clear();
            user4.clear();
            user5.clear();
            userCountLocal = 0;
            uiRun = 0;
            incScoreC(true,1,"R", "R", true);
            DisableToggleButtons();
            Intent sicko = new Intent(ClientScore.this, ClientScoreboard.class);
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
            localScore++;
            if(tvDier.getText().length() > 6){
                localScore++;
            }
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
    int alphaWeak =  50;
    int alphaStrong = 255;


    void CheckUsernames(){
        if (username0.length() < 2 && !user0.isEmpty()){
            user0.clear();
        }else if (username1.length() < 2  && !user1.isEmpty()){
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
        handler.postDelayed(() -> {
            if(staticAct.equalsIgnoreCase("CS")){
                CheckUsernames();
            }

        }, 500);
    }

    void NextUser(){
        if(staticAct.equalsIgnoreCase("CS")){
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
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.please_vote_first, Snackbar.LENGTH_SHORT);
                snackbar.setTextColor(getResources().getColor(R.color.yellow_text));
                snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                snackbar.show();
            }
        }
    }
    void GameAliveWatchDog(){
        handler.postDelayed(() -> {
            if(staticAct.equalsIgnoreCase("CS")) {
                if (userCount < 2) {
                    Toast.makeText(this, R.string.the_host_closed, Toast.LENGTH_LONG).show();
                    handler.postDelayed(this::finish, 1000);
                } else {
                    GameAliveWatchDog();
                }
            }
        },5000);
    }

    boolean DBScan(String what, String letter, int type){
        System.out.println("DBSCANNING + " + what + letter + type);
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
        bg_0 = findViewById(R.id.bg_0_cs);
        bg_1 = findViewById(R.id.bg_2_cs);
        bg_2 = findViewById(R.id.bg_3_cs);
        bg_3 = findViewById(R.id.bg_4_cs);
        bg_4 = findViewById(R.id.bg_6_cs);
        bg_5 = findViewById(R.id.bg_7_cs);
        bg_6 = findViewById(R.id.bg_8_cs);
        con = this;
        BackgroundClass.SetBackground(con, findViewById(R.id.cs_mv));
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
                    if(staticAct.equals("CS")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_0.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_0);
                        bghand0.postDelayed(bgrun0, bgdur);
                    }
                };
                bgrun1 = () -> {
                    if(staticAct.equals("CS")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_1.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_1);
                        bghand1.postDelayed(bgrun1, bgdur);
                    }
                };
                bgrun2 = () -> {
                    if(staticAct.equals("CS")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_2.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_2);
                        bghand2.postDelayed(bgrun2, bgdur);
                    }
                };
                bgrun3 = () -> {
                    if(staticAct.equals("CS")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_3.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_3);
                        bghand3.postDelayed(bgrun3, bgdur);
                    }
                };
                bgrun4 = () -> {
                    if(staticAct.equals("CS")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_4.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_4);
                        bghand4.postDelayed(bgrun4, bgdur);
                    }
                };
                bgrun5 = () -> {
                    if(staticAct.equals("CS")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_5.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_5);
                        bghand5.postDelayed(bgrun5, bgdur);
                    }
                };
                bgrun6 = () -> {
                    if(staticAct.equals("CS")){
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