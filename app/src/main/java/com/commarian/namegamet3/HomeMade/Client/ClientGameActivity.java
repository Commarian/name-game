package com.commarian.namegamet3.HomeMade.Client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.commarian.namegamet3.HomeMade.Classes.BackgroundClass;
import com.commarian.namegamet3.HomeMade.Classes.LocaleHelper;
import com.commarian.namegamet3.HomeMade.Classes.WordUploader;
import com.commarian.namegamet3.R;
import com.google.android.gms.ads.AdView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import static com.commarian.namegamet3.HomeMade.Classes.AnimHandler.animHandler;
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
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.game_over;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.ir;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.lobbyName;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.previousLetter;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.singleStaticDier;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.singleStaticDorp;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.singleStaticNaam;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.singleStaticVan;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticAct;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticLetter;
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


public class ClientGameActivity extends AppCompatActivity {
    DatabaseReference rdb = FirebaseDatabase.getInstance(firebaseServer).getReference(lobbyName);
    androidx.appcompat.widget.AppCompatImageView btnStopC;

    //FirebaseFirestore db = FirebaseFirestore.getInstance();
    int failSafe = 0;
    TextView tvLetter;
    AlertDialog.Builder builder ;
    Animation anim;
    Animation spin ;
    EditText etName ;
    EditText etVanne;
    EditText etDiere ;
    EditText etDorpe;
    TextView CD;

    TextView tvOne;
    TextView tvTwo;
    TextView tvThree;
    TextView tvFour;
    TextView tvLang;




    Boolean intentsafe = true;
    Animation vibrateAnim;
    Animation testAnim;

    final Handler handler = new Handler();
    Boolean timo = true;
    int run = 0;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_game);

        game_over = "";
        staticAct = "CGA";
        Backgrounds();

        etName = findViewById(R.id.etNameC);
        etVanne = findViewById(R.id.etVanneC);
        etDiere = findViewById(R.id.etDiereC);
        etDorpe = findViewById(R.id.etDorpeC);
        tvLetter = findViewById(R.id.tvLetterC);
        tvLang = findViewById(R.id.TVLangGameClient);

        tvOne = findViewById(R.id.tvNameC);
        tvTwo = findViewById(R.id.tvVanneC);
        tvThree = findViewById(R.id.tvDiereC);
        tvFour = findViewById(R.id.tvDorpeC);

        btnStopC = findViewById(R.id.stop_btn_client_game);
        intentsafe = true;
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim);
        spin = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.spin);
        vibrateAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.vib);
        testAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.test);

        FixAvatarCodesC();

        AdView adView = (AdView) findViewById(R.id.adViewCGA);
        adView.loadAd(adRequest);

        GameAliveWatchDog();
        btnStopC.setVisibility(View.INVISIBLE);
        tvLetter.setVisibility(View.INVISIBLE);
        btnStopC.setEnabled(false);

        if(gameLang != null && !gameLang.equals("")){
            if(gameLang.equals("en")){
                tvLang.setText(getString(R.string.english));
            }else if(gameLang.equals("afr")) {
                tvLang.setText(getString(R.string.afrikaans));
            }
        }
        CD = findViewById(R.id.CAGCDT);
        CD.setText("");
        timo = true;

        builder = new AlertDialog.Builder(this, R.style.AlertDialogThemeCustom);

        getLetter();
        ListenStopCall();
        failSafe = 0;
        etName.setEnabled(true);
        etVanne.setEnabled(true);
        etDiere.setEnabled(true);
        etDorpe.setEnabled(true);

        if(difficulty.equals("Custom")){
            tvOne.setText(custom1);
            tvTwo.setText(custom2);
            tvThree.setText(custom3);
            tvFour.setText(custom4);

            etName.setHint(custom1);
            etVanne.setHint(custom2);
            etDiere.setHint(custom3);
            etDorpe.setHint(custom4);
        }

        System.out.println("Difficulty C = " + difficulty);
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
                                finish();
                            });
                        });
                builder.setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss());

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);


        btnStopC.setOnClickListener(v -> {
            if(etName.getText().toString().length() > 1 && etVanne.getText().toString().length() > 1 && etDiere.getText().toString().length() > 1
                    && etDorpe.getText().toString().length() > 1)
            {
                if(this.getCurrentFocus() != null){
                    if(this.getCurrentFocus().getWindowToken()!= null){
                        InputMethodManager inputMethodManager = (InputMethodManager)this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
                    }
                }
                btnStopC.setEnabled(false);
                sendC("S", "S");
                stopGameC(1);
            }
            else{
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.boxes_empty, Snackbar.LENGTH_LONG);
                snackbar.setTextColor(getResources().getColor(R.color.red));
                snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                snackbar.show(); }
        });

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }@Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0){
                    if(!String.valueOf(s.charAt(0)).toUpperCase().equals(tvLetter.getText().toString()) && !String.valueOf(s.charAt(0)).equals("")){
                        etName.setText("");
                    }
                }
            }@Override public void afterTextChanged(Editable s) { }
        });
        etVanne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }@Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0){
                    if(!String.valueOf(s.charAt(0)).toUpperCase().equals(tvLetter.getText().toString()) && !String.valueOf(s.charAt(0)).equals("")){
                        etVanne.setText("");
                    }
                }
            }@Override public void afterTextChanged(Editable s) { }
        });
        etDiere.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }@Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0){
                    if(!String.valueOf(s.charAt(0)).toUpperCase().equals(tvLetter.getText().toString()) && !String.valueOf(s.charAt(0)).equals("")){
                        etDiere.setText("");
                    }
                } }@Override public void afterTextChanged(Editable s) { }
        });
        etDorpe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }@Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0){
                    if(!String.valueOf(s.charAt(0)).toUpperCase().equals(tvLetter.getText().toString()) && !String.valueOf(s.charAt(0)).equals("")){
                        etDorpe.setText("");
                    }
                } }@Override public void afterTextChanged(Editable s) { }
        });

    }

    public void stopGameC(int i) {
        if(difficulty.equals("Ez")){
            playSound("stop");
            System.out.println("difficulty.equals(Ez) && i == 0)");
            new CountDownTimer(3000, 900) {
                @Override
                public void onTick(long l) {
                    playSound("click");
                    double lank = (double)l/1000;
                    int x = (int) Math.round(lank);
                    String str = String.valueOf(x);
                    runOnUiThread(() -> {
                        CD.startAnimation(testAnim);
                        CD.setText(str);
                    });
                }
                @Override
                public void onFinish() {
                    CD.setText("");
                    if(failSafe == 0){
                        failSafe = 1;
                        runOnUiThread(() -> {
                            CD.setText("");
                            etName.setEnabled(false);
                            etVanne.setEnabled(false);
                            etDiere.setEnabled(false);
                            etDorpe.setEnabled(false);
                            btnStopC.setVisibility(View.INVISIBLE);
                            btnStopC.setEnabled(false);
                        });
                        String naam = etName.getText().toString().trim();
                        String van = etVanne.getText().toString().trim();
                        String dier = etDiere.getText().toString().trim();
                        String dorp = etDorpe.getText().toString().trim();
                        Map<String, Object> mapp= new HashMap<>();
                        singleStaticNaam = naam;
                        System.out.println("singleStaticNaam; + " + singleStaticNaam);
                        singleStaticVan = van;
                        System.out.println("singleStaticvan; + " + singleStaticVan);
                        singleStaticDier= dier;
                        System.out.println("singleStaticdier; + " + singleStaticDier);
                        singleStaticDorp = dorp;
                        System.out.println("singleStaticdorp; + " + singleStaticDorp);

                        mapp.put("Dier", dier);
                        mapp.put("Dorp", dorp);
                        mapp.put("Naam", naam);
                        mapp.put("Van", van);
                        mapp.put("score", 0);

                        WordUploader.UploadWords(naam,van,dier,dorp);

                        intentsafe =false;
                        rdb.child(staticUsername).updateChildren(mapp).addOnCompleteListener(task -> {

                            Intent o = new Intent(ClientGameActivity.this, ClientScore.class);
                            finish();

                            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                            startActivity(o);
                            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                        });
                    }
                }
            }.start();
        }
        else
        if(failSafe == 0){
            playSound("stop");
            failSafe = 1;
            runOnUiThread(() -> {
                etName.setEnabled(false);
                etVanne.setEnabled(false);
                etDiere.setEnabled(false);
                etDorpe.setEnabled(false);
                btnStopC.setVisibility(View.INVISIBLE);
                btnStopC.setEnabled(false);
            });
            String naam = etName.getText().toString().trim();
            String van = etVanne.getText().toString().trim();
            String dier = etDiere.getText().toString().trim();
            String dorp = etDorpe.getText().toString().trim();
            Map<String, Object> mapp= new HashMap<>();

            singleStaticNaam = naam;
            singleStaticVan = van;
            singleStaticDier= dier;
            singleStaticDorp = dorp;

            mapp.put("Dier", dier);
            mapp.put("Dorp", dorp);
            mapp.put("Naam", naam);
            mapp.put("Van", van);
            mapp.put("score", 0);
            WordUploader.UploadWords(naam,van,dier,dorp);
            intentsafe =false;
            rdb.child(staticUsername).updateChildren(mapp).addOnCompleteListener(task -> {
                Intent o = new Intent(ClientGameActivity.this, ClientScore.class);
                finish();

                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                startActivity(o);
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            });
        }

    }

    private void SetLetterC(String s) {
        tvLetter.setVisibility(View.GONE);
        if(!s.equals(previousLetter) && !s.equals(" ")){
            System.out.println("setletterC ran with the letter: " + s);
            etName.setEnabled(true);
            etVanne.setEnabled(true);
            etDiere.setEnabled(true);
            etDorpe.setEnabled(true);
            etName.setText("");
            etVanne.setText("");
            etDiere.setText("");
            etDorpe.setText("");
            tvLetter.setText(s);
            previousLetter = s;
            handler.postDelayed(() -> {
                tvLetter.setVisibility(View.VISIBLE);
                tvLetter.startAnimation(anim);
                btnStopC.setEnabled(true);
                btnStopC.setVisibility(View.VISIBLE);
            }, 500);

        }else{
            handler.postDelayed(this::getLetter,500);

        }
    }


    void ListenStopCall(){

        if(!stopCalled){
            handler.postDelayed(this::ListenStopCall, 1000);
        }
        if (stopCalled){
            if(staticAct.equals("CGA")){
                btnStopC.setEnabled(false);
                stopGameC(0);
            }
        }
    }

        private void getLetter(){
            handler.postDelayed(() -> rdb.child("C").get().addOnSuccessListener(dataSnapshot -> {
                if(dataSnapshot.getValue() !=null){
                    if(dataSnapshot.exists() && !dataSnapshot.getValue().toString().equals(" ")){
                        System.out.println("GOT C " + dataSnapshot.getValue());
                        String letter = dataSnapshot.getValue().toString();
                        staticLetter = letter.charAt(0);
                        System.out.println("Charat0 of letter = " + letter.charAt(0));
                        if(letter.length() > 4){
                            game_over = "GAME_OVER";
                            SetLetterC(String.valueOf(letter.charAt(0)));
                        }else{
                        SetLetterC(letter);
                        }
                    }else{
                        handler.postDelayed(this::getLetter, 500);
                    }
                }
            }).addOnFailureListener(e -> {
                System.out.println("Error getting letter: " + e);
                handler.postDelayed(this::getLetter, 500);
            }), 1500);

        }



    private void sendC(String child, String value){
        rdb.child(child).setValue(value).addOnSuccessListener(aVoid -> System.out.println("SendS success: " + child + value))
                .addOnFailureListener(e -> sendC(child, value));
    }



    private void FixAvatarCodesC(){
        if (username0 != null && !username0.equals("")){
            if(avatar0code == 0){
                avatar0code = R.drawable.avatar0;
            }
        }
        if (username1 != null && !username1.equals("")){
            if(avatar1code == 0){
                avatar1code = R.drawable.avatar0;
            }
        }
        if (username2 != null && !username2.equals("")){
            if(avatar2code == 0){
                avatar2code = R.drawable.avatar0;
            }
        }
        if (username3 != null && !username3.equals("")){
            if(avatar3code == 0){
                avatar3code = R.drawable.avatar0;
            }
        }
        if (username4 != null && !username4.equals("")){
            if(avatar4code == 0){
                avatar4code = R.drawable.avatar0;
            }
        }
        if (username5 != null && !username5.equals("")){
            if(avatar5code == 0){
                avatar5code = R.drawable.avatar0;
            }
        }
    }
    void GameAliveWatchDog(){
        handler.postDelayed(() -> {
            if(staticAct.equalsIgnoreCase("CGA")) {
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
        bg_0 = findViewById(R.id.bg_0_cga);
        bg_1 = findViewById(R.id.bg_2_cga);
        bg_2 = findViewById(R.id.bg_3_cga);
        bg_3 = findViewById(R.id.bg_4_cga);
        bg_4 = findViewById(R.id.bg_6_cga);
        bg_5 = findViewById(R.id.bg_7_cga);
        bg_6 = findViewById(R.id.bg_8_cga);
        con = this;
        BackgroundClass.SetBackground(con, findViewById(R.id.cga_mv));
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
                    if(staticAct.equals("CGA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_0.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_0);
                        bghand0.postDelayed(bgrun0, bgdur);
                    }
                };
                bgrun1 = () -> {
                    if(staticAct.equals("CGA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_1.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_1);
                        bghand1.postDelayed(bgrun1, bgdur);
                    }
                };
                bgrun2 = () -> {
                    if(staticAct.equals("CGA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_2.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_2);
                        bghand2.postDelayed(bgrun2, bgdur);
                    }
                };
                bgrun3 = () -> {
                    if(staticAct.equals("CGA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_3.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_3);
                        bghand3.postDelayed(bgrun3, bgdur);
                    }
                };
                bgrun4 = () -> {
                    if(staticAct.equals("CGA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_4.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_4);
                        bghand4.postDelayed(bgrun4, bgdur);
                    }
                };
                bgrun5 = () -> {
                    if(staticAct.equals("CGA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_5.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_5);
                        bghand5.postDelayed(bgrun5, bgdur);
                    }
                };
                bgrun6 = () -> {
                    if(staticAct.equals("CGA")){
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