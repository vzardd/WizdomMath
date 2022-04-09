package com.androidaura.wizdommath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreCards extends AppCompatActivity {
    CardView cvPlayer,cvProf,cvWizard;
    TextView tvPlayName,tvProfName,tvWizName;
    TextView tvPlayScore,tvProfScore,tvWizScore,tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_cards);
        getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        cvPlayer=findViewById(R.id.cvPlayerCard);
        cvProf=findViewById(R.id.cvProfCard);
        cvWizard=findViewById(R.id.cvWizCard);
        tvPlayName=findViewById(R.id.tvCardName2);
        tvProfName=findViewById(R.id.tvCardName);
        tvWizName=findViewById(R.id.tvCardName3);
        tvPlayScore=findViewById(R.id.tvCardScore2);
        tvEmpty=findViewById(R.id.tvEmpty);
        tvEmpty.setVisibility(View.GONE);
        tvProfScore=findViewById(R.id.tvCardScore);
        tvWizScore=findViewById(R.id.tvCardScore3);
        SharedPreferences pref = getSharedPreferences(ListPlay.FILENAME,MODE_PRIVATE);
        String name=pref.getString("username","");
        if(name.equals(""))
        {
            cvPlayer.setVisibility(View.GONE);
            cvProf.setVisibility(View.GONE);
            cvWizard.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.VISIBLE);
        }
        else
        {
            tvPlayName.setText("Username :  "+name);
            tvProfName.setText("Username :  "+name);
            tvWizName.setText("Username :  "+name);
            int playscore=Integer.parseInt(pref.getString("PlayerScore","0"));
            if(playscore<70)
            {
                cvPlayer.setVisibility(View.GONE);
                cvProf.setVisibility(View.GONE);
                cvWizard.setVisibility(View.GONE);
                tvEmpty.setVisibility(View.VISIBLE);
            }
            else
            {
                tvPlayScore.setText("High Score :  "+Integer.toString(playscore));
                int profscore=Integer.parseInt(pref.getString("ProfScore","0"));
                if(profscore<60)
                {
                    cvProf.setVisibility(View.GONE);
                    cvWizard.setVisibility(View.GONE);
                }
                else
                {
                    tvProfScore.setText("High Score :  "+Integer.toString(profscore));
                    int wizscore=Integer.parseInt(pref.getString("WizScore","0"));
                    if(wizscore<50)
                    {
                        cvWizard.setVisibility(View.GONE);
                    }
                    else
                    {
                        tvWizScore.setText("High Score :  "+Integer.toString(wizscore));
                    }
                }
            }
        }
    }
}