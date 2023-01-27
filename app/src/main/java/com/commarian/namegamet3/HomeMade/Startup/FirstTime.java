package com.commarian.namegamet3.HomeMade.Startup;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.commarian.namegamet3.HomeMade.Classes.AppPreferences;
import com.commarian.namegamet3.HomeMade.Classes.LocaleHelper;
import com.commarian.namegamet3.R;

import java.util.concurrent.atomic.AtomicInteger;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.staticAct;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.langChanged;

public class FirstTime extends AppCompatActivity implements  AdapterView.OnItemSelectedListener{
    Button yes;
    Button no;
    Spinner dropDown;
    TextView TV;
    AppPreferences _appPrefs;
    Boolean changed = false;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);
        AtomicInteger pressedTime = new AtomicInteger();
        staticAct = "FT";
        yes = findViewById(R.id.yesFirstTimeBtn);
        no = findViewById(R.id.noFirstTimeBtn);
        TV = findViewById(R.id.first_time_tvc);
        _appPrefs = new AppPreferences(getApplicationContext());
        _appPrefs.saveString("First Time", "First");
        yes.setOnClickListener(v -> {
            if(pressedTime.get() == 1){
                langChanged = changed;
                overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
                finish();
                overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
            }else{
                TV.setText(R.string.language_first_time_mesg);
                dropDown.setVisibility(View.VISIBLE);
                no.setVisibility(View.GONE);
                yes.setText(R.string.done);
            }
            pressedTime.getAndIncrement();
        });
        no.setOnClickListener(v -> {
            langChanged = changed;
            overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
            finish();
            overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                langChanged = changed;
                overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
                finish();
                overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
            }
        };

        getOnBackPressedDispatcher().addCallback(this, callback);

        dropDown = findViewById(R.id.first_time_lang_drop);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lang_array, android.R.layout.simple_spinner_dropdown_item);
        dropDown.setAdapter(adapter);
        dropDown.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        //System.out.println("ONITEMSELECTED");
        if(!item.equals("")){
            String curLang = _appPrefs.getString("lang");
            if(item.equals("Afrikaans")){
                if(!curLang.equals(item)){
                    _appPrefs.saveString("lang", "Afrikaans");
                    changed = true;
                }
            }else{
                if(!curLang.equals(item)){
                    changed = true;
                    _appPrefs.saveString("lang", "English");
                }
            }

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}