package com.example.nextapp.question;

import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import java.util.Locale;

public class QuestionActivity extends AppCompatActivity {


    private static final long START_TIME = 15000;
    private long mTime = START_TIME;

    private TextView timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        timer = (TextView) findViewById(R.id.tv_timer);
        //ActionBar actionBar=getSupportActionBar();
       // actionBar.setTitle(Html.fromHtml("<font  color='#000000' > Sport</font>"));
       startTimer();


    }

    private void startTimer() {
        mTime = START_TIME;
        timer();

    }

    private void timer() {

         new CountDownTimer(mTime, 1000) {
            @Override
            public void onTick(long l) {
                mTime = l;

                int m = (int) (mTime/1000) / 60;
                int s = (int) (mTime/1000) % 60;
                timer.setText(String.format(Locale.getDefault(), "0%2d:%02d", m, s));
            }

            @Override
            public void onFinish() {



            }
        }.start();
    }

}

