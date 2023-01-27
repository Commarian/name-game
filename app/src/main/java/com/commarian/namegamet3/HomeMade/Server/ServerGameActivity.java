package com.commarian.namegamet3.HomeMade.Server;

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

import com.commarian.namegamet3.HomeMade.Classes.AppPreferences;
import com.commarian.namegamet3.HomeMade.Classes.BackgroundClass;
import com.commarian.namegamet3.HomeMade.Classes.LocaleHelper;
import com.commarian.namegamet3.HomeMade.Classes.WordUploader;
import com.commarian.namegamet3.R;
import com.google.android.gms.ads.AdView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.game_over;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.ir;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.lobbyName;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.singleStaticDier;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.singleStaticDorp;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.singleStaticNaam;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.singleStaticVan;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.soloBool;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticAct;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticLetter;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticRounds;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticUsername;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.stopCalled;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username0;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username1;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username2;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username3;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username4;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.username5;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.playSound;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.random_int_gen;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.random_long_gen;

public class ServerGameActivity extends AppCompatActivity {
    DatabaseReference rdb = FirebaseDatabase.getInstance(firebaseServer).getReference(lobbyName);
    DatabaseReference cdb = FirebaseDatabase.getInstance("https://letsnamegame-default-rtdb.firebaseio.com").getReference(lobbyName);
    //FirebaseFirestore db = FirebaseFirestore.getInstance();
    static List<Character> listChar = new ArrayList<>();
    final Handler handler = new Handler();
    int runtime = 0;
    int spamCatch = 0;
    Animation anim;
    Animation spin ;
    Boolean timo = true;
    AppCompatImageView stopBtnS;
    EditText etName ;
    EditText etVanne;
    EditText etDiere;
    EditText etDorpe;
    Animation vibrateAnim;
    Animation testAnim;

    AppPreferences _appPrefs;
    TextView CD;
    TextView tvLetter;

    TextView tvOne;
    TextView tvTwo;
    TextView tvThree;
    TextView tvFour;
    TextView tvLang;

    Runnable name_run;
    Runnable surname_run;
    Runnable town_run;
    Runnable animal_run;
    int multiplier = 1;
    long delay;
    int percentile;
    int multi =1;
    int secondsFromStart;



    AlertDialog.Builder builder;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_game);
        secondsFromStart = 0;
        staticAct = "SGA";
        Backgrounds();
        game_over = "";
        if(soloBool){
            BotTimer();
            DifficultyAligner();
        }

        //SetGameState();

        //System.out.println("ONCREATE IN SAG");
        stopBtnS = findViewById(R.id.stop_btn_server_game);
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim);
        spin = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.spin);
        timo = true;
        etName = findViewById(R.id.etNameS);
        etVanne = findViewById(R.id.etVanneS);
        etDiere = findViewById(R.id.etDiereS);
        etDorpe = findViewById(R.id.etDorpeS);

        tvOne = findViewById(R.id.tvNameS);
        tvTwo = findViewById(R.id.tvVanneS);
        tvThree = findViewById(R.id.tvDiereS);
        tvFour = findViewById(R.id.tvDorpeS);
        tvLang = findViewById(R.id.TVLangGameServer);

        CD = findViewById(R.id.SAGCDT);
        CD.setText("");
        tvLetter = findViewById(R.id.tvLetterS);
        _appPrefs = new AppPreferences(getApplicationContext());
        if(_appPrefs.getString("runtime") != null || !_appPrefs.getString("runtime").isEmpty()){
            runtime = Integer.parseInt(_appPrefs.getString("runtime"));
        }else{
            _appPrefs.saveString("runtime", "0");
            runtime = 0;
        }
        startGame(runtime);
        FixAvatarCodes();

        if(gameLang != null && !gameLang.equals("")){
            if(gameLang.equals("en")){
                tvLang.setText(getString(R.string.english));
            }else if(gameLang.equals("afr")) {
                tvLang.setText(getString(R.string.afrikaans));
            }
        }

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

        //Ads
        AdView adView = (AdView) findViewById(R.id.adViewSGA);

        adView.loadAd(adRequest);


        tvLetter.setVisibility(View.INVISIBLE);
        stopBtnS.setVisibility(View.INVISIBLE);
        spamCatch = 0;
        //System.out.println("Difficulty = " + difficulty);
        vibrateAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.vib);
        testAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.test);

        etName.setEnabled(true);
        etVanne.setEnabled(true);
        etDiere.setEnabled(true);
        etDorpe.setEnabled(true);
        etName.setText("");
        etVanne.setText("");
        etDiere.setText("");
        etDorpe.setText("");

        if(!soloBool){
            handler.postDelayed(this::ResetR, 7000);
        }


        builder = new AlertDialog.Builder(this, R.style.AlertDialogThemeCustom);
        ListenStopCall();

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
                                runtime = 0;
                                rdb.removeValue().addOnCompleteListener(task1 -> cdb.removeValue().addOnCompleteListener(task -> {
                                    finish();
                                    //System.out.println("Deleted the thing");
                                }));


                        });
                builder.setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss());
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);





        stopBtnS.setOnClickListener(view -> {
            playSound("click");
            if(etName.getText().toString().length() > 1 && etVanne.getText().toString().length() > 1 && etDiere.getText().toString().length() > 1
                    && etDorpe.getText().toString().length() > 1){
                sendS("S", "S");
                stopGameS();
                if(this.getCurrentFocus() != null){
                    if(this.getCurrentFocus().getWindowToken()!= null){
                        InputMethodManager inputMethodManager = (InputMethodManager)this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
                    }
                }
            }
            else{
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.boxes_empty, Snackbar.LENGTH_LONG);
                snackbar.setTextColor(getResources().getColor(R.color.yellow_text));
                snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                snackbar.show();
            }

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

    public void startGame(int i){
        tvLetter.setVisibility(View.GONE);
        _appPrefs.saveString("runtime", "1");
        runtime = 1;
        if(i == 0){
            listChar.add('A');
            listChar.add('B');
            listChar.add('C');
            listChar.add('D');
            listChar.add('E');
            listChar.add('F');
            listChar.add('G');
            listChar.add('H');
            listChar.add('I');
            listChar.add('J');
            listChar.add('K');
            listChar.add('L');
            listChar.add('M');
            listChar.add('N');
            listChar.add('O');
            listChar.add('P');
            listChar.add('R');
            listChar.add('S');
            listChar.add('T');
            listChar.add('U');
            if(difficulty.equals("Hard")){
                if(staticRounds > 26){
                    staticRounds = 26;
                }
                listChar.add('Q');
                listChar.add('W');
                listChar.add('X');
                listChar.add('Y');
                listChar.add('Z');
                listChar.add('V');

            }
            if(!difficulty.equals("Hard")){
                if(staticRounds > 20){
                    staticRounds = 20;
                }
            }
            int charSize = listChar.size();
            for(int k = 1; k <= (charSize - staticRounds); k++){
                Random r = new Random();
                int id = r.nextInt(listChar.size());
                char c = listChar.get(id);
                listChar.remove(Character.valueOf(c));
            }

        }
        if (!listChar.isEmpty()) {
            //System.out.println(listChar);
            Random r = new Random();
            int id = r.nextInt(listChar.size());
            char c = listChar.get(id);
            //System.out.println(c);
            listChar.remove(Character.valueOf(c));
            String text = String.valueOf(c);
            if(listChar.size() == 0){
                sendS("C", text + "GAMEOVER");
                game_over = "GAME_OVER";
            }else{
            sendS("C", text);
            }
            //System.out.println("SENT C + "+ text);
            tvLetter.setText(text);
            tvLetter.setVisibility(View.INVISIBLE);
            staticLetter = text.charAt(0);
            etName.setEnabled(true);
            etVanne.setEnabled(true);
            etDiere.setEnabled(true);
            etDorpe.setEnabled(true);
            etName.setText("");
            etVanne.setText("");
            etDiere.setText("");
            etDorpe.setText("");
            handler.postDelayed(() -> {
                tvLetter.startAnimation(anim);
                tvLetter.setVisibility(View.VISIBLE);
                stopBtnS.setVisibility(View.VISIBLE);
                stopBtnS.setEnabled(true);
            }, 1500);
        }
        else{
            tvLetter.startAnimation(vibrateAnim);
            tvLetter.setText(R.string.GameOver);
            sendS("C", "GAME_OVER");
            game_over = "GAME_OVER";
            Intent o = new Intent(ServerGameActivity.this, ServerScoreboard.class);

            finish();
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            startActivity(o);
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
        }
        //System.out.println("List of chars remaining" + listChar);

    }


    void ListenStopCall(){
        if(!stopCalled){
            handler.postDelayed(this::ListenStopCall, 1000);
        }
        if (stopCalled){
            if(staticAct.equals("SGA")){
                stopBtnS.setEnabled(false);
                stopGameS();
            }
        }
    }



    public void stopGameS() {
        if(difficulty.equals("Ez") && !soloBool){
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
                    playSound("stop");
                    CD.setText("");
                    runOnUiThread(() -> {
                        CD.setText("");
                        String naam = etName.getText().toString().trim();
                        String van = etVanne.getText().toString().trim();
                        String dier = etDiere.getText().toString().trim();
                        String dorp = etDorpe.getText().toString().trim();

                        singleStaticNaam = naam;
                        //System.out.println("singleStaticNaam; + " + singleStaticNaam);
                        singleStaticVan = van;
                        //System.out.println("singleStaticvan; + " + singleStaticVan);
                        singleStaticDier= dier;
                        //System.out.println("singleStaticdier; + " + singleStaticDier);
                        singleStaticDorp = dorp;
                        //System.out.println("singleStaticdorp; + " + singleStaticDorp);

                        Map<String, Object> mapp= new HashMap<>();
                        mapp.put("Dier", dier);
                        mapp.put("Dorp", dorp);
                        mapp.put("Naam", naam);
                        mapp.put("Van", van);
                        mapp.put("score", 0);
                        WordUploader.UploadWords(naam,van,dier,dorp);
                        if(!soloBool){
                            rdb.child(staticUsername).updateChildren(mapp).addOnCompleteListener(task -> System.out.println("Successfully set map into rdb"));
                        }
                        etName.setEnabled(false);
                        etVanne.setEnabled(false);
                        etDiere.setEnabled(false);
                        etDorpe.setEnabled(false);
                    });
                    runtime = 1;
                    if(spamCatch == 0){
                        spamCatch = 1;
                        runOnUiThread(() -> {
                            stopBtnS.setEnabled(false);
                            stopBtnS.setVisibility(View.INVISIBLE);
                            tvLetter.setVisibility(View.INVISIBLE);
                        });
                        Intent o = new Intent(ServerGameActivity.this, ServerScore.class);

                        finish();
                        overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                        startActivity(o);
                        overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                    }
                }
            }.start();
        }else{
            playSound("stop");
            runOnUiThread(() -> {
                String naam = etName.getText().toString().trim();
                String van = etVanne.getText().toString().trim();
                String dier = etDiere.getText().toString().trim();
                String dorp = etDorpe.getText().toString().trim();

                singleStaticNaam = naam;
                //System.out.println("singleStaticNaam; + " + singleStaticNaam);
                singleStaticVan = van;
                //System.out.println("singleStaticvan; + " + singleStaticVan);
                singleStaticDier= dier;
                //System.out.println("singleStaticdier; + " + singleStaticDier);
                singleStaticDorp = dorp;
                //System.out.println("singleStaticdorp; + " + singleStaticDorp);


                Map<String, Object> mapp= new HashMap<>();
                mapp.put("Dier", dier);
                mapp.put("Dorp", dorp);
                mapp.put("Naam", naam);
                mapp.put("Van", van);
                mapp.put("score", 0);
                WordUploader.UploadWords(naam,van,dier,dorp);
                if(!soloBool){
                    rdb.child(staticUsername).updateChildren(mapp).addOnCompleteListener(task -> System.out.println("Successfully set map into rdb"));
                }
                etName.setEnabled(false);
                etVanne.setEnabled(false);
                etDiere.setEnabled(false);
                etDorpe.setEnabled(false);
            });
            runtime = 1;
            if(spamCatch == 0){
                spamCatch = 1;
                Intent o = new Intent(ServerGameActivity.this, ServerScore.class);
                finish();
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                startActivity(o);
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                runOnUiThread(() -> {
                    stopBtnS.setEnabled(false);
                    stopBtnS.setVisibility(View.INVISIBLE);
                    tvLetter.setVisibility(View.INVISIBLE);
                });
            }
        }
    }


    private void sendS(String child, String value){
        if(!soloBool){
            rdb.child(child).setValue(value).addOnSuccessListener(aVoid -> System.out.println("SendS success: " + child + value))
                    .addOnFailureListener(e -> sendS(child, value));
        }
    }


    private void ResetR(){
        rdb.child("R").setValue(0)
                .addOnSuccessListener(aVoid -> System.out.println("Reset R Success"))
                .addOnFailureListener(e -> {
            handler.postDelayed(this::ResetR, 2000);
            System.out.println("Error resetting R " + e);

        });
    }

    private void FixAvatarCodes(){
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

    boolean botRanThroughFirst = false;


    void BotTimer(){
        Random rand = new Random();
        multiplier = rand.nextInt(multi) + 1;
        name_run = () -> {
            if (staticAct.equals("SGA")) {
                System.out.println("NAME RUN");
                if (rand.nextInt(1001) > percentile&& !botHasName) {
                    System.out.println("bot has a name");
                    botHasName = true;
                }
                if(botRanThroughFirst){
                    BotSecondRun(delay * multiplier);
                }else{
                    handler.postDelayed(surname_run, delay * multiplier);
                }
            }
        };
        surname_run = () -> {
            System.out.println("SURNAME RUN");
            if (staticAct.equals("SGA")) {
                if (rand.nextInt(1001) > percentile && !botHasSurname) {
                    botHasSurname = true;
                    System.out.println("bot has a surname");
                }
                if(botRanThroughFirst){
                    BotSecondRun(delay * multiplier);
                }else{
                    handler.postDelayed(animal_run, delay * multiplier);
                }
            }
        };
        animal_run = () -> {
            System.out.println("ANIMAL RUN");
            if (staticAct.equals("SGA")) {
                if (rand.nextInt(1001) > percentile && !botHasAnimal) {
                    botHasAnimal = true;
                    System.out.println("bot has an animal");
                }
                if(botRanThroughFirst){
                    BotSecondRun(delay * multiplier);
                }else{
                    handler.postDelayed(town_run, delay * multiplier);
                }
            }
        };
        town_run = () -> {
            System.out.println("TOWN RUN");
            botRanThroughFirst =true;
            if (staticAct.equals("SGA")) {
                if (rand.nextInt(1001) > percentile && !botHasTown) {
                    botHasTown = true;
                    System.out.println("bot has a town");
                }
            }
            BotSecondRun(delay * multiplier);
        };



        if(staticAct.equalsIgnoreCase("SGA")){
            handler.postDelayed(name_run,  delay * multiplier);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(botHasName && botHasSurname && botHasAnimal && botHasTown){
                        if(staticAct.equals("SGA")){
                            stopGameS();
                        }
                    }else{
                        if(staticAct.equals("SGA")){
                            if(secondsFromStart >= 120){
                                botHasName = true;
                                botHasSurname = true;
                                botHasAnimal = true;
                                botHasTown = true;
                            }
                            multiplier = rand.nextInt(multi) +1;
                            secondsFromStart++;
                            handler.postDelayed(this, 1000);
                        }
                    }
                }
            }, 1000);
        }
    }

    void BotSecondRun(long DELAY){
        if(botHasSurname){
            if(botHasAnimal){
                if(botHasTown){
                    handler.postDelayed(name_run, DELAY);
                }else{
                    handler.postDelayed(town_run, DELAY);
                }
            }else{
                handler.postDelayed(animal_run, DELAY);
            }
        }else{
            handler.postDelayed(surname_run, DELAY);
        }
    }

    void DifficultyAligner(){
        Random rand = new Random();
        switch (difficulty){
            case "Ez":
                multi = 4;
                delay = 4000;
                percentile = 500;
                System.out.println("EZ INITIALIZED");
                break;
            case "Med":
            case "Rush":
            case "Custom":
                multi = 3;
                delay = 3000;
                percentile = 350;
                System.out.println("MED INITIALIZED");
                break;
            case "Hard":
                multi = 2;
                delay = 3100;
                percentile = 50;
                System.out.println("Hard INITIALIZED");
                break;
            default:
                multi = 3;
                delay = 5000;
                percentile = 200;
        }
        multiplier = rand.nextInt(multi) + 1;
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
        bg_0 = findViewById(R.id.bg_0_sga);
        bg_1 = findViewById(R.id.bg_2_sga);
        bg_2 = findViewById(R.id.bg_3_sga);
        bg_3 = findViewById(R.id.bg_4_sga);
        bg_4 = findViewById(R.id.bg_6_sga);
        bg_5 = findViewById(R.id.bg_7_sga);
        bg_6 = findViewById(R.id.bg_8_sga);
        con = this;
        BackgroundClass.SetBackground(con, findViewById(R.id.sga_mv));
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
                    if(staticAct.equals("SGA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_0.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_0);
                        bghand0.postDelayed(bgrun0, bgdur);
                    }
                };
                bgrun1 = () -> {
                    if(staticAct.equals("SGA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_1.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_1);
                        bghand1.postDelayed(bgrun1, bgdur);
                    }
                };
                bgrun2 = () -> {
                    if(staticAct.equals("SGA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_2.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_2);
                        bghand2.postDelayed(bgrun2, bgdur);
                    }
                };
                bgrun3 = () -> {
                    if(staticAct.equals("SGA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_3.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_3);
                        bghand3.postDelayed(bgrun3, bgdur);
                    }
                };
                bgrun4 = () -> {
                    if(staticAct.equals("SGA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_4.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_4);
                        bghand4.postDelayed(bgrun4, bgdur);
                    }
                };
                bgrun5 = () -> {
                    if(staticAct.equals("SGA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_5.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_5);
                        bghand5.postDelayed(bgrun5, bgdur);
                    }
                };
                bgrun6 = () -> {
                    if(staticAct.equals("SGA")){
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