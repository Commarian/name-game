package com.commarian.namegamet3.HomeMade.Server;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.commarian.namegamet3.HomeMade.Classes.AppPreferences;
import com.commarian.namegamet3.HomeMade.Classes.BackgroundClass;
import com.commarian.namegamet3.HomeMade.Classes.LocaleHelper;
import com.commarian.namegamet3.HomeMade.DBs.NamesDBHelper;
import com.commarian.namegamet3.HomeMade.Extras.MessageFrag;
import com.commarian.namegamet3.R;
import com.google.android.gms.ads.AdView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentContainerView;

import static com.commarian.namegamet3.HomeMade.Classes.AnimHandler.animHandler;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.adRequest;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.animatedBackgrounds;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar0code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar1code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar2code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar3code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar4code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar5code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.childAdded;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.difficulty;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.firebaseServer;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.gameLang;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.ir;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.lobbyName;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.myAvatar;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.newMessage;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.noMoMessages;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.soloBool;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticAct;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticServer;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticUsername;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.stopCalled;
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

public class ServerActivity1 extends AppCompatActivity {
    DatabaseReference rdb = FirebaseDatabase.getInstance(firebaseServer).getReference(lobbyName);
    DatabaseReference cdb = FirebaseDatabase.getInstance("https://letsnamegame-default-rtdb.firebaseio.com").getReference(lobbyName);

    AppPreferences _appPrefs;
    TextView tvPlace0;
    TextView tvPlace1;
    TextView tvPlace2;
    TextView tvPlace3;
    TextView tvPlace4 ;
    TextView tvPlace5 ;
    TextView tvReadyNum ;
    TextView CD;
    TextView info;

    AppCompatImageView avatar0;
    AppCompatImageView avatar1;
    AppCompatImageView avatar2;
    AppCompatImageView avatar3;
    AppCompatImageView avatar4;
    AppCompatImageView avatar5;

    LinearLayout hor0;
    LinearLayout hor1;
    LinearLayout hor2;
    LinearLayout hor3;
    LinearLayout hor4;
    LinearLayout hor5;

    AppCompatImageView leaveBtn;
    AppCompatImageView helper_ready;

    boolean avatar0done = false;
    boolean avatar1done = false;
    boolean avatar2done = false;
    boolean avatar3done = false;
    boolean avatar4done = false;
    boolean avatar5done = false;






    FragmentContainerView fragContainer;
    boolean fragment = true;
    boolean readied = false;

    AppCompatImageView  msgBtn;

    


    ProgressBar PBS1;
    Boolean failsafe = true;


    int textCycleTimes = 0;

    Button readyBtn;

    int cycleDelay = 1000;

    Animation vibrateAnim;
    Animation enterAnim;
    Animation exitAnim;
    Animation testAnim;


    final Handler handler = new Handler();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server1);

        staticAct = "SA1";

        noMoMessages = false;

        userCount = 0;

        avatar0code = 0;
        avatar1code = 0;
        avatar2code = 0;
        avatar3code = 0;
        avatar4code = 0;
        avatar5code = 0;

        if(difficulty.equals("")){
            difficulty = "Med";
        }
        Backgrounds();

        staticServer = true;

        avatar0done = false;
        avatar1done = false;
        avatar2done = false;
        avatar3done = false;
        avatar4done = false;
        avatar5done = false;


        username0 = "";
        username1 = "";
        username2 = "";
        username3 = "";
        username4 = "";
        username5 = "";

        fragment = true;
        fragContainer = findViewById(R.id.fragment_container_server_1);
        msgBtn = findViewById(R.id.messageButtonServer1);
        PBS1 = findViewById(R.id.PBS1);
        CD = findViewById(R.id.tvCDSA);
        tvPlace0 = findViewById(R.id.tvU0);
        tvPlace1 = findViewById(R.id.tvU1);
        tvPlace2 = findViewById(R.id.tvU2);
        tvPlace3 = findViewById(R.id.tvU3);
        tvPlace4 = findViewById(R.id.tvU4);
        tvPlace5 = findViewById(R.id.tvU5);
        hor0 = findViewById(R.id.whore0);
        hor1 = findViewById(R.id.whore1);
        hor2 = findViewById(R.id.whore2);
        hor3 = findViewById(R.id.whore3);
        hor4 = findViewById(R.id.whore4);
        hor5 = findViewById(R.id.whore5);
        tvReadyNum = findViewById(R.id.tvReadySA1);

        info = findViewById(R.id.tvInfoS1);
        PBS1.setVisibility(View.VISIBLE);
        readyBtn = findViewById(R.id.btnReadySA1);

        avatar0 = findViewById(R.id.avatarS1);
        avatar1 = findViewById(R.id.avatarS2);
        avatar2 = findViewById(R.id.avatarS3);
        avatar3 = findViewById(R.id.avatarS4);
        avatar4 = findViewById(R.id.avatarS5);
        avatar5 = findViewById(R.id.avatarS6);

        leaveBtn = findViewById(R.id.leave_game_server_1);
        helper_ready = findViewById(R.id.helper_ready_s1);

        //Ads
        AdView adView = (AdView) findViewById(R.id.adViewSA1);
        adView.loadAd(adRequest);



        CycleText();
        AlertDialog.Builder builder;
        readied = false;

        _appPrefs = new AppPreferences(getApplicationContext());

        childAdded = 0;
        msgBtn.setVisibility(View.INVISIBLE);
        CD.setVisibility(View.INVISIBLE);
        info.setText(R.string.finishing_up);
        failsafe = true;
        if(!soloBool){
            rdb.child("R").addValueEventListener(checkReady);
            SetGameState("A");
            iniScores();
            iniRest();
            MessageAlert(msgBtn);
        }else{
            username0 = staticUsername;
            hor0.setVisibility(View.VISIBLE);
            PBS1.setVisibility(View.GONE);
            tvPlace0.setVisibility(View.VISIBLE);
            CD.setVisibility(View.VISIBLE);
            GiveBotName();
        }

        vibrateAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.vib);
        testAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.test);
        enterAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.enter_act_quick);
        exitAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.exit_act_quick);

        CheckUsernames();
        if(fragment){
            if (savedInstanceState == null) {
                fragment = false;
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .add(R.id.fragment_container_server_1, MessageFrag.class, null)
                        .commit();
            }
            else{
                fragment = false;
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container_server_1, MessageFrag.class, null)
                        .commit();
            }
        }
        msgBtn.setOnClickListener(v -> {
            newMessage = false;
            msgBtn.setImageResource(R.drawable.old_message);
            if(this.getCurrentFocus() != null){
                if(this.getCurrentFocus().getWindowToken()!= null){
                    InputMethodManager inputMethodManager = (InputMethodManager)this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
                }
            }
            if(fragment){
                if (savedInstanceState == null) {
                    fragment = false;
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .add(R.id.fragment_container_server_1, MessageFrag.class, null)
                            .commit();
                }else{
                    fragment = false;
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragment_container_server_1, MessageFrag.class, null)
                            .commit();
                }
            }
            int isVis = fragContainer.getVisibility();
            if(isVis != 8){
                fragContainer.setVisibility(View.GONE);
            }else{
                fragContainer.setVisibility(View.VISIBLE);
            }

        });




        builder = new AlertDialog.Builder(this, R.style.AlertDialogThemeCustom);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if(fragContainer.getVisibility() == View.VISIBLE){
                    fragContainer.setVisibility(View.GONE);
                }else{
                builder.setCancelable(true);
                builder.setTitle(R.string.leave_title);
                builder.setMessage(R.string.are_you_sure_leave);
                builder.setPositiveButton(R.string.yes,
                        (dialog, which) -> {
                            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), getString(R.string.leaving_game), Snackbar.LENGTH_SHORT);
                            snackbar.setTextColor(getResources().getColor(R.color.yellow_text));
                            snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                            snackbar.show();
                            removeJunk();
                            rdb.removeValue().addOnCompleteListener(task1 -> cdb.removeValue().addOnCompleteListener(task -> finish()));
                        });
                builder.setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss());

                AlertDialog dialog = builder.create();
                dialog.show();
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        leaveBtn.setOnClickListener(v -> {
            if(fragContainer.getVisibility() == View.VISIBLE){
                fragContainer.setVisibility(View.GONE);
            }else{
                builder.setCancelable(true);
                builder.setTitle(R.string.leave_title);
                builder.setMessage(R.string.are_you_sure_leave);
                builder.setPositiveButton(R.string.yes,
                        (dialog, which) -> {
                            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), getString(R.string.leaving_game), Snackbar.LENGTH_SHORT);
                            snackbar.setTextColor(getResources().getColor(R.color.yellow_text));
                            snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                            snackbar.show();
                            removeJunk();
                            rdb.removeValue().addOnCompleteListener(task1 -> cdb.removeValue().addOnCompleteListener(task -> finish()));
                        });
                builder.setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss());

                AlertDialog dialog = builder.create();
                dialog.show();
            }

        });
        readyBtn.setOnClickListener(v -> {
            handler.postDelayed(() -> {
                if(soloBool){
                    cdt.start();
                    readyBtn.setVisibility(View.INVISIBLE);
                    helper_ready.setVisibility(View.INVISIBLE);
                }
            }, 220);
            playSound("click");
            readyBtn.setVisibility(View.INVISIBLE);
            helper_ready.setVisibility(View.INVISIBLE);
            if(!readied){
                readied = true;
                incScoreS(true, 1, "R", "R", true);
            }

        });

        avatar0.setImageResource(myAvatar);
        avatar0.setVisibility(View.VISIBLE);

    }

    CountDownTimer cdt = new CountDownTimer(3000, 900) {
        @Override
        public void onTick(long l) {
            double lank = (double)l/1000;
            playSound("click");
            int x = (int) Math.round(lank);
            System.out.println("x + " + x);
            String str = String.valueOf(x);
            runOnUiThread(() -> {
                CD.startAnimation(testAnim);
                CD.setText(str);
            });
        }
        @Override
        public void onFinish() {
            if(failsafe){
                if(getSupportFragmentManager().findFragmentById(R.id.fragment_container_server_1) != null){
                    fragContainer.setVisibility(View.GONE);
                    getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.fragment_container_server_1));
                }

                failsafe = false;
                Intent o = new Intent(ServerActivity1.this, ServerGameActivity.class);
                removeJunk();
                finish();
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                startActivity(o);
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);

            }
        }
    };

    ValueEventListener checkReady = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            System.out.println("DEBUGGING INSIDE Checkready SA1 VEL");
            if (snapshot.getValue() != null){
                long r = (long)snapshot.getValue();
                System.out.println("R IS UPDATED WITH R : " + r);
                if(r <= userCount && r != 0){
                    String str = r + getString(R.string.of_special) + userCount + getString(R.string.players_ready_special);
                    tvReadyNum.setText(str);
                }
                if(r >= userCount && userCount > 1){
                    SetGameState("B");
                    cdt.start();
                }
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            rdb.child("R").removeEventListener(checkReady);
            rdb.child("R").addValueEventListener(checkReady);
        }
    };


    public ChildEventListener removeListener = new ChildEventListener(){
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            System.out.println("onChildAdded = " + snapshot);
            if(staticAct.equalsIgnoreCase("SA1")) {
                runOnUiThread(() -> {
                    PBS1.setVisibility(View.GONE);
                    tvPlace0.setVisibility(View.VISIBLE);
                    msgBtn.setVisibility(View.VISIBLE);
                    CD.setVisibility(View.VISIBLE);
                });
            }
            if(snapshot.getKey() !=null){
            String snappy = snapshot.getKey();
                if(snappy.equals("S")){
                    stopCalled = true;
                }
                if(snappy.length() > 1){
                    if(childAdded == 0){
                        username0  = snappy;
                        childAdded++;
                        incScoreS(true, 1, "X", "X", true);
                        userCount++;
                        if(snapshot.child("pp").getValue() != null){
                            avatar0code = SetAvatarCode(Integer.parseInt(snapshot.child("pp").getValue().toString()));
                        }else {
                            avatar0code = R.drawable.avatar0;
                        }
                        userHandler(avatar0, tvPlace0, avatar0code, username0, true, hor0);
                    }else
                    if(childAdded == 1){
                        username1  = snappy;
                        childAdded++;
                        incScoreS(true, 1, "X", "X", true);
                        userCount++;
                        if(snapshot.child("pp").getValue() != null){
                            avatar1code = SetAvatarCode(Integer.parseInt(snapshot.child("pp").getValue().toString()));
                        }else {
                            avatar1code = R.drawable.avatar1;
                        }
                        userHandler(avatar1, tvPlace1, avatar1code, username1, true, hor1);
                    }else
                    if(childAdded == 2){
                        username2  = snappy;
                        childAdded++;
                        incScoreS(true, 1, "X", "X", true);
                        userCount++;
                        if(snapshot.child("pp").getValue() != null){
                            avatar2code = SetAvatarCode(Integer.parseInt(snapshot.child("pp").getValue().toString()));
                        }else {
                            avatar2code = R.drawable.avatar2;
                        }
                        userHandler(avatar2, tvPlace2, avatar2code, username2, true, hor2);
                    }else
                    if(childAdded == 3){
                        username3  = snappy;
                        childAdded++;
                        incScoreS(true, 1, "X", "X", true);
                        userCount++;
                        if(snapshot.child("pp").getValue() != null){
                            avatar3code = SetAvatarCode(Integer.parseInt(snapshot.child("pp").getValue().toString()));
                        }else {
                            avatar3code = R.drawable.avatar3;
                        }
                        userHandler(avatar3, tvPlace3, avatar3code, username3, true, hor3);
                    }else
                    if(childAdded == 4){
                        username4  = snappy;
                        childAdded++;
                        incScoreS(true, 1, "X", "X", true);
                        userCount++;
                        if(snapshot.child("pp").getValue() != null){
                            avatar4code = SetAvatarCode(Integer.parseInt(snapshot.child("pp").getValue().toString()));
                        }else {
                            avatar4code = R.drawable.avatar4;
                        }
                        userHandler(avatar4, tvPlace4, avatar4code, username4, true, hor4);
                    }else
                    if(childAdded == 5){
                        username5  = snappy;
                        childAdded++;
                        incScoreS(true, 1, "X", "X", true);
                        userCount++;
                        if(snapshot.child("pp").getValue() != null){
                            avatar5code = SetAvatarCode(Integer.parseInt(snapshot.child("pp").getValue().toString()));
                        }else {
                            avatar5code = R.drawable.avatar5;
                        }
                        userHandler(avatar5, tvPlace5, avatar5code, username5, true, hor5);
                    }
                }
            }
            //GetAvatars();
        }
        @Override
        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }
        @Override
        public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            if(username5.equals(snapshot.getKey())){
                username5 = "";
                avatar5code = 0;
                childAdded--;
                userCount--;
                incScoreS(false, 1, "X", "X", true);
                userHandler(avatar5, tvPlace5, avatar5code, username5, false, hor5);
            }
            if(username4.equals(snapshot.getKey())){
                username4 = "";
                avatar4code = 0;
                childAdded--;
                userCount--;
                incScoreS(false, 1, "X", "X", true);
                userHandler(avatar4, tvPlace4, avatar4code, username4, false, hor4);
            }
            if(username3.equals(snapshot.getKey())){
                username3 = "";
                avatar3code = 0;
                childAdded--;
                userCount--;
                incScoreS(false, 1, "X", "X", true);
                userHandler(avatar3, tvPlace3, avatar3code, username3, false, hor3);
            }
            if(username2.equals(snapshot.getKey())){
                username2 = "";
                avatar2code = 0;
                childAdded--;
                userCount--;
                incScoreS(false, 1, "X", "X", true);
                userHandler(avatar2, tvPlace2, avatar2code, username2, false, hor2);
            }
            if(username1.equals(snapshot.getKey())){
                username1 = "";
                avatar1code = 0;
                childAdded--;
                userCount--;
                incScoreS(false, 1, "X", "X", true);
                userHandler(avatar1, tvPlace1, avatar1code, username1, false, hor1);
            }
            if(username0.equals(snapshot.getKey())){
                username0 = "";
                avatar0code = 0;
                childAdded--;
                userCount--;
                incScoreS(false, 1, "X", "X", true);
                userHandler(avatar0, tvPlace0, avatar0code, username0, false, hor0);
            }
            //GetAvatars();
        }
        @Override
        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            rdb.removeEventListener(removeListener);
            rdb.addChildEventListener(removeListener);
        }

    };



    public static void incScoreS(boolean addOrSub, int amount, String username, String entryName, boolean ignoreEntryName){
        if(!soloBool){
            if(lobbyName != null){
                System.out.println("incScoreS : " + addOrSub + amount + username + entryName + ignoreEntryName);
                DatabaseReference rootRef = FirebaseDatabase.getInstance(firebaseServer).getReference(lobbyName).child(username);
                DatabaseReference scoreRef = rootRef.child(entryName);
                if(ignoreEntryName){
                    rootRef.runTransaction(new Transaction.Handler() {
                        @Override
                        public Transaction.@NotNull Result doTransaction(@NotNull MutableData mutableData) {
                            Integer score = mutableData.getValue(Integer.class);
                            if (score == null) {
                                return Transaction.success(mutableData);
                            }
                            if (addOrSub) {
                                mutableData.setValue(score + amount);
                            } else {
                                mutableData.setValue(score - amount);
                            }
                            return Transaction.success(mutableData);
                        }

                        @Override
                        public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                            if(!b){
                                incScoreS(addOrSub,amount,username,entryName,ignoreEntryName);
                            }
                        }

                    });
                }else{
                    scoreRef.runTransaction(new Transaction.Handler() {
                        @Override
                        public Transaction.@NotNull Result doTransaction(@NotNull MutableData mutableData) {
                            Integer score = mutableData.getValue(Integer.class);
                            if (score == null) {
                                return Transaction.success(mutableData);
                            }
                            if (addOrSub) {
                                mutableData.setValue(score + amount);
                            } else {
                                mutableData.setValue(score - amount);
                            }
                            return Transaction.success(mutableData);
                        }

                        @Override
                        public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                            if(!b){
                                incScoreS(addOrSub,amount,username,entryName,ignoreEntryName);
                            }

                        }

                    });
                }
            }
        }
    }


    private void iniScores(){
        if(!soloBool){
            Map<String, Object> scores= new HashMap<>();

            int pp = 0;
            if(_appPrefs.getString("Avatar" ) != null && !_appPrefs.getString("Avatar").equals("")){
                pp = Integer.parseInt(_appPrefs.getString("Avatar"));
            }
            scores.put("score", 0);
            scores.put("scoref", 0);
            scores.put("pp", pp);
            scores.put("Mess", "");

            rdb.child(staticUsername).updateChildren(scores).addOnSuccessListener(aVoid -> {
                System.out.println("Successfully initialized scores");
            }).addOnFailureListener(e -> {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.failed_connect, Snackbar.LENGTH_LONG);
                snackbar.setTextColor(getResources().getColor(R.color.red));
                snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                snackbar.show();
                iniScores();
            });
            rdb.onDisconnect().removeValue();
            cdb.onDisconnect().removeValue();
        }
    }

    private void iniRest(){
        Map<String, Object> mapp= new HashMap<>();
        mapp.put("X", 0);
        mapp.put("R", 0);
        mapp.put("C", " ");
        mapp.put("K", "0");
        mapp.put("T", ServerValue.TIMESTAMP);
        mapp.put("D", difficulty);
        rdb.updateChildren(mapp).addOnSuccessListener(aVoid ->{
            //rdb.addValueEventListener(getUsernames);
            //rdb.child("X").addValueEventListener(listenX);
            rdb.addChildEventListener(removeListener);
            System.out.println("Successfully initialized xrdc");
        }).addOnFailureListener(e -> {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.failed_connect, Snackbar.LENGTH_LONG);
            snackbar.setTextColor(getResources().getColor(R.color.red));
            snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
            snackbar.show();
            iniRest();
        });
    }

    private void removeJunk(){
        if(!soloBool){
            rdb.child("R").removeEventListener(checkReady);
        }
        //rdb.removeEventListener(removeListener);
        //rdb.child("X").removeEventListener(listenX);

    }
    private String CreateText(String part1, String part2){
        switch (difficulty) {
            case "Ez":
                return part1 + " " + part2 + " \n" + getString(R.string.diff_) + " " + getString(R.string.easy_mode);
            case "Med":
                return part1 + " " + part2 + " \n" + getString(R.string.diff_) + " " + getString(R.string.medium_mode);
            case "Hard":
                return part1 + " " + part2 + " \n" + getString(R.string.diff_) + " " + getString(R.string.hardcore_mode);
            case "Rush":
                return part1 + " " + part2 + " \n" + getString(R.string.diff_) + " " + getString(R.string.rush_mode);
            case "Custom":
                return part1 + " " + part2 + " \n" + getString(R.string.diff_) + " " + getString(R.string.custom_mode);
            default:
                return part1 + " " + part2;
        }
    }

    private void CycleText(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                //System.out.println("Running Cycle, cycle == " + textCycleTimes);
                if(textCycleTimes == 0 ) {
                    String roomString;
                    if(!soloBool){
                        if(!lobbyName.startsWith("english_") && !lobbyName.startsWith("afrikaans_")){
                            roomString = CreateText(getString(R.string.room_), lobbyName);
                        }else{
                            if(gameLang.equals("en")){
                                roomString = CreateText(getString(R.string.room_pub_en), "");
                            }else if(gameLang.equals("afr")){
                                roomString = CreateText(getString(R.string.room_pub_afr), "");
                            }else{
                                roomString = CreateText(getString(R.string.room_pub), "");
                            }

                        }
                        info.startAnimation(exitAnim);
                        info.setText(roomString);
                        info.startAnimation(enterAnim);
                        cycleDelay = 5000;
                    }

                }
                else if(textCycleTimes == 1 && userCount < 2 && !soloBool){
                    String roomString = getString(R.string.waiting_for_more_players);
                    cycleDelay = 2000;
                    info.startAnimation(exitAnim);
                    info.setText(roomString);
                    info.startAnimation(enterAnim);
                }else if(readyBtn.getVisibility() == View.INVISIBLE && textCycleTimes == 2 && userCount > 1 && !soloBool){
                    String roomString = getString(R.string.waiting_for_everyone_to_ready);
                    cycleDelay = 3000;
                    info.startAnimation(exitAnim);
                    info.setText(roomString);
                    info.startAnimation(enterAnim);
                }else if(readyBtn.getVisibility() == View.VISIBLE && textCycleTimes == 3){
                    String roomString = getString(R.string.press_ready_when_ready);
                    cycleDelay = 4000;
                    info.startAnimation(exitAnim);
                    info.setText(roomString);
                    info.startAnimation(enterAnim);
                }else if(textCycleTimes >3){
                    textCycleTimes = -1;
                    cycleDelay = 100;
                }
                if (staticAct.equalsIgnoreCase("SA1")){
                    handler.postDelayed(this, cycleDelay);
                }
                textCycleTimes ++;
            }
        });
    }




    private int SetAvatarCode(int code){
        if(code == 0){
            return R.drawable.avatar0;
        }else if(code == 1){
            return R.drawable.avatar0;
        }else if(code == 2){
            return R.drawable.avatar1;
        }else if(code == 3){
            return R.drawable.avatar2;
        }else if(code == 4){
            return R.drawable.avatar3;
        }else if(code == 5){
            return R.drawable.avatar4;
        }else if(code == 6){
            return R.drawable.avatar5;
        }else if(code == 7){
            return R.drawable.avatar7;
        }else if(code == 8){
            return R.drawable.avatar8;
        }else if(code == 9){
            return R.drawable.avatar9;
        }else if(code == 10){
            return R.drawable.avatar10;
        }else if(code == 11){
            return R.drawable.avatar11;
        }else if(code == 12){
            return R.drawable.avatar12;
        }else if(code == 13){
            return R.drawable.avatar13;
        }else if(code == 14){
            return R.drawable.avatar14;
        }else if(code == 15){
            return R.drawable.avatar15;
        }else if(code == 16){
            return R.drawable.avatar16;
        }else if(code == 17){
            return R.drawable.avatar17;
        }else if(code == 18){
            return R.drawable.avatar18;
        }else if(code == 19){
            return R.drawable.avatar19;
        }else if(code == 20){
            return R.drawable.avatar20;
        }else if(code == 21){
            return R.drawable.avatar21;
        }else if(code == 22){
            return R.drawable.avatar22;
        }else if(code == 23){
            return R.drawable.avatar23;
        }else if(code == 24){
            return R.drawable.avatar24;
        }else if(code == 25){
            return R.drawable.avatar25;
        }else{
            return 0;
        }
    }

    void MessageAlert(ImageView iv){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(staticAct.equalsIgnoreCase("SA1")) {
                    if (newMessage) {
                        iv.setImageResource(R.drawable.new_message);
                    }
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }

    void SetGameState(String value){
        rdb.child("A").setValue(value).addOnSuccessListener(aVoid -> System.out.println("Success writing Gamestate"))
                .addOnFailureListener(e -> {
                    handler.postDelayed(() -> SetGameState(value), 2000);
                });
    }


    void CheckUsernames(){
        if(staticAct.equals("SA1")){
            if (username0.length() < 2 ) {
                if( tvPlace0.getText().toString().length() > 1){
                    tvPlace0.setText("");
                    hor0.setVisibility(View.GONE);
                }
            }else {
                hor0.setVisibility(View.VISIBLE);
            }
            if (username1.length() < 2 ){if( tvPlace1.getText().toString().length() > 1){
                tvPlace1.setText("");
                hor1.setVisibility(View.GONE);
            }
            }else {
                hor1.setVisibility(View.VISIBLE);
            }
            if (username2.length() < 2 ){if( tvPlace2.getText().toString().length() > 1){
                tvPlace2.setText("");
                hor2.setVisibility(View.GONE);
            }
            }else {
                hor2.setVisibility(View.VISIBLE);
            }
            if (username3.length() < 2 ){if( tvPlace3.getText().toString().length() > 1){
                tvPlace3.setText("");
                hor3.setVisibility(View.GONE);}
            }else {
                hor3.setVisibility(View.VISIBLE);
            }
            if (username4.length() < 2 ){if( tvPlace4.getText().toString().length() > 1){
                tvPlace4.setText("");
                hor4.setVisibility(View.GONE);
            }
            }else {
                hor4.setVisibility(View.VISIBLE);
            }
            if (username5.length() < 2 ){if(tvPlace5.getText().toString().length() > 1){
                tvPlace5.setText("");
                hor5.setVisibility(View.GONE);
            }
            }else{
                hor5.setVisibility(View.VISIBLE);
            }
            if(userCount < 2 && !soloBool){
                readyBtn.setVisibility(View.INVISIBLE);
                helper_ready.setVisibility(View.INVISIBLE);
            }else if(!readied && !soloBool) {
                readyBtn.setVisibility(View.VISIBLE);
                helper_ready.setVisibility(View.VISIBLE);
            }
        }
        if(staticAct.equals("SA1")) {
            handler.postDelayed(this::CheckUsernames, 150);
        }
    }

    private void userHandler(ImageView avatarIV, TextView userTV, int AvatarCode, String UN, boolean Adding, LinearLayout LL) {
        if(staticAct.equalsIgnoreCase("SA1")) {
            if(Adding){
                LL.setVisibility(View.VISIBLE);
                avatarIV.setImageResource(AvatarCode);
            }else{
                LL.setVisibility(View.GONE);
            }
            userTV.setText(UN);
        }

    }
    int naam_rand = 0;
    int letter_rand = 1;
    NamesDBHelper namesDB = new NamesDBHelper(ServerActivity1.this);

    void GiveBotName(){
        Random rand = new Random();
        letter_rand = rand.nextInt(26) + 1;
        if(!namesDB.GetRand(letter_rand, letter_rand).equals(staticUsername)){
            username1 = namesDB.GetRand(letter_rand, letter_rand);
            handler.post(() -> {
                avatar1code = SetAvatarCode(rand.nextInt(25));
                avatar0code = myAvatar;
                userHandler(avatar0, tvPlace0, avatar0code, staticUsername, true, hor0);
                userHandler(avatar1, tvPlace1, avatar1code, username1, true, hor1);
            });
        }else{
            GiveBotName();
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
        bg_0 = findViewById(R.id.bg_0_sa1);
        bg_1 = findViewById(R.id.bg_2_sa1);
        bg_2 = findViewById(R.id.bg_3_sa1);
        bg_3 = findViewById(R.id.bg_4_sa1);
        bg_4 = findViewById(R.id.bg_6_sa1);
        bg_5 = findViewById(R.id.bg_7_sa1);
        bg_6 = findViewById(R.id.bg_8_sa1);
        con = this;
        BackgroundClass.SetBackground(con, findViewById(R.id.UserListS));
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
                    if(staticAct.equals("SA1")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_0.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_0);
                        bghand0.postDelayed(bgrun0, bgdur);
                    }
                };
                bgrun1 = () -> {
                    if(staticAct.equals("SA1")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_1.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_1);
                        bghand1.postDelayed(bgrun1, bgdur);
                    }
                };
                bgrun2 = () -> {
                    if(staticAct.equals("SA1")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_2.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_2);
                        bghand2.postDelayed(bgrun2, bgdur);
                    }
                };
                bgrun3 = () -> {
                    if(staticAct.equals("SA1")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_3.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_3);
                        bghand3.postDelayed(bgrun3, bgdur);
                    }
                };
                bgrun4 = () -> {
                    if(staticAct.equals("SA1")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_4.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_4);
                        bghand4.postDelayed(bgrun4, bgdur);
                    }
                };
                bgrun5 = () -> {
                    if(staticAct.equals("SA1")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_5.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_5);
                        bghand5.postDelayed(bgrun5, bgdur);
                    }
                };
                bgrun6 = () -> {
                    if(staticAct.equals("SA1")){
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