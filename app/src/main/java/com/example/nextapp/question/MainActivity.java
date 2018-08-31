package com.example.nextapp.question;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;

import com.example.nextapp.question.Data.User;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {


     private CardView mProfile,mRank;
     private LinearLayout sport,generalInfo;
    User user;
    public static  SharedPreferences sharedPreferences;
    CircleImageView circleImageView;
    public static final String Intentkey="Question";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPreferences=getSharedPreferences(User.FILE_NAME, Context.MODE_PRIVATE);


        mProfile=(CardView)findViewById(R.id.cv_profile);
        mRank=(CardView)findViewById(R.id.cv_ranking);
        mProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (User.isSignUp())

                     startActivity(new Intent(MainActivity.this, ProfileActivity.class));

                else {

                    startActivity(new Intent(MainActivity.this,SignUpActivity.class));

                }
            }
        });


        mRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RankingActivity.class));
            }
        });



        sport=(LinearLayout)findViewById(R.id.it_sport);
        generalInfo=(LinearLayout)findViewById(R.id.it_general_info);


        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this,QuestionActivity.class);
                intent.putExtra(Intentkey,"sport");
                startActivity(intent);
            }
        });


        generalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this,QuestionActivity.class);
                intent.putExtra(Intentkey,"general info");
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

       User.setSignUp(sharedPreferences.getBoolean(User.IS_SIGN_KEY,false));

       String name = sharedPreferences.getString(User.NAME_KEY,"NULL"),Email = sharedPreferences.getString(User.EMAIL_KEY,"NULL"),password ="" ;
       int score = sharedPreferences.getInt(User.SCORE_KEY,0),rank =sharedPreferences.getInt(User.RANK_KEY,-1);

       user=new User(name,Email,password,score);
      // user.setRank(rank);

    }



}
