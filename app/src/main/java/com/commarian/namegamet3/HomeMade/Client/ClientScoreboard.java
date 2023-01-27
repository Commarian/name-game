package com.commarian.namegamet3.HomeMade.Client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.commarian.namegamet3.HomeMade.Classes.AppPreferences;
import com.commarian.namegamet3.HomeMade.Classes.BackgroundClass;
import com.commarian.namegamet3.HomeMade.Classes.LocaleHelper;
import com.commarian.namegamet3.HomeMade.Extras.MessageFrag;
import com.commarian.namegamet3.R;
import com.google.android.gms.ads.AdView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentContainerView;

import static com.commarian.namegamet3.HomeMade.Client.ClientActivity1.incScoreC;
import static com.commarian.namegamet3.HomeMade.Classes.AnimHandler.animHandler;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.Points;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.adRequest;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.animatedBackgrounds;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar0code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar1code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar2code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar3code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar4code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.avatar5code;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.firebaseServer;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.gameType;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.game_over;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.ir;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.lobbyName;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.newMessage;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticAct;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticUserID;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticUsername;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.userCount;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username0;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username1;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username2;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username3;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username4;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username5;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.fragMute;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.playSound;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.random_int_gen;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.random_long_gen;

public class ClientScoreboard extends AppCompatActivity {

    DatabaseReference rdb = FirebaseDatabase.getInstance(firebaseServer).getReference(lobbyName);
    Boolean fixable = true;
    Animation marioAni;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    AppPreferences _appPrefs;

    String scoreboard = "";

    TextView info;
    TextView user0;
    TextView user1;
    TextView user2;
    TextView user3;
    TextView user4;
    TextView user5;
    TextView score0;
    TextView score1;
    TextView score2;
    TextView score3;
    TextView score4;
    TextView score5;
    androidx.appcompat.widget.AppCompatImageView avatar0;
    androidx.appcompat.widget.AppCompatImageView avatar1;
    androidx.appcompat.widget.AppCompatImageView avatar2;
    androidx.appcompat.widget.AppCompatImageView avatar3;
    androidx.appcompat.widget.AppCompatImageView avatar4;
    androidx.appcompat.widget.AppCompatImageView avatar5;
    androidx.appcompat.widget.AppCompatImageView helperReady;
    androidx.appcompat.widget.AppCompatImageView leaveBtn;
    LinearLayout hor0;
    LinearLayout hor1;
    LinearLayout hor2;
    LinearLayout hor3;
    LinearLayout hor4;
    LinearLayout hor5;
    TextView tvReady;


    

    int updateTwice = 0;

    boolean readied = false;

    FragmentContainerView fragContainer;
    boolean fragment = true;
    androidx.appcompat.widget.AppCompatImageView msgBtn;
    Handler handler = new Handler();

    Boolean done = true;
    Button readyBtn;

    AlertDialog.Builder builder ;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_scoreboard);


        staticAct = "CSB";
        Backgrounds();
        AdView adView = (AdView) findViewById(R.id.adViewCB);
        leaveBtn = findViewById(R.id.leave_game_client_scoreboard);

        adView.loadAd(adRequest);

        FixAvatarCodesC();


        _appPrefs = new AppPreferences(getApplicationContext());

        String appSound = _appPrefs.getString("sound");


        done = true;
        marioAni = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.mario);

        fragment = true;
        fragContainer = findViewById(R.id.fragment_container_client_scoreboard);
        msgBtn = findViewById(R.id.messageButtonClientScoreBoard);

        readied = false;
        updateTwice = 0;
        info = findViewById(R.id.scoreboardInfoC);

        avatar0 = findViewById(R.id.avatarCB1);
        avatar1 = findViewById(R.id.avatarCB2);
        avatar2 = findViewById(R.id.avatarCB3);
        avatar3 = findViewById(R.id.avatarCB4);
        avatar4 = findViewById(R.id.avatarCB5);
        avatar5 = findViewById(R.id.avatarCB6);
        hor0 = findViewById(R.id.chor1);
        hor1 = findViewById(R.id.chor2);
        hor2 = findViewById(R.id.chor3);
        hor3 = findViewById(R.id.chor4);
        hor4 = findViewById(R.id.chor5);
        hor5 = findViewById(R.id.chor6);

        helperReady = findViewById(R.id.helper_ready_scoreboard_client);

        tvReady = findViewById(R.id.tvReadyCS);


        user0 = findViewById(R.id.userSC1);
        user1 = findViewById(R.id.userSC2);
        user2 = findViewById(R.id.userSC3);
        user3 = findViewById(R.id.userSC4);
        user4 = findViewById(R.id.userSC5);
        user5 = findViewById(R.id.userSC6);
        score0 = findViewById(R.id.scoreSC1);
        score1 = findViewById(R.id.scoreSC2);
        score2 = findViewById(R.id.scoreSC3);
        score3 = findViewById(R.id.scoreSC4);
        score4 = findViewById(R.id.scoreSC5);
        score5 = findViewById(R.id.scoreSC6);
        fixable = true;
        readyBtn = findViewById(R.id.btnReadyC);

        msgBtn.setImageResource(R.drawable.old_message);
        CheckUsernames();
        readyBtn.setEnabled(false);
        readyBtn.setVisibility(View.INVISIBLE);
        helperReady.setVisibility(View.INVISIBLE);

        rdb.child("R").addValueEventListener(checkReady);

        /*if(gameType.equals("Public") && userCount > 2){
            if(!user0.getText().toString().equals(staticUsername) && tv1){
                new Thread(() -> tv1 = KickFun(user0)).start();
            }
            if(!user1.getText().toString().isEmpty() && !user1.getText().toString().equals(staticUsername)&& tv2){
                new Thread(() -> tv2 = KickFun(user1)).start();
            }
            if(!user2.getText().toString().isEmpty()&& !user2.getText().toString().equals(staticUsername)&& tv3){
                new Thread(() -> tv3 = KickFun(user2)).start();
            }
            if(!user3.getText().toString().isEmpty()&& !user3.getText().toString().equals(staticUsername)&& tv4){
                new Thread(() -> tv4 = KickFun(user3)).start();
            }
            if(!user4.getText().toString().isEmpty()&& !user4.getText().toString().equals(staticUsername)&& tv5){
                new Thread(() -> tv5 = KickFun(user4)).start();
            }
            if(!user5.getText().toString().isEmpty()&& !user5.getText().toString().equals(staticUsername)&& tv6){
                new Thread(() -> tv6 = KickFun(user5)).start();
            }
            //rdb.child("K").addChildEventListener(kickListener);
        }*/


        GameAliveWatchDog();
        builder = new AlertDialog.Builder(this, R.style.AlertDialogThemeCustom);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if(fragContainer.getVisibility() == View.VISIBLE){
                    fragContainer.setVisibility(View.GONE);
                    if(appSound.equals("ON")){
                        fragMute = false;
                    }
                }else{
                playSound("click");
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
                                rdb.child("R").removeEventListener(checkReady);
                                //rdb.child("K").removeEventListener(kickListener);
                                RemoveHeavyListeners();
                                finish();
                            });
                        });
                builder.setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss());

                AlertDialog dialog = builder.create();
                dialog.show();
            }}
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        leaveBtn.setOnClickListener(v -> {
            if(fragContainer.getVisibility() == View.VISIBLE){
                fragContainer.setVisibility(View.GONE);
                if(appSound.equals("ON")){
                    fragMute = false;
                }
            }else{
                playSound("click");
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
                                rdb.child("R").removeEventListener(checkReady);
                                if(gameType.equals("Public")){
                                    //rdb.child("K").removeEventListener(kickListener);
                                }
                                RemoveHeavyListeners();
                                finish();
                            });
                        });
                builder.setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss());

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        if(game_over.equals("GAME_OVER")){
            readyBtn.setText(getString(R.string.game_over_btn));
        }
        readyBtn.setOnClickListener(v -> {
            playSound("click");
            if(game_over.equals("GAME_OVER")){
                rdb.child("R").removeEventListener(checkReady);
                if(gameType.equals("Public")){
                    //rdb.child("K").removeEventListener(kickListener);
                }
                rdb.removeValue();
                RemoveHeavyListeners();
                finish();
            }else {
                readyBtn.setVisibility(View.INVISIBLE);
                helperReady.setVisibility(View.INVISIBLE);
                if(!readied){
                    readied = true;
                    incScoreC(true, 1, "R", "R", true);
                }
            }
        });

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
                            .add(R.id.fragment_container_client_scoreboard, MessageFrag.class, null)
                            .commit();
                }
            }
            int isVis = fragContainer.getVisibility();
            if(isVis != 8){
                fragContainer.setVisibility(View.GONE);
                if(appSound.equals("ON")){
                    fragMute = false;
                }
            }else{
                fragContainer.setVisibility(View.VISIBLE);
                fragMute = true;
            }

        });
        MessageAlert(msgBtn);
        if(fragment){
            if (savedInstanceState == null) {
                fragment = false;
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .add(R.id.fragment_container_client_scoreboard, MessageFrag.class, null)
                        .commit();
            }
        }


        if(username0.length() > 1){
            handler.post(() -> rdb.child(username0).child("scoref").addValueEventListener(val0));
        }
        if(username1.length() > 1){
            handler.post(() -> rdb.child(username1).child("scoref").addValueEventListener(val1));
        }
        if(username2.length() > 1){
            handler.post(() -> rdb.child(username2).child("scoref").addValueEventListener(val2));
        }
        if(username3.length() > 1){
            handler.post(() -> rdb.child(username3).child("scoref").addValueEventListener(val3));
        }
        if(username4.length() > 1){
            handler.post(() -> rdb.child(username4).child("scoref").addValueEventListener(val4));
        }
        if(username5.length() > 1){
            handler.post(() -> rdb.child(username5).child("scoref").addValueEventListener(val5));
        }


    }

    /*ChildEventListener kickListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            //System.out.println("onChildAdded = " + snapshot);
            if (snapshot.getValue() != null) {
                String snapper = snapshot.getKey();
                //System.out.println("SNAPPER = " + snapper);
                if (snapper.length() > 1) {
                    String kicker = getString(R.string.snack_kick_single) + " "+ snapper;
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), kicker, Snackbar.LENGTH_LONG);
                    snackbar.setTextColor(getResources().getColor(R.color.yellow_text));
                    snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                    snackbar.show();
                }
            }
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            //System.out.println("onChildChanged = " + snapshot);
            if (snapshot.getValue() != null) {
                String snapper = snapshot.getKey();
                //System.out.println("SNAPPER = " + snapper);
                if (snapper.length() > 1) {
                    long no = (long) snapshot.getValue();
                    String kicker = no + " "+ getString(R.string.snack_kick) + " " + snapper;
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), kicker, Snackbar.LENGTH_LONG);
                    snackbar.setTextColor(getResources().getColor(R.color.yellow_text));
                    snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                    snackbar.show();
                    if(no == (userCount -1)){
                        if(snapper.equals(staticUsername)){
                            rdb.child("R").removeEventListener(checkReady);
                            rdb.child("K").removeEventListener(kickListener);
                            RemoveHeavyListeners();
                            kickedNuts = true;
                            finish();
                        }else{
                            userCount--;
                            readyBtn.setVisibility(View.VISIBLE);
                            helperReady.setVisibility(View.VISIBLE);
                            readyBtn.setEnabled(true);
                            if(user0.getText().toString().equals(snapper)){
                                user0.setText("");
                                avatar0.setVisibility(View.INVISIBLE);
                                avatar0code = 0;
                                username0 = "";
                                score0.setText("");
                            }else if(user1.getText().toString().equals(snapper)){
                                user1.setText("");
                                avatar1.setVisibility(View.INVISIBLE);
                                avatar1code = 0;
                                username1 = "";
                                score1.setText("");
                            }else if(user2.getText().toString().equals(snapper)){
                                user2.setText("");
                                avatar2.setVisibility(View.INVISIBLE);
                                avatar2code = 0;
                                username2 = "";
                                score2.setText("");
                            }else if(user3.getText().toString().equals(snapper)){
                                user3.setText("");
                                avatar3.setVisibility(View.INVISIBLE);
                                avatar3code = 0;
                                username3 = "";
                                score3.setText("");
                            }else if(user4.getText().toString().equals(snapper)){
                                user4.setText("");
                                avatar4.setVisibility(View.INVISIBLE);
                                avatar4code = 0;
                                username4 = "";
                                score4.setText("");
                            }else if(user5.getText().toString().equals(snapper)){
                                user5.setText("");
                                avatar5.setVisibility(View.INVISIBLE);
                                avatar5code = 0;
                                username5 = "";
                                score5.setText("");
                            }

                        }
                    }
                }
            }
        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            System.out.println("onChildRemoved = " + snapshot);
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            System.out.println("onChildMoved = " + snapshot);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            System.out.println("onCancelled = " + error);
            rdb.child("K").removeEventListener(kickListener);
            rdb.child("K").addChildEventListener(kickListener);
        }
    };*/

    ValueEventListener checkReady = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            //System.out.println("DEBUGGING INSIDE Checkready CSB VEL");
            if (snapshot.getValue() != null){

                if (snapshot.getValue() != null){
                    long r = (long)snapshot.getValue();
                    //System.out.println("VALUE OF R IS " + r);
                    if(r >= userCount){
                        if(fixable){
                            handler.postDelayed(() -> fixScores(), 1000);
                            fixable = false;
                            readyBtn.setEnabled(true);
                            readyBtn.setVisibility(View.VISIBLE);
                            helperReady.setVisibility(View.VISIBLE);
                            CheckPoints();
                        }
                    }
                    if(r > userCount && r <= userCount*2){
                        String str = (r - userCount) + getString(R.string.of_special) + userCount + getString(R.string.players_ready_special);
                        tvReady.setText(str);

                    }
                    if(r >= (2 * userCount)){
                        if(getSupportFragmentManager().findFragmentById(R.id.fragment_container_client_scoreboard) != null){
                            fragContainer.setVisibility(View.GONE);
                            getSupportFragmentManager().beginTransaction().remove(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.fragment_container_client_scoreboard)));
                        }
                        Intent o = new Intent(ClientScoreboard.this, ClientGameActivity.class);
                        done = false;
                        rdb.child("R").removeEventListener(checkReady);
                        if(gameType.equals("Public") && userCount > 2){
                            //rdb.child("K").removeEventListener(kickListener);
                        }
                        RemoveHeavyListeners();
                        finish();
                        overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                        startActivity(o);
                        overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                    }
                }
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            rdb.child("R").removeEventListener(checkReady);
            rdb.child("R").addValueEventListener(checkReady);
        }
    };

    private void fixScores(){
        rdb.child(staticUsername).child("score").get().addOnCompleteListener(task -> {
            if(task.getResult() !=null){
                if(task.getResult().getValue() != null){
                    double localUC = (userCount - 1);
                    long score = (long) task.getResult().getValue();
                    //System.out.println("Score = " +score);
                    long rem = score%userCount;
                    //System.out.println("REM = " + rem);
                    double dScore = score/localUC;
                    double test = rem/localUC;
                    //System.out.println("TEST = " + test);
                    if(test >= 0.66){
                        dScore = Math.ceil(dScore);
                    }else
                    {
                        dScore = Math.floor(dScore);
                    }
                    //System.out.println("Dscore: " + dScore);
                    incScoreC(true, (int) dScore, staticUsername, "scoref", false);
                    rdb.child(staticUsername).child("score").setValue(0).
                            addOnSuccessListener(aVoid -> System.out.println("Successfully set score to zero")).
                            addOnFailureListener(e -> {
                        //System.out.println("Failed to set score to zero");
                        rdb.child(staticUsername).child("score").setValue(0);
                    });
                }
            }
        });
    }

    long localPoints = 0;
    private void CheckPoints(){
        if(game_over.equals("GAME_OVER")){
            localPoints = 0;
             readyBtn.setText(getString(R.string.game_over_btn));
            rdb.child(staticUsername).child("scoref").get().addOnSuccessListener(dataSnapshot -> {
                if(dataSnapshot.getValue() != null){
                    String data = dataSnapshot.getValue().toString();
                    int dataInt = Integer.parseInt(data);
                    localPoints = Points + dataInt;
                    //System.out.println("LOCALPoINTS = " + localPoints);
                    Map<String, Object> map= new HashMap<>();
                    map.put("Points", localPoints);
                    db.collection(staticUserID).document("Points").set(map).addOnSuccessListener(aVoid -> {
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), getString(R.string.game_finished) + " (" + dataInt + ")", Snackbar.LENGTH_LONG);
                    snackbar.setTextColor(getResources().getColor(R.color.green_text));
                    snackbar.setBackgroundTint(getResources().getColor(R.color.green_text));
                    snackbar.show();
                    }).addOnFailureListener(e -> CheckPoints());
                }

            }).addOnFailureListener(e -> CheckPoints());

        }
    }
    boolean dicky = true;
    /*private boolean KickFun(TextView tv ){
        tv.setOnClickListener(v -> {
            playSound("click");
            builder = new AlertDialog.Builder(ClientScoreboard.this, R.style.AlertDialogThemeCustom);
            builder.setCancelable(true);
            builder.setTitle(R.string.kick_title);
            String kicky = getString(R.string.kick_player) + " " +tv.getText() + "?";
            builder.setMessage(kicky);
            builder.setPositiveButton(R.string.yes,
                    (dialog, which) -> {
                        rdb.child("K").child(tv.getText().toString()).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                            @Override
                            public void onSuccess(DataSnapshot dataSnapshot) {
                                if(dataSnapshot.getValue() != null){
                                    long kickAmount = (long) dataSnapshot.getValue();
                                    rdb.child("K").child(tv.getText().toString()).setValue(kickAmount+1);
                                    tv.setOnClickListener(v1 -> { });

                                    dicky = false;
                                }else{
                                    rdb.child("K").child(tv.getText().toString()).setValue(1);
                                    tv.setOnClickListener(v1 -> {
                                    });

                                    dicky = false;
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

                    });
            builder.setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();

        });

        return dicky;

    }*/

    void MessageAlert(ImageView iv){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(done){
                    if(newMessage){
                        iv.setImageResource(R.drawable.new_message);
                    }
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }

    void RemoveHeavyListeners(){
        handler.post(() -> {
            if(username0.length() > 1){
                handler.post(() -> rdb.child(username0).child("scoref").removeEventListener(val0));
            }
            if(username1.length() > 1){
                handler.post(() -> rdb.child(username1).child("scoref").removeEventListener(val1));
            }
            if(username2.length() > 1){
                handler.post(() -> rdb.child(username2).child("scoref").removeEventListener(val2));
            }
            if(username3.length() > 1){
                handler.post(() -> rdb.child(username3).child("scoref").removeEventListener(val3));
            }
            if(username4.length() > 1){
                handler.post(() -> rdb.child(username4).child("scoref").removeEventListener(val4));
            }
            if(username5.length() > 1){
                handler.post(() -> rdb.child(username5).child("scoref").removeEventListener(val5));
            }
        });

    }
    ValueEventListener val0 = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            System.out.println("onDataChange(@NonNull DataSnapshot snapshot)" + snapshot);
            if (snapshot.getValue() != null) {
                scoreboard = snapshot.getValue().toString();
            } else {
                scoreboard = "0";
            }
            if (!score0.getText().toString().equals(scoreboard)) {
                runOnUiThread(() -> {
                    score0.setText(scoreboard);
                    score0.startAnimation(marioAni);
                });
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };
    ValueEventListener val1 = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.getValue() != null) {
                scoreboard = snapshot.getValue().toString();
            } else {
                scoreboard = "0";
            }
            if (!score1.getText().toString().equals(scoreboard)) {
                runOnUiThread(() -> {
                    score1.setText(scoreboard);
                    score1.startAnimation(marioAni);
                });
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };
    ValueEventListener val2 = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.getValue() != null) {
                scoreboard = snapshot.getValue().toString();
            } else {
                scoreboard = "0";
            }
            if (!score2.getText().toString().equals(scoreboard)) {
                runOnUiThread(() -> {
                    score2.setText(scoreboard);
                    score2.startAnimation(marioAni);
                });
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };
    ValueEventListener val3 = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.getValue() != null) {
                scoreboard = snapshot.getValue().toString();
            } else {
                scoreboard = "0";
            }
            if (!score3.getText().toString().equals(scoreboard)) {
                runOnUiThread(() -> {
                    score3.setText(scoreboard);
                    score3.startAnimation(marioAni);
                });
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };
    ValueEventListener val4 = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.getValue() != null) {
                scoreboard = snapshot.getValue().toString();
            } else {
                scoreboard = "0";
            }
            if (!score4.getText().toString().equals(scoreboard)) {
                runOnUiThread(() -> {
                    score4.setText(scoreboard);
                    score4.startAnimation(marioAni);
                });
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };
    ValueEventListener val5 = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.getValue() != null) {
                scoreboard = snapshot.getValue().toString();
            } else {
                scoreboard = "0";
            }
            if (!score5.getText().toString().equals(scoreboard)) {
                runOnUiThread(() -> {
                    score5.setText(scoreboard);
                    score5.startAnimation(marioAni);
                });
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };


    void CheckUsernames(){
        if(staticAct.equals("CSB")) {
            handler.postDelayed(() -> {
                CheckUsernames();
                if (username0.length() < 2 ){if( user0.getText().toString().length() > 1){
                    user0.setText("");
                    hor0.setVisibility(View.GONE);
                    score0.setText("");}
                }else {
                    hor0.setVisibility(View.VISIBLE);
                    user0.setText(username0);
                    avatar0.setImageResource(avatar0code);
                }
                if (username1.length() < 2 ){if( user1.getText().toString().length() > 1){
                    user1.setText("");
                    hor1.setVisibility(View.GONE);
                    score1.setText("");}
                }else {
                    hor1.setVisibility(View.VISIBLE);
                    user1.setText(username1);
                    avatar1.setImageResource(avatar1code);
                }
                if (username2.length() < 2 ){if( user2.getText().toString().length() > 1){
                    user2.setText("");
                    hor2.setVisibility(View.GONE);
                    score2.setText("");}
                }else {
                    hor2.setVisibility(View.VISIBLE);
                    user2.setText(username2);
                    avatar2.setImageResource(avatar2code);
                }
                if (username3.length() < 2 ){if( user3.getText().toString().length() > 1){
                    user3.setText("");
                    score3.setText("");
                    hor3.setVisibility(View.GONE);}
                }else {
                    hor3.setVisibility(View.VISIBLE);
                    user3.setText(username3);
                    avatar3.setImageResource(avatar3code);
                }
                if (username4.length() < 2 ){if( user4.getText().toString().length() > 1){
                    user4.setText("");
                    hor4.setVisibility(View.GONE);
                    score4.setText("");}
                }else {
                    hor4.setVisibility(View.VISIBLE);
                    user4.setText(username4);
                    avatar4.setImageResource(avatar4code);
                }
                if (username5.length() < 2 ){if(user5.getText().toString().length() > 1){
                    user5.setText("");
                    hor5.setVisibility(View.GONE);
                    score5.setText("");}
                }else{
                    hor5.setVisibility(View.VISIBLE);
                    user5.setText(username5);
                    avatar5.setImageResource(avatar5code);
                }

            }, 1000);
        }
    }


    private void FixAvatarCodesC(){
        if (username0 != null && username0.length() > 2){
            if(avatar0code == 0){
                avatar0code = R.drawable.avatar0;
            }
        }
        if (username1 != null && username1.length() > 2){
            if(avatar1code == 0){
                avatar1code = R.drawable.avatar0;
            }
        }
        if (username2 != null && username2.length() > 2){
            if(avatar2code == 0){
                avatar2code = R.drawable.avatar0;
            }
        }
        if (username3 != null && username3.length() > 2){
            if(avatar3code == 0){
                avatar3code = R.drawable.avatar0;
            }
        }
        if (username4 != null && username4.length() > 2){
            if(avatar4code == 0){
                avatar4code = R.drawable.avatar0;
            }
        }
        if (username5 != null && username5.length() > 2){
            if(avatar5code == 0){
                avatar5code = R.drawable.avatar0;
            }
        }
    }
    void GameAliveWatchDog(){
        handler.postDelayed(() -> {
            if(staticAct.equalsIgnoreCase("CSB")) {
                if (userCount < 2) {
                    Toast.makeText(this, R.string.the_host_closed, Toast.LENGTH_LONG).show();
                    handler.postDelayed(this::finish, 1000);
                } else {
                    GameAliveWatchDog();
                }
            }
        },5000);
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
        bg_0 = findViewById(R.id.bg_0_csb);
        bg_1 = findViewById(R.id.bg_2_csb);
        bg_2 = findViewById(R.id.bg_3_csb);
        bg_3 = findViewById(R.id.bg_4_csb);
        bg_4 = findViewById(R.id.bg_6_csb);
        bg_5 = findViewById(R.id.bg_7_csb);
        bg_6 = findViewById(R.id.bg_8_csb);
        con = this;
        BackgroundClass.SetBackground(con, findViewById(R.id.csb_mv));
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
                    if(staticAct.equals("CSB")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_0.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_0);
                        bghand0.postDelayed(bgrun0, bgdur);
                    }
                };
                bgrun1 = () -> {
                    if(staticAct.equals("CSB")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_1.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_1);
                        bghand1.postDelayed(bgrun1, bgdur);
                    }
                };
                bgrun2 = () -> {
                    if(staticAct.equals("CSB")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_2.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_2);
                        bghand2.postDelayed(bgrun2, bgdur);
                    }
                };
                bgrun3 = () -> {
                    if(staticAct.equals("CSB")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_3.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_3);
                        bghand3.postDelayed(bgrun3, bgdur);
                    }
                };
                bgrun4 = () -> {
                    if(staticAct.equals("CSB")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_4.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_4);
                        bghand4.postDelayed(bgrun4, bgdur);
                    }
                };
                bgrun5 = () -> {
                    if(staticAct.equals("CSB")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_5.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_5);
                        bghand5.postDelayed(bgrun5, bgdur);
                    }
                };
                bgrun6 = () -> {
                    if(staticAct.equals("CSB")){
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