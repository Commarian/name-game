package com.commarian.namegamet3.HomeMade.Server;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.commarian.namegamet3.HomeMade.Classes.BackgroundClass;
import com.commarian.namegamet3.HomeMade.Classes.LocaleHelper;
import com.commarian.namegamet3.R;
import com.google.android.gms.ads.AdView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import static com.commarian.namegamet3.HomeMade.Classes.AnimHandler.animHandler;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.adRequest;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.animatedBackgrounds;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.custom1;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.custom2;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.custom3;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.custom4;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.firebaseServer;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.ir;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.lobbyName;
import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticAct;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.random_int_gen;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.random_long_gen;

public class CustomHostActivity extends AppCompatActivity {

    DatabaseReference rdb = FirebaseDatabase.getInstance(firebaseServer).getReference(lobbyName);

    EditText et1;
    EditText et2;
    EditText et3;
    EditText et4;
    TextView tvinfo;
    Button nextBtn;
    final Handler handler = new Handler();



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_host);
        staticAct = "CHA";
        Backgrounds();
        et1 = findViewById(R.id.customHostET1);
        et2 = findViewById(R.id.customHostET2);
        et3 = findViewById(R.id.customHostET3);
        et4 = findViewById(R.id.customHostET4);

        tvinfo = findViewById(R.id.customHostInfo);

        nextBtn = findViewById(R.id.customHostBtn);

        custom1 = "";
        custom2 = "";
        custom3 = "";
        custom4 = "";

        //Ads
        AdView adView = findViewById(R.id.adViewCH);

        adView.loadAd(adRequest);



        nextBtn.setOnClickListener(v -> {
            nextBtn.setEnabled(false);
            if(CheckLength(et1) && CheckLength(et2) && CheckLength(et3) && CheckLength(et4)){
                custom1 = et1.getText().toString();
                custom2 = et2.getText().toString();
                custom3 = et3.getText().toString();
                custom4 = et4.getText().toString();
                Intent i = new Intent(CustomHostActivity.this, ServerActivity1.class);
                handler.post(() -> {
                    Map<String, Object> mapp= new HashMap<>();
                    mapp.put("L", custom1);
                    mapp.put("M", custom2);
                    mapp.put("N", custom3);
                    mapp.put("O", custom4);
                    rdb.setValue(mapp).addOnSuccessListener(aVoid -> {
                        finish();
                        overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                        startActivity(i);
                        overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
                    }).addOnFailureListener(e -> {
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.failed_connect, Snackbar.LENGTH_LONG);
                        snackbar.setTextColor(getResources().getColor(R.color.red));
                        snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
                        snackbar.show();
                        nextBtn.setEnabled(true);
                    });
                });

            }
        });

    }
    boolean CheckLength(EditText et){
        if(et.getText().toString().isEmpty()){
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), getString(R.string.please_fill_subjects), Snackbar.LENGTH_LONG);
            snackbar.setTextColor(getResources().getColor(R.color.yellow_text));
            snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
            snackbar.show();
            nextBtn.setEnabled(true);
            return false;
        }else if(et.getText().toString().length() < 2){
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), getString(R.string.please_enter_longer_subjects), Snackbar.LENGTH_LONG);
            snackbar.setTextColor(getResources().getColor(R.color.red));
            snackbar.setBackgroundTint(getResources().getColor(R.color.snackback));
            snackbar.show();
            nextBtn.setEnabled(true);
            return false;
        }else{
            return true;
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
        bg_0 = findViewById(R.id.bg_0_custom_host);
        bg_1 = findViewById(R.id.bg_2_custom_host);
        bg_2 = findViewById(R.id.bg_3_custom_host);
        bg_3 = findViewById(R.id.bg_4_custom_host);
        bg_4 = findViewById(R.id.bg_6_custom_host);
        bg_5 = findViewById(R.id.bg_7_custom_host);
        bg_6 = findViewById(R.id.bg_8_custom_host);
        con = this;
        BackgroundClass.SetBackground(con, findViewById(R.id.custom_mv));
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
                    if(staticAct.equals("CHA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_0.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_0);
                        bghand0.postDelayed(bgrun0, bgdur);
                    }
                };
                bgrun1 = () -> {
                    if(staticAct.equals("CHA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_1.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_1);
                        bghand1.postDelayed(bgrun1, bgdur);
                    }
                };
                bgrun2 = () -> {
                    if(staticAct.equals("CHA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_2.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_2);
                        bghand2.postDelayed(bgrun2, bgdur);
                    }
                };
                bgrun3 = () -> {
                    if(staticAct.equals("CHA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_3.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_3);
                        bghand3.postDelayed(bgrun3, bgdur);
                    }
                };
                bgrun4 = () -> {
                    if(staticAct.equals("CHA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_4.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_4);
                        bghand4.postDelayed(bgrun4, bgdur);
                    }
                };
                bgrun5 = () -> {
                    if(staticAct.equals("CHA")){
                        bgdur = random_long_gen(30000, 100000);
                        bg_5.setImageResource(ir[random_int_gen(0, 8)]);
                        animHandler(con, bgdur, bg_5);
                        bghand5.postDelayed(bgrun5, bgdur);
                    }
                };
                bgrun6 = () -> {
                    if(staticAct.equals("CHA")){
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