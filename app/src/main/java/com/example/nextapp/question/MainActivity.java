package com.example.nextapp.question;

import android.content.Intent;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;



public class MainActivity extends AppCompatActivity {


   private CardView mProfile;
   private LinearLayout sport;
   public boolean isSignUp =false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProfile=(CardView)findViewById(R.id.cv_profile);

        mProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSignUp)startActivity(new Intent(MainActivity.this, ProfileActivity.class));

                else startActivity(new Intent(MainActivity.this,SignUpActivity.class));
            }
        });

        sport=(LinearLayout)findViewById(R.id.it_sport);
        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,QuestionActivity.class));
            }
        });


    }

}
