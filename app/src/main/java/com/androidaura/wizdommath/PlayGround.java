package com.androidaura.wizdommath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PlayGround extends AppCompatActivity {
    ProgressBar progressBar;
    CountDownTimer countDownTimer;
    private long timeleft;
    TextView tvQn,tvScore;
    int score,bonus;
    EditText etAns;
    ImageView ivNext,ivRes;
    private int expectedAnswer;
    private int val;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_ground);
        getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        progressBar=findViewById(R.id.progressBar);
        final MediaPlayer crct= MediaPlayer.create(this, R.raw.correctsound);
        final MediaPlayer wrng = MediaPlayer.create(this, R.raw.wrongsound);
        timeleft=120000;
        ivRes=findViewById(R.id.ivRes);
        ivRes.setVisibility(View.GONE);
        ivNext=findViewById(R.id.ivNext);
        ivNext.setVisibility(View.GONE);
        etAns=findViewById(R.id.etAns);
        etAns.setVisibility(View.GONE);
        tvQn=findViewById(R.id.tvQn);
        tvScore=findViewById(R.id.tvScore);
        expectedAnswer=0;
        delaystart();
        val=getIntent().getIntExtra("level",0);
        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etAns.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(PlayGround.this,"Please Enter the answer!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String ans=etAns.getText().toString().trim();
                    int answer=Integer.parseInt(ans);
                    if(answer==expectedAnswer)
                    {

                        crct.start();
                        ivRes.setImageResource(R.drawable.correct);
                        ivRes.setVisibility(View.VISIBLE);
                        score+=10;
                        //bonus+=10000;
                        //incomplete update bonus timing
                        tvScore.setText("Score : "+score);
                        //incomplete time delay to show next qn for(long i=0;i<1000000;++i);
                        //incomplete sound effects on right and wrong answer
                        etAns.setText(null);
                        showQns();
                    }
                    else {
                        wrng.start();
                        ivRes.setImageResource(R.drawable.incorrect);
                        ivRes.setVisibility(View.VISIBLE);
                        etAns.setText(null);
                        //bonus-=2000;
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countDownTimer.cancel();
    }

    public void countdown(){
        final MediaPlayer mp =MediaPlayer.create(this,R.raw.clocktick);
        countDownTimer=new CountDownTimer(120000,100) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleft=millisUntilFinished;
                int progr=(int) ((timeleft/120000.0)*100);
                if(progr<30 && progr>=10)
                {
                    progressBar.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
                }
                else if(progr<10)
                {
                    progressBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                    mp.start();
                }
                else{
                    progressBar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                }
                progressBar.setProgress(progr);
            }

            @Override
            public void onFinish() {
                //incomplete on finish update score to shared preferences and display score activity
                //set playerunlocked=true depending on the val from indent
                Intent intent = new Intent(PlayGround.this,com.androidaura.wizdommath.ScoreCard.class);
                intent.putExtra("cscore",score);
                intent.putExtra("level",val);
                startActivity(intent);
            }
        }.start();
    }
    public void delaystart(){
        countDownTimer=new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleft=millisUntilFinished;
                int sec=(int) (timeleft/1000)+1;
                tvQn.setText(Integer.toString(sec));
            }

            @Override
            public void onFinish() {
                countdown();
                etAns.setVisibility(View.VISIBLE);
                ivNext.setVisibility(View.VISIBLE);
                showQns();
            }
        }.start();
    }

public void showQns(){

    //incomplete show qn depending on level and operators using random
    //tvQn.setText(Html.fromHtml("5x<sup><small>2</small></sup>"));

    Random random = new Random();
    int randrange=0,randnum,a,b;
    String opr = new String("");
    String qn=new String("");
    if(val==1 || val==2){
        randrange=4;
    }
    else if(val==3){
        randrange=6;
    }
    else{
        randrange=7;
    }
    randnum=random.nextInt(randrange)+1;
    switch (randnum)
    {
        case 1:opr="+";
        break;
        case 2:opr="-";
        break;
        case 3:opr="x";
        break;
        case 4:opr="/";
        break;
        case 5:opr="^";
        break;
        case 6:opr="√";
        break;
        case 7:opr="∛";
        break;
        default:opr="+";
        break;
    }
    switch(opr)
    {
        case "+": switch(val)
        {
            case 1: {
                a=random.nextInt(50-1)+2;
                b=random.nextInt(50-1)+2;
                expectedAnswer=a+b;
                qn=Integer.toString(a)+" "+opr+" "+Integer.toString(b);
            }
            break;
            case 2: {
                a=random.nextInt(500-50+1)+50;
                b=random.nextInt(500-50+1)+50;
                expectedAnswer=a+b;
                qn=Integer.toString(a)+" "+opr+" "+Integer.toString(b);
            }
            break;
            case 3: {
                a=random.nextInt(3000-1000+1)+1000;
                b=random.nextInt(3000-1000+1)+1000;
                expectedAnswer=a+b;
                qn=Integer.toString(a)+" "+opr+" "+Integer.toString(b);
            }
            break;
            case 4: {
                a=random.nextInt(7000-3000+1)+3000;
                b=random.nextInt(7000-3000+1)+3000;
                expectedAnswer=a+b;
                qn=Integer.toString(a)+" "+opr+" "+Integer.toString(b);
            }
            break;
        }
        break;
        case "-": switch (val)
        {
            case 1: {
                a=random.nextInt(100-1)+2;
                b=random.nextInt(a-1)+2;
                expectedAnswer=a-b;
                qn=Integer.toString(a)+" "+opr+" "+Integer.toString(b);
            }
            break;
            case 2: {
                a=random.nextInt(500-200+1)+200;
                b=random.nextInt(a-40)+40;
                expectedAnswer=a-b;
                qn=Integer.toString(a)+" "+opr+" "+Integer.toString(b);
            }
            break;
            case 3: {
                a=random.nextInt(3000-1000+1)+1000;
                b=random.nextInt(a-500)+500;
                expectedAnswer=a-b;
                qn=Integer.toString(a)+" "+opr+" "+Integer.toString(b);
            }
            break;
            case 4: {
                a=random.nextInt(8000-3000+1)+3000;
                b=random.nextInt(a-1000)+1000;
                expectedAnswer=a-b;
                qn=Integer.toString(a)+" "+opr+" "+Integer.toString(b);
            }
            break;
        }
        break;
        case "x": switch (val)
        {
            case 1: {
                a=random.nextInt(12-2+1)+2;
                b=random.nextInt(12-2+1)+2;
                expectedAnswer=a*b;
                qn=Integer.toString(a)+" "+opr+" "+Integer.toString(b);
            }
            break;
            case 2: {
                a=random.nextInt(50-13+1)+13;
                b=random.nextInt(50-13+1)+13;
                expectedAnswer=a*b;
                qn=Integer.toString(a)+" "+opr+" "+Integer.toString(b);
            }
            break;
            case 3: {
                a=random.nextInt(120-50+1)+50;
                b=random.nextInt(120-50+1)+50;
                expectedAnswer=a*b;
                qn=Integer.toString(a)+" "+opr+" "+Integer.toString(b);
            }
            break;
            case 4: {
                a=random.nextInt(300-100+1)+100;
                b=random.nextInt(300-100+1)+100;
                expectedAnswer=a*b;
                qn=Integer.toString(a)+" "+opr+" "+Integer.toString(b);
            }
            break;
        }
        break;
        case "/": switch (val)
        {
            case 1: {
                a=random.nextInt(20-10+1)+10;
                b=random.nextInt(10-2+1)+2;
                expectedAnswer=a;
                qn=Integer.toString((a*b))+" "+opr+" "+Integer.toString(b);
            }
            break;
            case 2: {
                a=random.nextInt(50-20+1)+20;
                b=random.nextInt(20-5+1)+5;
                expectedAnswer=a;
                qn=Integer.toString((a*b))+" "+opr+" "+Integer.toString(b);
            }
            break;
            case 3: {
                a=random.nextInt(80-30+1)+30;
                b=random.nextInt(30-10+1)+10;
                expectedAnswer=a;
                qn=Integer.toString((a*b))+" "+opr+" "+Integer.toString(b);
            }
            break;
            case 4: {
                a=random.nextInt(100-50+1)+50;
                b=random.nextInt(50-20+1)+20;
                expectedAnswer=a;
                qn=Integer.toString((a*b))+" "+opr+" "+Integer.toString(b);
            }
            break;
        }
        break;
        case "^": switch (val)
        {
            case 3:{
                a=random.nextInt(50-2+1)+2;
                b=2;
                expectedAnswer=a*a;
                qn=Integer.toString(a)+"<sup><small>2</small></sup>";
            }
            break;
            case 4:{
                a=random.nextInt(50-2+1)+2;
                b=random.nextInt(3-2+1)+2;
                if (b==2){
                    expectedAnswer=a*a;
                }
                else {
                    expectedAnswer = a * a * a;
                }
                qn=Integer.toString(a)+"<sup><small>"+Integer.toString(b)+"</small></sup>";
            }
            break;
        }
        break;
        case "√":switch (val)
        {
            case 3:{
                a=random.nextInt(40-2+1)+2;
                b=a*a;
                expectedAnswer=a;
                qn=opr+Integer.toString(b);
            }
            break;
            case 4:{
                a=random.nextInt(90-30+1)+30;
                b=a*a;
                expectedAnswer=a;
                qn=opr+Integer.toString(b);
            }
            break;
        }
            break;
        case "∛":
        {
            a=random.nextInt(50-10+1)+10;
            b=a*a*a;
            expectedAnswer=a;
            qn=opr+Integer.toString(b);
        }
        break;
    }
    tvQn.setText(Html.fromHtml(qn));
}
}