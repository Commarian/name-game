package com.commarian.namegamet3.HomeMade.Extras;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.commarian.namegamet3.HomeMade.Classes.LocaleHelper;
import com.commarian.namegamet3.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.playSound;
import static com.commarian.namegamet3.HomeMade.Startup.initialize_app.static_help;

public class HelpActivity extends AppCompatActivity {
    TextView helpTV;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        AppCompatImageView back = findViewById(R.id.backDiff);

        helpTV = findViewById(R.id.helpTV);
        back.setEnabled(true);



        back.setOnClickListener(v -> {
            playSound("click");
            back.setEnabled(false);
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
            finish();
            overridePendingTransition(R.anim.enter_act, R.anim.exit_act);
        });

        switch (static_help) {
            case "PublicPrivate":
                helpTV.setText(R.string.public_private_help);
                break;
            case "JoinCreateActivity":
                helpTV.setText(R.string.join_create_help);
                break;
            case "CreateActivityPrivate":
                helpTV.setText(R.string.create_game_pri_help);
                break;
            case "CreateActivityPublic":
                helpTV.setText(R.string.create_game_pub_help);
                break;
            case "CreateActivitySolo":
                helpTV.setText(R.string.create_game_solo_help);
                break;
            case "JoinActivityPrivate":
                helpTV.setText(R.string.join_game_pri_help);
                break;
            case "JoinActivityPublic":
                helpTV.setText(R.string.join_game_pub_help);
                break;
            case "PublicDifficultyHelp":
                helpTV.setText(R.string.public_difficulty_help);
                break;
            case "PrivateDifficultyHelp":
                helpTV.setText(R.string.private_difficulty_help);
                break;
            case "SoloDifficultyHelp":
                helpTV.setText(R.string.solo_difficulty_help);
                break;
            case "SettingsActivity":
                helpTV.setText(R.string.how_to_play_help);
                break;
            default:
                helpTV.setText("");
        }


    }
}