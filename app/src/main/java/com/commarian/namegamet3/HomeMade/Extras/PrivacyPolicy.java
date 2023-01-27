package com.commarian.namegamet3.HomeMade.Extras;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import com.commarian.namegamet3.HomeMade.Classes.LocaleHelper;
import com.commarian.namegamet3.R;

import androidx.appcompat.app.AppCompatActivity;

public class PrivacyPolicy extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        Button readMore = findViewById(R.id.readMorePrivacy);

        readMore.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://docs.google.com/document/d/16iYwk_utlbySlw3dpgK7oCkUmwGveZk7jD0BFeMWSig/edit?usp=sharing");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });


    }
}