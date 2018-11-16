package com.example.nextapp.question;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FristActivity extends AppCompatActivity {
int sec=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frist);

        sec*=1000;
        new CountDownTimer(sec,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent intent=new Intent(FristActivity.this,MainActivity.class);
                startActivity(intent);
            }
            }.start();
    }
}
