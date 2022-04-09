package com.androidaura.wizdommath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HighScore extends AppCompatActivity {
    TextView tvNoobscore,tvPlayerscore,tvProfscore,tvWizscore;
    String noobscore,playerscore,profscore,wizscore;
    Button btnTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        getSupportActionBar().hide();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);
        btnTitles=findViewById(R.id.btnTitles);
        btnTitles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(HighScore.this,com.androidaura.wizdommath.ScoreCards.class);
                startActivity(intent);
            }
        });
        tvNoobscore=findViewById(R.id.tvNoobScore);
        tvPlayerscore=findViewById(R.id.tvPlayerScore);
        tvProfscore=findViewById(R.id.tvProfScore);
        tvWizscore=findViewById(R.id.tvWizscore);
        SharedPreferences preferences=getSharedPreferences(ListPlay.FILENAME,MODE_PRIVATE);
        noobscore=preferences.getString("NoobScore","0");
        playerscore=preferences.getString("PlayerScore","0");
        profscore=preferences.getString("ProfScore","0");
        wizscore=preferences.getString("WizScore","0");
        tvNoobscore.setText(noobscore);
        tvPlayerscore.setText(playerscore);
        tvProfscore.setText(profscore);
        tvWizscore.setText(wizscore);
    }
}