package com.androidaura.wizdommath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreCard extends AppCompatActivity {
    TextView tvCurrentScore,tvStatus;
    TextView tvCardName,tvCardLevel,tvCardScore,tvCardStatus;
    String lvl,setScore,clevel;
    private int cscore,minscore,val;
    EditText etUsername;
    Button btnContinue;
    CardView cvScoreCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);
        getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        tvCardName=findViewById(R.id.tvCardName);
        tvCardLevel=findViewById(R.id.tvCardLevel);
        tvCardScore=findViewById(R.id.tvCardScore);
        tvCardStatus=findViewById(R.id.tvCardStatus);
        etUsername=findViewById(R.id.etUserName);
        etUsername.setVisibility(View.GONE);
        cvScoreCard=findViewById(R.id.cvScoreCard);
        btnContinue=findViewById(R.id.btnContinue);
        tvCurrentScore=findViewById(R.id.tvCurrentScore);
        lvl=new String();
        setScore=new String();
        clevel=new String();
        tvStatus=findViewById(R.id.tvQualify);
        cscore=getIntent().getIntExtra("cscore",0);
        val=getIntent().getIntExtra("level",1);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(val!=1) {
                    Intent intent = new Intent(ScoreCard.this, com.androidaura.wizdommath.MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    if(etUsername.getText().toString().trim().isEmpty())
                    {
                        Toast.makeText(ScoreCard.this,"Please Enter your name and Continue.",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String name=new String();
                        name=etUsername.getText().toString().trim();
                        SharedPreferences.Editor editor=getSharedPreferences(ListPlay.FILENAME,MODE_PRIVATE).edit();
                        editor.putString("username",name);
                        editor.commit();
                        Intent intent = new Intent(ScoreCard.this, com.androidaura.wizdommath.MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
        tvCurrentScore.setText(Integer.toString(cscore));
        switch (val)
        {
            case 1:minscore=140;lvl="playerunlocked";setScore="NoobScore";etUsername.setVisibility(View.VISIBLE);
            break;
            case 2:minscore=70;lvl="profunlocked";setScore="PlayerScore";clevel="Player";
            break;
            case 3:minscore=60;lvl="wizunlocked";setScore="ProfScore";clevel="Professor";
            break;
            case 4:minscore=50;lvl="done";setScore="WizScore";clevel="Wizard";
            break;
        }
        if(cscore>=minscore)
        {
            SharedPreferences pref = getSharedPreferences(ListPlay.FILENAME,MODE_PRIVATE);
            int xScore=Integer.parseInt(pref.getString(setScore,"0"));
            tvStatus.setText("Congratulations! You've cleared this level");
            if(val==1)
            {
                cvScoreCard.setVisibility(View.GONE);
            }
            else
            {
                String name = new String();
                name= pref.getString("username","");
                name=name.toUpperCase();

                String score = Integer.toString(((cscore>xScore)?cscore:xScore));
                String status=new String("Cleared");
                tvCardName.setText("Username :  "+name);
                tvCardLevel.setText("Level :  "+clevel);
                tvCardScore.setText("High Score :  "+score);
                tvCardStatus.setText("Status :  Cleared");
                cvScoreCard.setVisibility(View.VISIBLE);
            }
            if (lvl.equals("done"))
            {
                SharedPreferences.Editor editor= getSharedPreferences(ListPlay.FILENAME,MODE_PRIVATE).edit();
                if(cscore>xScore) {
                    editor.putString(setScore, Integer.toString(cscore));
                }
                editor.commit();
                Toast.makeText(ScoreCard.this,"Congrats, You've won the Wizdom Math Title!",Toast.LENGTH_SHORT).show();
            }
            else
            {
                SharedPreferences.Editor editor= getSharedPreferences(ListPlay.FILENAME,MODE_PRIVATE).edit();
                editor.putBoolean(lvl,true);
                if(cscore>xScore) {
                    editor.putString(setScore, Integer.toString(cscore));
                }
                editor.commit();
            }

        }
        else
        {
            cvScoreCard.setVisibility(View.GONE);
            SharedPreferences pref = getSharedPreferences(ListPlay.FILENAME,MODE_PRIVATE);
            int xScore=Integer.parseInt(pref.getString(setScore,"0"));
            tvStatus.setText("Better luck next time!");
            SharedPreferences.Editor editor= getSharedPreferences(ListPlay.FILENAME,MODE_PRIVATE).edit();
            if(cscore>xScore) {
                editor.putString(setScore, Integer.toString(cscore));
            }
            editor.commit();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(val==1)
        {
            SharedPreferences.Editor editor = getSharedPreferences(ListPlay.FILENAME,MODE_PRIVATE).edit();
            editor.putBoolean("playerunlocked",false);
            editor.commit();
        }
    }
}