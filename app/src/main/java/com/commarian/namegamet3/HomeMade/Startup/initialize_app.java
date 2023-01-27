package com.commarian.namegamet3.HomeMade.Startup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.commarian.namegamet3.HomeMade.Classes.AppPreferences;
import com.commarian.namegamet3.HomeMade.Classes.LocaleHelper;
import com.commarian.namegamet3.R;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class initialize_app extends AppCompatActivity {
    static Handler handler = new Handler();

    AppPreferences _appPrefs;

    public static SoundPool soundPool;
    public static int btnclick0,btnclick1,btnclick2, msg_fail, msg_success, start, stop;
    public static String static_help;

    public static int red;
    public static int green;
    public static int purple;

    public static boolean muted = false;
    public static boolean fragMute = false;
    static AudioManager am;

    public static boolean langChanged = false;

    Resources resources;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_ini);
        overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
        _appPrefs = new AppPreferences(getApplicationContext());
        static_help = "";
        red = getResources().getColor(R.color.red);
        green = getResources().getColor(R.color.green);
        purple = getResources().getColor(R.color.purple_500);
        am = (AudioManager) getSystemService(AUDIO_SERVICE);

        AudioAttributes
                audioAttributes
                = new AudioAttributes
                .Builder()
                .setUsage(
                        AudioAttributes
                                .USAGE_MEDIA)
                .setContentType(
                        AudioAttributes
                                .CONTENT_TYPE_MUSIC)
                .build();
        soundPool
                = new SoundPool
                .Builder()
                .setMaxStreams(7)
                .setAudioAttributes(
                        audioAttributes)
                .build();
                btnclick0 = soundPool.load(getApplicationContext(), R.raw.clicka0, 2);
                btnclick1 = soundPool.load(getApplicationContext(), R.raw.clicka1, 2);
                btnclick2 = soundPool.load(getApplicationContext(), R.raw.clicka2, 2);
                msg_fail = soundPool.load(getApplicationContext(), R.raw.msg_fail, 1);
                msg_success = soundPool.load(getApplicationContext(), R.raw.msg_success, 1);
                start = soundPool.load(getApplicationContext(), R.raw.start, 1);
                stop = soundPool.load(getApplicationContext(), R.raw.stop, 1);

        soundPool.autoPause();


        if(_appPrefs.getString("sound") != null){
            String appSound = _appPrefs.getString("sound");
            muted = appSound.equals("OFF");
        }else{
            muted = false;
        }
        try {
            System.out.println("Default locale before setting: " + Locale.getDefault());
            Context context;
            if(_appPrefs.getString("lang").equals("Afrikaans")){

                context = LocaleHelper.setLocale(initialize_app.this, "af");
                resources = context.getResources();
            }else if(_appPrefs.getString("lang").equals("English")){

                context = LocaleHelper.setLocale(initialize_app.this, "en");
                resources = context.getResources();
            }
            System.out.println("Default locale after setting: " + Locale.getDefault());

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            Intent intento = new Intent(initialize_app.this, MainActivity.class);
            finish();
            overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
            startActivity(intento);
            overridePendingTransition(R.anim.enter_act_quick, R.anim.exit_act_quick);
        }
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }


    public static void hideKeyboardFrom(Context context, View view) {
        handler.post(() -> {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        });

    }
    public static void playSound(String sound)
    {
        try{
            if(!muted){
                float music_volume_level = am.getStreamVolume(AudioManager.STREAM_MUSIC);
                float music_volume_max = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                float vol = music_volume_level/music_volume_max;
                //System.out.println("VOL = " + vol);
                switch (sound) {
                    case "click":
                        int r = random_int_gen(1,2);
                        if(r == 0){
                            soundPool.play(btnclick0, vol, vol, 2, 0, 1);
                        }else if (r == 1){
                            soundPool.play(btnclick1, vol, vol, 2, 0, 1);
                        }else {
                            soundPool.play(btnclick2, vol, vol, 2, 0, 1);
                        }
                        break;
                    case "msgFail":
                        soundPool.play(
                                msg_fail, vol, vol, 0, 0, 1);
                        break;
                    case "msgSuccess":
                        soundPool.play(
                                msg_success, vol, vol, 0, 0, 1);
                        break;
                    case "stop":
                        soundPool.play(
                                stop, vol, vol, 0, 0, 1);
                        break;
                    case "start":
                        soundPool.play(
                                start, vol, vol, 0, 0, 1);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            muted = true;
        }
    }
    public static int random_int_gen(int Min, int Max)
    {
        return (int) (Math.random()*(Max + 1 - Min)) + Min;
    }

    public static long random_long_gen(long Min, long Max)
    {
        return (long) (Math.random()*(Max + 1 - Min)) + Min;
    }

}