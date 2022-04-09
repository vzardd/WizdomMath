package com.androidaura.wizdommath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ListPlay extends AppCompatActivity {
    CardView btnNoob,btnPlayer,btnProf,btnWiz;
    ImageView ivPlayerlock,ivProflock,ivWizlock;
    public static String FILENAME="com.androidaura.wizdommath.Cache";
    Boolean playerunlocked,profunlocked,wizunlocked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_play);
        getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        ivPlayerlock=findViewById(R.id.ivPlayerlock);
        ivProflock=findViewById(R.id.ivProflock);
        ivWizlock=findViewById(R.id.ivWizlock);
        SharedPreferences pref=getSharedPreferences(FILENAME,MODE_PRIVATE);
        playerunlocked=pref.getBoolean("playerunlocked",false);
        profunlocked=pref.getBoolean("profunlocked",false);
        wizunlocked=pref.getBoolean("wizunlocked",false);
        if(playerunlocked)
        {
            ivPlayerlock.setVisibility(View.GONE);
        }
        if(profunlocked)
        {
            ivProflock.setVisibility(View.GONE);
        }
        if(wizunlocked)
        {
            ivWizlock.setVisibility(View.GONE);
        }
        btnNoob=findViewById(R.id.btnNoob);
        btnPlayer=findViewById(R.id.btnPlayer);
        btnProf=findViewById(R.id.btnProf);
        btnWiz=findViewById(R.id.btnWiz);
        btnNoob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ListPlay.this,com.androidaura.wizdommath.PlayGround.class);
                intent.putExtra("level",1);
                startActivity(intent);
            }
        });
        btnPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!playerunlocked)
                {
                    Toast.makeText(ListPlay.this,"Qualify Previous Mode to Unlock",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent=new Intent(ListPlay.this,com.androidaura.wizdommath.PlayGround.class);
                    intent.putExtra("level",2);
                    startActivity(intent);
                }
            }
        });
        btnProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!profunlocked)
                {
                    Toast.makeText(ListPlay.this,"Qualify Previous Mode to Unlock",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent=new Intent(ListPlay.this,com.androidaura.wizdommath.PlayGround.class);
                    intent.putExtra("level",3);
                    startActivity(intent);
                }
            }
        });
        btnWiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!wizunlocked)
                {
                    Toast.makeText(ListPlay.this,"Qualify Previous Mode to Unlock",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent=new Intent(ListPlay.this,com.androidaura.wizdommath.PlayGround.class);
                    intent.putExtra("level",4);
                    startActivity(intent);
                }
            }
        });

    }
}