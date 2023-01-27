package com.commarian.namegamet3.HomeMade.Startup;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.commarian.namegamet3.HomeMade.Avatars.AvatarActivity;
import com.commarian.namegamet3.HomeMade.Classes.AppPreferences;
import com.commarian.namegamet3.HomeMade.Classes.BackgroundClass;
import com.commarian.namegamet3.HomeMade.Classes.LocaleHelper;
import com.commarian.namegamet3.HomeMade.Classes.Tutorials;
import com.commarian.namegamet3.HomeMade.Extras.SettingsActivity;
import com.commarian.namegamet3.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentContainerView;
import pl.droidsonroids.gif.GifImageView;

import static com.commarian.namegamet3.HomeMade.Classes.AnimHandler.animHandler;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.langChanged;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.playSound;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.random_int_gen;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.random_long_gen;

public class MainActivity extends AppCompatActivity {

    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    final String TAG = "MainActivity";

    GifImageView splashView;
    private RewardedAd mRewardedAd;


    public static boolean tv1 = true;
    public static boolean tv2 = true;
    public static boolean tv3 = true;
    public static boolean tv4 = true;
    public static boolean tv5 = true;
    public static boolean tv6 = true;
    public static boolean stopCalled;
    public static boolean kickedNuts = false;
    public static boolean newMessage = false;
    public static boolean noMoMessages = false;
    public static boolean create_game;
    public static boolean join_game;
    public static boolean public_game = false;
    public static boolean private_game = false;
    public static boolean staticServer = false;
    public static boolean soloBool = false;
    public static boolean botHasName = false;
    public static boolean botHasSurname = false;
    public static boolean botHasTown = false;
    public static boolean botHasAnimal = false;
    public static boolean tut_active = false;
    public static boolean animatedBackgrounds = true;

    public static String lobbyName;
    public static String gameType = "";
    public static String custom1 = "";
    public static String custom2 = "";
    public static String custom3 = "";
    public static String custom4 = "";
    public static String game_over;
    public static String username0 = "";
    public static String username1 = "";
    public static String username2 = "";
    public static String username3 = "";
    public static String username4 = "";
    public static String username5 = "";
    public static String staticUsername;
    public static String staticAct = "MA";
    public static String staticUserID;
    public static String firebaseServer = "https://letsnamegame-default-rtdb.firebaseio.com/";
    public static String difficulty = "";
    public static String gameLang = "";
    public static String previousLetter = "";

    public static String singleStaticNaam = "";
    public static String singleStaticVan = "";
    public static String singleStaticDier = "";
    public static String singleStaticDorp = "";

    public static char staticLetter = '0';


    public static long userCount = 0;
    public static long Points;


    public static int myAvatar = R.drawable.avatar0;
    public static int avatar0code = 0;
    public static int avatar1code = 0;
    public static int avatar2code = 0;
    public static int avatar3code = 0;
    public static int avatar4code = 0;
    public static int avatar5code = 0;
    public static int staticRounds = 0;
    public static int childAdded = 0;
    public static int staticBotScore = 0;
    public static int staticSoloScore = 0;

    public static int[] ir = new int[9];

    public static Map<String, Object> user0old = new HashMap<>();
    public static Map<String, Object> user1old = new HashMap<>();
    public static Map<String, Object> user2old = new HashMap<>();
    public static Map<String, Object> user3old = new HashMap<>();
    public static Map<String, Object> user4old = new HashMap<>();
    public static Map<String, Object> user5old = new HashMap<>();


    public static AdRequest adRequest;


    static FragmentContainerView fragContainer;


    boolean fragment = true,
            visible = true;

    AppPreferences _appPrefs;
    final Handler handler = new Handler();

    final FirebaseFirestore db = FirebaseFirestore.getInstance();

    Button exitBtn;
    Button settingsBtn;
    Button signOutBtn;
    Button playBtn;

    TextView tvPoints;
    TextView shareTV;

    AppCompatImageView PP;
    AppCompatImageView h1;
    AppCompatImageView h3;
    AppCompatImageView h4;
    AppCompatImageView h5;
    AppCompatImageView shareBtn;



    TextView tut_info;
    Button tut_btn_next;
    Button tut_btn_stop;
    View blocking;
    EditText etUser;

    AlertDialog.Builder builder ;
    private int rewardAmount;
    private int versionShowed;
    String rewardType;

    ViewGroup rootView;


    long pressedTime;



    View mainView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView = findViewById(R.id.main_main_main);
        tvPoints = findViewById(R.id.TVScoreMain);
        shareBtn = findViewById(R.id.share_button_main);
        shareTV = findViewById(R.id.share_textview_main);
        PP = findViewById(R.id.mainPP);
        tut_btn_next = findViewById(R.id.tut_btn_next);
        tut_btn_stop = findViewById(R.id.tut_btn_stop);
        tut_info = findViewById(R.id.tut_tv_info_main);
        blocking = findViewById(R.id.blocking_view_main);
        splashView = findViewById(R.id.splashView);
        etUser = findViewById(R.id.etUsername);
        mainView = findViewById(R.id.main_main_main);



        bg_0 = findViewById(R.id.bg_0_m);
        bg_1 = findViewById(R.id.bg_2_m);
        bg_2 = findViewById(R.id.bg_3_m);
        bg_3 = findViewById(R.id.bg_4_m);
        bg_4 = findViewById(R.id.bg_6_m);
        bg_5 = findViewById(R.id.bg_7_m);
        bg_6 = findViewById(R.id.bg_8_m);

        fragContainer = findViewById(R.id.fragment_container_avatar);
        playBtn = findViewById(R.id.PlayGameBtn);
        exitBtn= findViewById(R.id.exitBtnMain);
        settingsBtn= findViewById(R.id.settingsBtn);
        signOutBtn = findViewById(R.id.signOutButton);
        h1 = findViewById(R.id.helper_play);
        h3 = findViewById(R.id.helper_settings);
        h4 = findViewById(R.id.helper_switch);
        h5 = findViewById(R.id.helper_exit);
        AdView adView = findViewById(R.id.adViewMain);

        con = this;

        _appPrefs = new AppPreferences(getApplicationContext());

        ir[0] = R.drawable.plane;
        ir[1] = R.drawable.cloud1;
        ir[2] = R.drawable.cloud2;
        ir[3] = R.drawable.cloud3;
        ir[4] = R.drawable.cloud4;
        ir[5] = R.drawable.cloud5;
        ir[6] = R.drawable.cloud6;
        ir[7] = R.drawable.cloud7;
        ir[8] = R.drawable.cloud8;



        mAuth = FirebaseAuth.getInstance();


        iniShareButton();
        try{
            splashView.setImageResource(R.drawable.splash);
        } catch (Exception e) {
            try{
                splashView.setImageResource(0);
            }catch(Exception ek){
                ek.printStackTrace();
                splashView.setVisibility(View.GONE);
            }
            e.printStackTrace();
        }


        //VERSION CHECKER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if(_appPrefs.getString("versionShowed") != null && !_appPrefs.getString("versionShowed").isEmpty()){
            versionShowed = Integer.parseInt(_appPrefs.getString("versionShowed"));
        }else{
            versionShowed = 0;
        }

        HandlePackage();




        builder = new AlertDialog.Builder(this, R.style.AlertDialogThemeCustom);





        adRequest = new AdRequest.Builder().setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);


        try {

            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id_str))
                    .requestEmail()
                    .build();
            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);}
        catch (Exception e) {
            //System.out.println("Exception with GSO = " + e);
            e.printStackTrace();
        }


        try {
            copyDB();
        } catch (IOException e) {
            e.printStackTrace();
        }
        avatarPress();

        fragment = true;

        handler.postDelayed(() -> {
            if(staticUserID ==null || staticUserID.isEmpty()){
                if(staticAct.equals("MA")){
                    Snackbar snackbar = Snackbar.make(mainView, R.string.press_back_if_cant_sign, Snackbar.LENGTH_LONG);
                    snackbar.setTextColor(getResources().getColor(R.color.yellow_text));
                    snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                    snackbar.show();
                }
            }
        },30000);
        signIn();


        MobileAds.initialize(this, initializationStatus -> {
        });

        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(MainActivity.this, getString(R.string.reward_ad_unit_id),
                adRequest, new RewardedAdLoadCallback(){
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        //System.out.println("ad was not loaded");
                        //System.out.println(loadAdError.getMessage());
                        mRewardedAd = null;
                    }
                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        //System.out.println("ad was loaded");
                    }
                });



        PlayButtonPress();
        SettingsBtnPress();

        etUser.setOnFocusChangeListener((v, hasFocus) -> {
            if (etUser.getText().toString().length() > 1) {
                if (Profanity(etUser.getText().toString())) {
                    Snackbar snackbar = Snackbar.make(mainView, getString(R.string.text_contains_profanity), Snackbar.LENGTH_LONG);
                    snackbar.setTextColor(getResources().getColor(R.color.red));
                    snackbar.setBackgroundTint(getResources().getColor(R.color.red));
                    snackbar.show();
                    etUser.setText("");
                } else {
                    staticUsername = etUser.getText().toString();
                    _appPrefs.saveString("UN", staticUsername);
                }
            }
        });
        try {
            if(_appPrefs.getString("background").equals("animate")){
                animatedBackgrounds = true;
            }else{
                animatedBackgrounds = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        PointsDialog();

        getOnBackPressedDispatcher().addCallback(this, callback);
        exitBtn.setOnClickListener(v -> {
            playSound("click");
            set_button_enabled(false);
            finish();
        });

    }

    final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                //System.out.println("WE ARE IN RESULT");
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    try {
                        //System.out.println("Google Sign In was successful, authenticate with Firebase");
                        // Google Sign In was successful, authenticate with Firebase
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        if (account != null) {
                            if (account.getIdToken() != null) {
                                firebaseAuthWithGoogle(account.getIdToken());
                            } else {
                                //System.out.println("ERROR + " + " idtoken null");
                            }
                        } else {
                            //System.out.println("ERROR+" + " account == null");
                        }
                    } catch (ApiException e) {
                        //System.out.println("ERROR + " + e);
                        handler.postDelayed(MainActivity.this::signIn, 2000);
                    }
                } else {
                    //System.out.println("ERROR + Requestcode not ok " + result.getResultCode());
                    //System.out.println("ERROR + " + result);
                    signOut();
                }
            }
    );

    OnBackPressedCallback callback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            if(pressedTime + 2500 > System.currentTimeMillis()){
                finish();
            }else{
                Snackbar snackbar = Snackbar.make(mainView, R.string.pressbackagain, Snackbar.LENGTH_SHORT);
                snackbar.setTextColor(getResources().getColor(R.color.yellow_text));
                snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                snackbar.show();
            }
            pressedTime = System.currentTimeMillis();
        }
    };

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }

    public void onResume(){
        super.onResume();
        ClearStatics();
        runOnUiThread(() -> {
            staticAct = "MA";
            CheckPoints();
            set_button_enabled(true);
            visible = true;
            BackgroundClass.SetBackground(con, rootView);
            Backgrounds();

            if(langChanged){
                langChanged = false;
                Intent k = new Intent(MainActivity.this, initialize_app.class);
                overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
                finish();
                overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
                startActivity(k);
                overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if(visible){
                        avatarPress();
                        handler.postDelayed(this, 1500);
                    }

                }
            });
        });


    }
    boolean dick = true;
    private void updateUI(FirebaseUser user) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(dick){
                    if (user != null) {
                        EnableButtons();
                        dick = false;
                        avatarPress();
                        etUser.setText(staticUsername);
                        CheckFirstGame();
                    }
                    handler.postDelayed(this, 200);
                }
            }
        });
    }

    private void EnableButtons(){
        runOnUiThread(() -> {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            playBtn.setEnabled(true);
            playBtn.setVisibility(View.VISIBLE);
            exitBtn.setEnabled(true);
            exitBtn.setVisibility(View.VISIBLE);
            settingsBtn.setEnabled(true);
            settingsBtn.setVisibility(View.VISIBLE);
            signOutBtn.setEnabled(true);
            signOutBtn.setVisibility(View.VISIBLE);
            shareBtn.setVisibility(View.VISIBLE);
            shareTV.setVisibility(View.VISIBLE);


            h1.setVisibility(View.VISIBLE);
            h3.setVisibility(View.VISIBLE);
            h4.setVisibility(View.VISIBLE);
            h5.setVisibility(View.VISIBLE);


            PP.setVisibility(View.VISIBLE);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    CheckFirstGame();
                    splashView.animate().alpha(0.0f);
                    splashView.setImageResource(0);
                }
            },1750);

            EditText et = findViewById(R.id.etUsername);
            et.setVisibility(View.VISIBLE);
            if(currentUser!=null){
                String GPDN = currentUser.getDisplayName();
                staticUserID = currentUser.getUid();
                signOutBtn.setOnClickListener(v -> {
                    playSound("click");
                    visible = false;
                    builder.setCancelable(true);
                    builder.setTitle(R.string.switchA);
                    builder.setMessage(R.string.you_want_to_switch);
                    builder.setPositiveButton(R.string.yes,
                            (dialog, which) -> {
                                dialog.dismiss();
                                signOut();
                            });
                    builder.setNegativeButton(R.string.no, (dialog, which) -> {dialog.dismiss();
                    visible = true;});
                    AlertDialog dialog = builder.create();
                    dialog.show();
                });
                if(!_appPrefs.getString("UN").equals("")){
                    staticUsername = _appPrefs.getString("UN");
                }else{
                    if (GPDN != null) {
                        String[] SUN = GPDN.split(" ");
                        staticUsername = SUN[0];
                    }
                }
                et.setText(staticUsername);
            }

        });


    }
    private void signOut(){
        // Firebase sign out
        mAuth.signOut();
        tvPoints.setText("0");
        Points = 0;
        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                task -> {
                    _appPrefs.saveString("UN", "");
                    _appPrefs.saveString("lang", "");
                    _appPrefs.saveString("Avatar", "");
                    staticUsername = "";
                    PP.setImageResource(R.drawable.avatar0);
                    EnableButtons();
                    signIn();
                });
    }



    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        activityResultLauncher.launch(signInIntent);
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential).addOnSuccessListener(authResult -> {
            // Sign in success, update UI with the signed-in user's information
            FirebaseUser user = mAuth.getCurrentUser();
            updateUI(user);

        }).addOnFailureListener(e -> {
            // If sign in fails, display a message to the user.
            handler.postDelayed(() -> {
                Snackbar snackbar = Snackbar.make(mainView, R.string.auth_failed, Snackbar.LENGTH_LONG);
                snackbar.setTextColor(getResources().getColor(R.color.red));
                snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                snackbar.show();
                firebaseAuthWithGoogle(idToken);

            }, 4000);
        });
    }

    void set_button_enabled(boolean en_dis){
        playBtn.setEnabled(en_dis);
        exitBtn.setEnabled(en_dis);
        settingsBtn.setEnabled(en_dis);
        signOutBtn.setEnabled(en_dis);
        shareBtn.setEnabled(en_dis);
    }

    private void PlayButtonPress() {
        playBtn.setOnClickListener(v -> {
            playSound("click");
            set_button_enabled(false);
            noMoMessages = true;
            staticUsername = etUser.getText().toString();

            if(etUser.getText().toString().length() < 2){
                Snackbar snackbar = Snackbar.make(mainView, R.string.choose_longer_un, Snackbar.LENGTH_LONG);
                snackbar.setTextColor(getResources().getColor(R.color.yellow_text));
                snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                snackbar.show();
                runOnUiThread(() -> {
                    set_button_enabled(true);
                });
            }else if (Profanity(etUser.getText().toString())) {
                Snackbar snackbar = Snackbar.make(mainView, getString(R.string.text_contains_profanity), Snackbar.LENGTH_LONG);
                snackbar.setTextColor(getResources().getColor(R.color.red));
                snackbar.setBackgroundTint(getResources().getColor(R.color.red));
                snackbar.show();
                etUser.setText("");
                runOnUiThread(() -> {
                    set_button_enabled(true);
                });
            } else{
                visible = false;
                _appPrefs.saveString("UN", staticUsername);
                Intent i = new Intent(new Intent(MainActivity.this, PublicPrivate.class));
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                startActivity(i);
                overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            }
        });
    }


    private void SettingsBtnPress(){
        settingsBtn.setOnClickListener(v -> {
            playSound("click");
            runOnUiThread(() -> {
                set_button_enabled(false);

            });
            visible = false;
            Intent km = new Intent(MainActivity.this, SettingsActivity.class);
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            startActivity(km);
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
        });
    }


    private void avatarPress(){
        if( _appPrefs.getString("Avatar") != null && !_appPrefs.getString("Avatar").equals("")){
            String avatar = _appPrefs.getString("Avatar");
            switch (avatar) {
                case "1":
                    myAvatar = R.drawable.avatar0;
                    PP.setImageResource(myAvatar);
                    break;
                case "2":
                    myAvatar = R.drawable.avatar1;
                    PP.setImageResource(myAvatar);
                    break;
                case "3":
                    myAvatar = R.drawable.avatar2;
                    PP.setImageResource(myAvatar);
                    break;
                case "4":
                    myAvatar = R.drawable.avatar3;
                    PP.setImageResource(myAvatar);
                    break;
                case "5":
                    myAvatar = R.drawable.avatar4;
                    PP.setImageResource(myAvatar);
                    break;
                case "6":
                    myAvatar = R.drawable.avatar5;
                    PP.setImageResource(myAvatar);
                    break;
                case "7":
                    myAvatar = R.drawable.avatar7;
                    PP.setImageResource(myAvatar);
                    break;
                case "8":
                    myAvatar = R.drawable.avatar8;
                    PP.setImageResource(myAvatar);
                    break;
                case "9":
                    myAvatar = R.drawable.avatar9;
                    PP.setImageResource(myAvatar);
                    break;
                case "10":
                    myAvatar = R.drawable.avatar10;
                    PP.setImageResource(myAvatar);
                    break;
                case "11":
                    myAvatar = R.drawable.avatar11;
                    PP.setImageResource(myAvatar);
                    break;
                case "12":
                    myAvatar = R.drawable.avatar12;
                    PP.setImageResource(myAvatar);
                    break;
                case "13":
                    myAvatar = R.drawable.avatar13;
                    PP.setImageResource(myAvatar);
                    break;
                case "14":
                    myAvatar = R.drawable.avatar14;
                    PP.setImageResource(myAvatar);
                    break;
                case "15":
                    myAvatar = R.drawable.avatar15;
                    PP.setImageResource(myAvatar);
                    break;
                case "16":
                    myAvatar = R.drawable.avatar16;
                    PP.setImageResource(myAvatar);
                    break;
                case "17":
                    myAvatar = R.drawable.avatar17;
                    PP.setImageResource(myAvatar);
                    break;
                case "18":
                    myAvatar = R.drawable.avatar18;
                    PP.setImageResource(myAvatar);
                    break;
                case "19":
                    myAvatar = R.drawable.avatar19;
                    PP.setImageResource(myAvatar);
                    break;
                case "20":
                    myAvatar = R.drawable.avatar20;
                    PP.setImageResource(myAvatar);
                    break;
                case "21":
                    myAvatar = R.drawable.avatar21;
                    PP.setImageResource(myAvatar);
                    break;
                case "22":
                    myAvatar = R.drawable.avatar22;
                    PP.setImageResource(myAvatar);
                    break;
                case "23":
                    myAvatar = R.drawable.avatar23;
                    PP.setImageResource(R.drawable.avatar23);
                    break;
                case "24":
                    myAvatar = R.drawable.avatar24;
                    PP.setImageResource(myAvatar);
                    break;
                case "25":
                    myAvatar = R.drawable.avatar25;
                    PP.setImageResource(myAvatar);
                    break;
            }
        }else{
            PP.setImageResource(myAvatar);
        }

        PP.setOnClickListener(v -> {
            playSound("click");
            set_button_enabled(false);
            Intent i = new Intent(new Intent(MainActivity.this, AvatarActivity.class));
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            visible = false;
            startActivity(i);
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);

        });

    }

    private void CheckPoints(){
        tvPoints.setText(String.valueOf(Points));
        handler.postDelayed(() -> {
            if(staticUserID != null && !staticUserID.equals("")){
                db.collection(staticUserID).document("Points").get().addOnSuccessListener(documentSnapshot -> {
                    if(documentSnapshot.get("Points") != null && documentSnapshot.get("Points") != ""){
                        Points = (long) documentSnapshot.get("Points");
                        tvPoints.setText(String.valueOf(Points));
                    }else{
                        Points = 0;
                        tvPoints.setText("0");
                    }
                    tvPoints.setVisibility(View.VISIBLE);
                }).addOnFailureListener(e -> CheckPoints());
            }else{
                CheckPoints();
            }

        }, 200);

    }

    void PointsDialog(){
        tvPoints.setOnClickListener(v -> {
            playSound("click");
            builder.setCancelable(true);
            builder.setTitle(String.valueOf(Points));
            builder.setMessage(getString(R.string.points_clicked_main_menu));
            builder.setPositiveButton(R.string.yes,
                    (dialog, which) -> {
                        if (mRewardedAd != null) {
                            Activity activityContext = MainActivity.this;
                            mRewardedAd.show(activityContext, rewardItem -> {
                                // Handle the reward.
                                Log.d(TAG, "The user earned the reward.");
                                rewardAmount = rewardItem.getAmount();
                                rewardType = rewardItem.getType();
                                Snackbar snackbar = Snackbar.make(mainView, getString(R.string.your_account_has_been_credited) + " (" + rewardAmount + ")", Snackbar.LENGTH_LONG);
                                snackbar.setTextColor(getResources().getColor(R.color.green_text));
                                snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                                snackbar.show();
                                Points = Points + rewardAmount;
                                Map<String, Object> map = new HashMap<>();
                                map.put("Points", Points);
                                db.collection(staticUserID).document("Points").set(map).addOnSuccessListener(aVoid -> dialog.dismiss()).addOnFailureListener(e -> {
                                    Snackbar snackbar1 = Snackbar.make(mainView, R.string.failed_connect, Snackbar.LENGTH_LONG);
                                    snackbar1.setTextColor(getResources().getColor(R.color.red));
                                    snackbar1.setBackgroundTint(getResources().getColor(R.color.snackback));
                                    snackbar1.show();
                                });
                            });
                        } else {
                            Snackbar snackbar = Snackbar.make(mainView, getString(R.string.the_ad_isnt_ready_yet_please_retry), Snackbar.LENGTH_LONG);
                            snackbar.setTextColor(getResources().getColor(R.color.green_text));
                            snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                            snackbar.show();

                            Log.d(TAG, "The rewarded ad wasn't ready yet.");
                        }

                    });
            builder.setNegativeButton(R.string.no, (dialog, which) -> {dialog.dismiss(); visible = true;});
            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }


    void HandlePackage(){
        try {
            PackageInfo pInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
            long versionCode = pInfo.versionCode;
            if (versionCode != 0){
                db.collection("version").document("code").get().addOnSuccessListener(documentSnapshot -> {
                    if(documentSnapshot.exists()){
                        if(documentSnapshot.get("min") != null){
                            long min = (long) documentSnapshot.get("min");
                            if(versionCode < min){
                                builder = new AlertDialog.Builder(this, R.style.AlertDialogThemeCustom);
                                builder.setCancelable(false);
                                builder.setTitle(R.string.req_update_title);
                                builder.setMessage(R.string.req_update_message);
                                builder.setPositiveButton(R.string.update,
                                        (dialog, which) -> {
                                            Intent intent = new Intent(Intent.ACTION_VIEW);
                                            intent.setData(Uri.parse(
                                                    "https://play.google.com/store/apps/details?id=com.commarian.namegamet3&ah=nze9cubWfpZm4_0-Whtfv6GMk80"));
                                            intent.setPackage("com.android.vending");
                                            startActivity(intent);
                                        });
                                builder.setNegativeButton(R.string.exit, (dialog, which) -> finish());
                                AlertDialog dialog = builder.create();
                                dialog.show();
                                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse(
                                            "https://play.google.com/store/apps/details?id=com.commarian.namegamet3"));
                                    intent.setPackage("com.android.vending");
                                    startActivity(intent);
                                });
                            }else{
                                if(documentSnapshot.get("max") != null){
                                    long max = (long) documentSnapshot.get("max");
                                    if(versionCode < max && versionShowed != max){
                                        builder = new AlertDialog.Builder(this, R.style.AlertDialogThemeCustom);
                                        builder.setCancelable(true);
                                        builder.setTitle(R.string.optional_update_title);
                                        builder.setMessage(R.string.optional_update_message);
                                        builder.setPositiveButton(R.string.update,
                                                (dialog, which) -> {
                                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                                    intent.setData(Uri.parse(
                                                            "https://play.google.com/store/apps/details?id=com.commarian.namegamet3"));
                                                    intent.setPackage("com.android.vending");
                                                    startActivity(intent);
                                                });
                                        builder.setNegativeButton(R.string.later, (dialog, which) -> dialog.dismiss());
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                        versionShowed = (int) max;
                                        _appPrefs.saveString("versionShowed", String.valueOf(versionShowed));
                                    }
                                }
                            }
                        }else{
                            handler.postDelayed(this::HandlePackage, 500);
                            signIn();
                        }
                    }else{
                        handler.postDelayed(this::HandlePackage, 500);
                        signIn();
                    }
                }).addOnFailureListener(e -> handler.postDelayed(this::HandlePackage,500));
            }else{
                Snackbar snackbar = Snackbar.make(mainView, getString(R.string.error_getting_version), Snackbar.LENGTH_LONG);
                snackbar.setTextColor(getResources().getColor(R.color.red));
                snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                snackbar.show();
                signIn();
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    void iniShareButton(){
        Intent sendIntent = new Intent();

        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                getString(R.string.share_text) +"\n" + getString(R.string.share_link));
        sendIntent.setType("text/plain");
        String title = getResources().getString(R.string.share_button);

        Intent chooser = Intent.createChooser(sendIntent, title);

        shareBtn.setOnClickListener(v -> {
            try {
                startActivity(chooser);
            } catch (ActivityNotFoundException e) {
                System.out.println(e);
            }
        });
        shareTV.setOnClickListener(v -> {
            try {
                startActivity(chooser);
            } catch (ActivityNotFoundException e) {
                System.out.println(e);
            }
        });
    }




    final static String[] strongProfanity = {"poes", "fok", "fuck","dickhead",
            "hoerkop","doosgesig","kaffer", "nigger", "tietkop",  "asshole",
            };



    final static String[] weakProfanity = {"kak", "hoer", "moer", "bliksem", "blixem", "doos",  "myt", "hoermyt", "twat",
            "faggot",  "moffie",  "poephol", "poepol", "teef","bitch","penis", "tosser","piss", "tril", "totter","bastard",
            "god", "etter", "nai", "tiet", "naai","naier",  "shit","vok", "pis", "piel",  "kont","cunt"};

    //Checks a string for profanity defined in xmlStrings. If it contains profanity then it returns true. else false.
    public static boolean Profanity(String str){
        //System.out.println("HERE IS THE WORD BEING CHECKED " + str);

        for (String s : strongProfanity) {
            //System.out.println("HERE IS THE WORD CHECKED IN StrongPROFANITY " + s);
            if (str.toLowerCase().contains(s.toLowerCase())) {
                //System.out.println("TRUE RETURNED IN PROFANITY");
                return true;
            }
        }
        for (String w : weakProfanity) {
            //System.out.println("HERE IS THE WORD CHECKED IN WEAKPROFANITY " + w);
            if (str.trim().equalsIgnoreCase(w)) {
                //System.out.println("TRUE RETURNED IN PROFANITY");
                return true;
            }
        }
        //System.out.println("FALSE RETURNED IN PROFANITY");
        return false;
    }
    
    private void ClearStatics(){
        tv1 = true;
        tv2 = true;
        tv3 = true;
        tv4 = true;
        tv5 = true;
        tv6 = true;
        kickedNuts = false;
        newMessage = false;
        create_game = false;
        join_game = false;
        public_game = false;
        private_game = false;
        stopCalled = false;
        staticServer = false;
        soloBool = false;
        botHasName = false;
        botHasSurname = false;
        botHasTown = false;
        botHasAnimal = false;
        lobbyName= "";
        gameType = "";
        custom1 = "";
        custom2 = "";
        custom3 = "";
        custom4 = "";
        game_over = "";
        username0 = "";
        username1 = "";
        username2 = "";
        username3 = "";
        username4 = "";
        username5 = "";
        staticAct = "MA";
        difficulty = "";
        gameLang = "";
        previousLetter = "";
        staticSoloScore = 0;
        staticLetter = '0';

        singleStaticNaam = "";
        singleStaticVan = "";
        singleStaticDier = "";
        singleStaticDorp = "";

        userCount = 0;

        avatar0code = 0;
        avatar1code = 0;
        avatar2code = 0;
        avatar3code = 0;
        avatar4code = 0;
        avatar5code = 0;
        staticRounds = 0;
        childAdded = 0;
        staticBotScore = 0;

        user0old.clear();
        user1old.clear();
        user2old.clear();
        user3old.clear();
        user4old.clear();
        user5old.clear();

    }
    int checkGate = 0;
    void CheckFirstGame(){
        //TODO delete this line under
        //_appPrefs.saveString("First Time", "First");
        if (checkGate == 1){
            if(_appPrefs.getString("First Time").isEmpty() ||  _appPrefs.getString("First Time") == null){
                _appPrefs.saveString("background", "animate");
                if(staticUserID != null ){
                    if(!staticUserID.equals("")){
                        if(staticUsername != null){
                            if(!staticUsername.equals("")){
                                overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
                                Intent first = new Intent(MainActivity.this, FirstTime.class);
                                overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
                                startActivity(first);
                                overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
                            }else{
                                handler.postDelayed(this::CheckFirstGame,2000);
                            }
                        }else{
                            handler.postDelayed(this::CheckFirstGame,2000);
                        }
                    }else{
                        handler.postDelayed(this::CheckFirstGame,2000);
                    }
                }else{
                    handler.postDelayed(this::CheckFirstGame,2000);
                }
            }else if(_appPrefs.getString("First Time").equals("First")){
                TutorialStart();
            }
        }
        checkGate++;
    }
    public void copyDB() throws IOException {

        String names_name = "NamesDB.db";
        String names_path = "data/data/com.commarian.namegamet3/databases/";
        String names_path_file = "data/data/com.commarian.namegamet3/databases/NamesDB.db";

        String animals_name = "AnimalsDB.db";
        String animals_path = "data/data/com.commarian.namegamet3/databases/";
        String animals_path_file = "data/data/com.commarian.namegamet3/databases/AnimalsDB.db";

        String towns_name = "TownsDB.db";
        String towns_path = "data/data/com.commarian.namegamet3/databases/";
        String towns_path_file = "data/data/com.commarian.namegamet3/databases/TownsDB.db";

        String surnames_name = "SurnamesDB.db";
        String surnames_path = "data/data/com.commarian.namegamet3/databases/";
        String surnames_path_file = "data/data/com.commarian.namegamet3/databases/SurnamesDB.db";

        String diere_name = "DiereDB.db";
        String diere_path = "data/data/com.commarian.namegamet3/databases/";
        String diere_path_file = "data/data/com.commarian.namegamet3/databases/DiereDB.db";

        String towns_mark_name = "TownsMarkDB.db";
        String towns_mark_path = "data/data/com.commarian.namegamet3/databases/";
        String towns_mark_path_file = "data/data/com.commarian.namegamet3/databases/TownsMarkDB.db";

        handler.postDelayed(() -> CopyFunc(names_name, names_path, names_path_file), 0);
        handler.postDelayed(() -> CopyFunc(animals_name, animals_path, animals_path_file), 200);
        handler.postDelayed(() -> CopyFunc(towns_name, towns_path, towns_path_file), 400);
        handler.postDelayed(() -> CopyFunc(surnames_name, surnames_path, surnames_path_file), 600);
        handler.postDelayed(() -> CopyFunc(diere_name, diere_path, diere_path_file), 800);
        handler.postDelayed(() -> CopyFunc(towns_mark_name, towns_mark_path, towns_mark_path_file), 1000);
    }

    void CopyFunc(String DB_NAME, String DB_PATH, String DB_PATH_FILE){
        try {
            File file = new File(DB_PATH_FILE);
            if (file.exists()) {

                boolean del = file.delete();
                //System.out.println("DELETED Success" + del);
            }
            InputStream ip =  this.getAssets().open(DB_NAME);
            Log.i("Input Stream....",ip+"");
            String op=  DB_PATH  +  DB_NAME ;
            OutputStream output = new FileOutputStream( op);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = ip.read(buffer))>0){
                output.write(buffer, 0, length);
                Log.i("Content.... ",length+"");
            }
            output.flush();
            output.close();
            ip.close();
            //System.out.println("Files Written "+ DB_NAME);

        }
        catch (IOException e) {
            Log.v("error", e.toString());
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
        con = this;
        BackgroundClass.SetBackground(con, mainView);
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
                    if(staticAct.equals("MA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_0.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_0);
                        bghand0.postDelayed(bgrun0, bgdur);
                    }
                };
                bgrun1 = () -> {
                    if(staticAct.equals("MA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_1.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_1);
                        bghand1.postDelayed(bgrun1, bgdur);
                    }
                };
                bgrun2 = () -> {
                    if(staticAct.equals("MA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_2.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_2);
                        bghand2.postDelayed(bgrun2, bgdur);
                    }
                };
                bgrun3 = () -> {
                    if(staticAct.equals("MA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_3.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_3);
                        bghand3.postDelayed(bgrun3, bgdur);
                    }
                };
                bgrun4 = () -> {
                    if(staticAct.equals("MA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_4.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_4);
                        bghand4.postDelayed(bgrun4, bgdur);
                    }
                };
                bgrun5 = () -> {
                    if(staticAct.equals("MA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_5.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_5);
                        bghand5.postDelayed(bgrun5, bgdur);
                    }
                };
                bgrun6 = () -> {
                    if(staticAct.equals("MA")){
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

    void TutorialStart(){
        Tutorials.TutorialFunction(con, blocking, tut_btn_next, tut_btn_stop, tut_info, etUser, PP, tvPoints, rootView, shareBtn, 1);
        _appPrefs.saveString("First Time", "Main");
        }
}