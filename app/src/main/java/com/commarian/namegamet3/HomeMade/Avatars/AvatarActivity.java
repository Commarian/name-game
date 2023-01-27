package com.commarian.namegamet3.HomeMade.Avatars;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.commarian.namegamet3.HomeMade.Classes.AppPreferences;
import com.commarian.namegamet3.HomeMade.Classes.LocaleHelper;
import com.commarian.namegamet3.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.Points;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticUserID;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.playSound;

public class AvatarActivity extends AppCompatActivity {
    CardView back;
    CardView cv1;
    CardView cv2;
    CardView cv3;
    CardView cv4;
    CardView cv5;
    CardView cv6;
    CardView cv7;
    CardView cv8;
    CardView cv9;
    CardView cv10;
    CardView cv11;
    CardView cv12;
    CardView cv13;
    CardView cv14;
    CardView cv15;
    CardView cv16;
    CardView cv17;
    CardView cv18;
    CardView cv19;
    CardView cv20;
    CardView cv21;
    CardView cv23;
    CardView cv22;
    CardView cv24;
    CardView cv25;

    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    TextView tv7;
    TextView tv8;
    TextView tv9;
    TextView tv10;
    TextView tv11;
    TextView tv12;
    TextView tv13;
    TextView tv14;
    TextView tv15;
    TextView tv16;
    TextView tv17;
    TextView tv18;
    TextView tv19;
    TextView tv20;
    TextView tv21;
    TextView tv22;
    TextView tv23;
    TextView tv24;
    TextView tv25;

    String tio = "";


    AlertDialog.Builder builder;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    AppPreferences _appPrefs;

    private final String TAG = "AvatarActivity";


    Handler handler = new Handler();

    private RewardedAd mRewardedAd;
    private int rewardAmount;
    private String rewardType;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
        _appPrefs = new AppPreferences(getApplicationContext());
        back = findViewById(R.id.cvBackAvatar);
        cv1 = findViewById(R.id.cv1Avatar);
        cv2 = findViewById(R.id.cv2Avatar);
        cv3 = findViewById(R.id.cv3Avatar);
        cv4 = findViewById(R.id.cv4Avatar);
        cv5 = findViewById(R.id.cv5Avatar);
        cv6 = findViewById(R.id.cv6Avatar);
        cv7 = findViewById(R.id.cv7Avatar);
        cv8 = findViewById(R.id.cv8Avatar);
        cv9 = findViewById(R.id.cv9Avatar);
        cv10 = findViewById(R.id.cv10Avatar);
        cv11 = findViewById(R.id.cv11Avatar);
        cv12 = findViewById(R.id.cv12Avatar);
        cv13 = findViewById(R.id.cv13Avatar);
        cv14 = findViewById(R.id.cv14Avatar);
        cv15 = findViewById(R.id.cv15Avatar);
        cv16 = findViewById(R.id.cv16Avatar);
        cv17 = findViewById(R.id.cv17Avatar);
        cv18 = findViewById(R.id.cv18Avatar);
        cv19 = findViewById(R.id.cv19Avatar);
        cv20 = findViewById(R.id.cv20Avatar);
        cv21 = findViewById(R.id.cv21Avatar);
        cv22 = findViewById(R.id.cv22Avatar);
        cv23 = findViewById(R.id.cv23Avatar);
        cv24 = findViewById(R.id.cv24Avatar);
        cv25 = findViewById(R.id.cv25Avatar);

        tv1 = findViewById(R.id.tv1A);
        tv2 = findViewById(R.id.tv2A);
        tv3 = findViewById(R.id.tv3A);
        tv4 = findViewById(R.id.tv4A);
        tv5 = findViewById(R.id.tv5A);
        tv6 = findViewById(R.id.tv6A);
        tv7 = findViewById(R.id.tv7A);
        tv8 = findViewById(R.id.tv8A);
        tv9 = findViewById(R.id.tv9A);
        tv10 = findViewById(R.id.tv10A);
        tv11 = findViewById(R.id.tv11A);
        tv12 = findViewById(R.id.tv12A);
        tv13 = findViewById(R.id.tv13A);
        tv14 = findViewById(R.id.tv14A);
        tv15 = findViewById(R.id.tv15A);
        tv16 = findViewById(R.id.tv16A);
        tv17 = findViewById(R.id.tv17A);
        tv18 = findViewById(R.id.tv18A);
        tv19 = findViewById(R.id.tv19A);
        tv20 = findViewById(R.id.tv20A);
        tv21 = findViewById(R.id.tv21A);
        tv22 = findViewById(R.id.tv22A);
        tv23 = findViewById(R.id.tv23A);
        tv24 = findViewById(R.id.tv24A);
        tv25 = findViewById(R.id.tv25A);

        tio = getString(R.string.text_if_owned);


        iniAvatars();


        MobileAds.initialize(this, initializationStatus -> {
        });

        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(AvatarActivity.this, getString(R.string.reward_ad_unit_id),
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
                        System.out.println("ad was loaded");
                    }
                });
        builder = new AlertDialog.Builder(this, R.style.AlertDialogThemeCustom);
        builder.setCancelable(true);

        back.setOnClickListener(v -> {
            playSound("click");
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            finish();
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
        });
    }

    void iniAvatars(){
        db.collection(staticUserID).document("PP").get().addOnSuccessListener(documentSnapshot -> {
            if(documentSnapshot.exists()){
                if(documentSnapshot.get("1") != null){
                    tv1.setText(getString(R.string.text_if_owned));
                    cv1.setOnClickListener(v -> {
                        playSound("click");
                        owned("1");
                    });
                }else{
                    cv1.setOnClickListener(v -> {
                        playSound("click");
                        selector(1, "1", getString(R.string.avatar_1_title), getString(R.string.avatar_1_description),250);
                    });
                }

                if(documentSnapshot.get("2") != null){
                    tv2.setText(getString(R.string.text_if_owned));
                    cv2.setOnClickListener(v -> {
                        playSound("click");
                        owned("2");
                    });
                }else{
                    cv2.setOnClickListener(v -> {
                        playSound("click");
                        selector(2, "2", getString(R.string.avatar_2_title), getString(R.string.avatar_2_description),250);
                    });
                }

                if(documentSnapshot.get("3") != null){
                    tv3.setText(getString(R.string.text_if_owned));
                    cv3.setOnClickListener(v -> {
                        playSound("click");
                        owned("3");
                    });
                }else{
                    cv3.setOnClickListener(v -> {
                        playSound("click");
                        selector(3, "3", getString(R.string.avatar_3_title), getString(R.string.avatar_3_description),250);
                    });
                }

                if(documentSnapshot.get("4") != null){
                    tv4.setText(getString(R.string.text_if_owned));
                    cv4.setOnClickListener(v -> {
                        playSound("click");
                        owned("4");
                    });
                }else{
                    cv4.setOnClickListener(v -> {
                        playSound("click");
                        selector(4, "4", getString(R.string.avatar_4_title), getString(R.string.avatar_4_description),250);
                    });
                }

                if(documentSnapshot.get("5") != null){
                    tv5.setText(getString(R.string.text_if_owned));
                    cv5.setOnClickListener(v -> {
                        playSound("click");
                        owned("5");
                    });
                }else{
                    cv5.setOnClickListener(v -> {
                        playSound("click");
                        selector(5, "5", getString(R.string.avatar_5_title), getString(R.string.avatar_5_description),250);
                    });
                }

                if(documentSnapshot.get("6") != null){
                    tv6.setText(getString(R.string.text_if_owned));
                    cv6.setOnClickListener(v -> {
                        playSound("click");
                        owned("6");
                    });
                }else{
                    cv6.setOnClickListener(v -> {
                        playSound("click");
                        selector(6, "6", getString(R.string.avatar_6_title), getString(R.string.avatar_6_description),500);
                    });
                }

                if(documentSnapshot.get("7") != null){
                    tv7.setText(getString(R.string.text_if_owned));
                    cv7.setOnClickListener(v -> {
                        playSound("click");
                        owned("7");
                    });
                }else{
                    cv7.setOnClickListener(v -> {
                        playSound("click");
                        selector(7, "7", getString(R.string.avatar_7_title), getString(R.string.avatar_7_description),500);
                    });
                }

                if(documentSnapshot.get("8") != null){
                    tv8.setText(getString(R.string.text_if_owned));
                    cv8.setOnClickListener(v -> {
                        playSound("click");
                        owned("8");
                    });
                }else{
                    cv8.setOnClickListener(v -> {
                        playSound("click");
                        selector(8, "8", getString(R.string.avatar_8_title), getString(R.string.avatar_8_description),500);
                    });
                }

                if(documentSnapshot.get("9") != null){
                    tv9.setText(getString(R.string.text_if_owned));
                    cv9.setOnClickListener(v -> {
                        playSound("click");
                        owned("9");
                    });
                }else{
                    cv9.setOnClickListener(v -> {
                        playSound("click");
                        selector(9, "9", getString(R.string.avatar_9_title), getString(R.string.avatar_9_description),500);
                    });
                }

                if(documentSnapshot.get("10") != null){
                    tv10.setText(getString(R.string.text_if_owned));
                    cv10.setOnClickListener(v -> {
                        playSound("click");
                        owned("10");
                    });
                }else{
                    cv10.setOnClickListener(v -> {
                        playSound("click");
                        selector(10, "10", getString(R.string.avatar_10_title), getString(R.string.avatar_10_description),1000);
                    });
                }

                if(documentSnapshot.get("11") != null){
                    tv11.setText(getString(R.string.text_if_owned));
                    cv11.setOnClickListener(v -> {
                        playSound("click");
                        owned("11");
                    });
                }else{
                    cv11.setOnClickListener(v -> {
                        playSound("click");
                        selector(11, "11", getString(R.string.avatar_11_title), getString(R.string.avatar_11_description),1200);
                    });
                }

                if(documentSnapshot.get("12") != null){
                    tv12.setText(getString(R.string.text_if_owned));
                    cv12.setOnClickListener(v -> {
                        playSound("click");
                        owned("12");
                    });
                }else{
                    cv12.setOnClickListener(v -> {
                        playSound("click");
                        selector(12, "12", getString(R.string.avatar_12_title), getString(R.string.avatar_12_description),1300);
                    });
                }

                if(documentSnapshot.get("13") != null){
                    tv13.setText(getString(R.string.text_if_owned));
                    cv13.setOnClickListener(v -> {
                        playSound("click");
                        owned("13");
                    });
                }else{
                    cv13.setOnClickListener(v -> {
                        playSound("click");
                        selector(13, "13", getString(R.string.avatar_13_title), getString(R.string.avatar_13_description),1400);
                    });
                }

                if(documentSnapshot.get("14") != null){
                    tv14.setText(getString(R.string.text_if_owned));
                    cv14.setOnClickListener(v -> {
                        playSound("click");
                        owned("14");
                    });
                }else{
                    cv14.setOnClickListener(v -> {
                        playSound("click");
                        selector(14, "14", getString(R.string.avatar_14_title), getString(R.string.avatar_14_description),1500);
                    });

                }

                if(documentSnapshot.get("15") != null){
                    tv15.setText(getString(R.string.text_if_owned));
                    cv15.setOnClickListener(v -> {
                        playSound("click");
                        owned("15");
                    });
                }else{
                    cv15.setOnClickListener(v -> {
                        playSound("click");
                        selector(15, "15", getString(R.string.avatar_15_title), getString(R.string.avatar_15_description),1500);
                    });

                }

                if(documentSnapshot.get("16") != null){
                    tv16.setText(getString(R.string.text_if_owned));
                    cv16.setOnClickListener(v -> {
                        playSound("click");
                        owned("16");
                    });
                }else{
                    cv16.setOnClickListener(v -> {
                        playSound("click");
                        selector(16, "16", getString(R.string.avatar_16_title), getString(R.string.avatar_16_description),1700);
                    });

                }

                if(documentSnapshot.get("17") != null){
                    tv17.setText(getString(R.string.text_if_owned));
                    cv17.setOnClickListener(v -> {
                        playSound("click");
                        owned("17");
                    });
                }else{
                    cv17.setOnClickListener(v -> {
                        playSound("click");
                        selector(17, "17", getString(R.string.avatar_17_title), getString(R.string.avatar_17_description),1700);
                    });
                }

                if(documentSnapshot.get("18") != null){
                    tv18.setText(getString(R.string.text_if_owned));
                    cv18.setOnClickListener(v -> {
                        playSound("click");
                        owned("18");
                    });
                }else{
                    cv18.setOnClickListener(v -> {
                        playSound("click");
                        selector(18, "18", getString(R.string.avatar_18_title), getString(R.string.avatar_18_description),1700);
                    });
                }

                if(documentSnapshot.get("19") != null){
                    tv19.setText(getString(R.string.text_if_owned));
                    cv19.setOnClickListener(v -> {
                        playSound("click");
                        owned("19");
                    });
                }else{
                    cv19.setOnClickListener(v -> {
                        playSound("click");
                        selector(19, "19", getString(R.string.avatar_19_title), getString(R.string.avatar_19_description),1900);
                    });

                }

                if(documentSnapshot.get("20") != null){
                    tv20.setText(getString(R.string.text_if_owned));
                    cv20.setOnClickListener(v -> {
                        playSound("click");
                        owned("20");
                    });
                }else{
                    cv20.setOnClickListener(v -> {
                        playSound("click");
                        selector(20, "20", getString(R.string.avatar_20_title), getString(R.string.avatar_20_description),1900);
                    });
                }

                if(documentSnapshot.get("21") != null){
                    tv21.setText(getString(R.string.text_if_owned));
                    cv21.setOnClickListener(v -> {
                        playSound("click");
                        owned("21");
                    });
                }else{
                    cv21.setOnClickListener(v -> {
                        playSound("click");
                        selector(20, "21", getString(R.string.avatar_21_title), getString(R.string.avatar_21_description),2200);
                    });
                }

                if(documentSnapshot.get("22") != null){
                    tv22.setText(getString(R.string.text_if_owned));
                    cv22.setOnClickListener(v -> {
                        playSound("click");
                        owned("22");
                    });
                }else{
                    cv22.setOnClickListener(v -> {
                        playSound("click");
                        selector(22, "22", getString(R.string.avatar_22_title), getString(R.string.avatar_22_description),2200);
                    });
                }

                if(documentSnapshot.get("23") != null){
                    tv23.setText(getString(R.string.text_if_owned));
                    cv23.setOnClickListener(v -> {
                        playSound("click");
                        owned("23");
                    });
                }else{
                    cv23.setOnClickListener(v -> {
                        playSound("click");
                        selector(23, "23", getString(R.string.avatar_23_title), getString(R.string.avatar_23_description),2500);
                    });
                }

                if(documentSnapshot.get("24") != null){
                    tv24.setText(getString(R.string.text_if_owned));
                    cv24.setOnClickListener(v -> {
                        playSound("click");
                        owned("24");
                    });
                }else{
                    cv24.setOnClickListener(v -> {
                        playSound("click");
                        selector(24, "24", getString(R.string.avatar_24_title), getString(R.string.avatar_24_description),2500);
                    });
                }

                if(documentSnapshot.get("25") != null){
                    tv25.setText(getString(R.string.text_if_owned));
                    cv25.setOnClickListener(v -> {
                        playSound("click");
                        owned("25");
                    });
                }else{
                    cv25.setOnClickListener(v -> {
                        playSound("click");
                        selector(25, "25", getString(R.string.avatar_25_title), getString(R.string.avatar_25_description),2500);
                    });
                }
            }else{
                System.out.println("ERROR AVATAR IS EMPTY");
                Map<String, Object> mappy = new HashMap<>();
                mappy.put("Avatar", 0);
                db.collection(staticUserID).document("PP").set(mappy).addOnSuccessListener(aVoid -> iniAvatars()).addOnFailureListener(e -> iniAvatars());

            }
        }).addOnFailureListener(e -> {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.error, Snackbar.LENGTH_LONG);
            snackbar.setTextColor(getResources().getColor(R.color.red));
            snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
            snackbar.show();
            finish();
        });
    }

    void selector(long avatarLong, String numberText, String title, String message, long cost){
        handler.post(() -> {
            if(Points > cost) {
                builder.setTitle(title);
                builder.setMessage(message + " " + Points);
                builder.setPositiveButton(R.string.yes,
                        (dialog, which) -> {
                            Points = Points - cost;
                            Map<String, Object> map = new HashMap<>();
                            map.put("Points", Points);
                            db.collection(staticUserID).document("Points").set(map).addOnSuccessListener(aVoid -> {
                                dialog.dismiss();
                                Map<String, Object> avatarMap = new HashMap<>();
                                _appPrefs.saveString("Avatar", numberText);
                                avatarMap.put("Avatar", avatarLong);
                                avatarMap.put(numberText, "1");
                                db.collection(staticUserID).document("PP").update(avatarMap).addOnSuccessListener(aVoid1 -> {
                                    AvatarActivity.this.overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                                    AvatarActivity.this.finish();
                                    AvatarActivity.this.overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                                }).addOnFailureListener(e -> {
                                    Points = Points + cost;
                                    selector(avatarLong, numberText,title,message,cost);
                                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.failed_connect, Snackbar.LENGTH_LONG);
                                    snackbar.setTextColor(getResources().getColor(R.color.red));
                                    snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                                    snackbar.show();
                                });
                            }).addOnFailureListener(e -> {
                                Points = Points + cost;
                                selector(avatarLong, numberText,title,message,cost);
                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.failed_connect, Snackbar.LENGTH_LONG);
                                snackbar.setTextColor(getResources().getColor(R.color.red));
                                snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                                snackbar.show();
                            });
                        });
                builder.setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss());
                AlertDialog dialog = builder.create();
                dialog.show();
            }else{
                builder.setTitle(getString(R.string.watch_ad_video_title));
                builder.setMessage(getString(R.string.watch_ad_video_message ) + " " + Points);
                builder.setPositiveButton(R.string.yes,
                        (dialog, which) -> {
                            if (mRewardedAd != null) {
                                Activity activityContext = AvatarActivity.this;
                                mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                                    @Override
                                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                        // Handle the reward.
                                        Log.d(TAG, "The user earned the reward.");
                                        rewardAmount = rewardItem.getAmount();
                                        rewardType = rewardItem.getType();
                                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), getString(R.string.your_account_has_been_credited) + " (" + rewardAmount + ")", Snackbar.LENGTH_LONG);
                                        snackbar.setTextColor(getResources().getColor(R.color.green_text));
                                        snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                                        snackbar.show();
                                        Points = Points + rewardAmount;
                                        Map<String, Object> map = new HashMap<>();
                                        map.put("Points", Points);
                                        db.collection(staticUserID).document("Points").set(map).addOnSuccessListener(aVoid -> {
                                            dialog.dismiss();
                                        }).addOnFailureListener(e -> {
                                            Points = Points + cost;
                                            Snackbar snackbar1 = Snackbar.make(findViewById(android.R.id.content), R.string.failed_connect, Snackbar.LENGTH_LONG);
                                            snackbar1.setTextColor(getResources().getColor(R.color.red));
                                            snackbar1.setBackgroundTint(getResources().getColor(R.color.snackback));
                                            snackbar1.show();
                                        });
                                    }
                                });
                            } else {
                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), getString(R.string.the_ad_isnt_ready_yet_please_retry), Snackbar.LENGTH_LONG);
                                snackbar.setTextColor(getResources().getColor(R.color.green_text));
                                snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                                snackbar.show();

                                Log.d(TAG, "The rewarded ad wasn't ready yet.");
                            }
                        }).setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss());
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    void owned( String textNumber){
        builder.setTitle(getString(R.string.select_this_avatar));
        builder.setMessage(getString(R.string.do_you_want_to_select ));
        builder.setPositiveButton(R.string.yes,
                (dialog, which) -> {
                    Map<String, Object> avatarMap = new HashMap<>();
                    _appPrefs.saveString("Avatar", textNumber);
                    long avatarLong = Integer.parseInt(textNumber);
                    avatarMap.put("Avatar", avatarLong);
                    db.collection(staticUserID).document("PP").update(avatarMap).addOnSuccessListener(aVoid1 -> {
                        AvatarActivity.this.overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                        AvatarActivity.this.finish();
                        AvatarActivity.this.overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                    }).addOnFailureListener(e -> {
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.failed_connect, Snackbar.LENGTH_LONG);
                        snackbar.setTextColor(getResources().getColor(R.color.red));
                        snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                        snackbar.show();
                    });
                    finish();
                }).setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}