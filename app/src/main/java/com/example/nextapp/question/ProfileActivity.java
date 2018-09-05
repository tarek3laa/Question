package com.example.nextapp.question;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.nextapp.question.Data.User;

public class ProfileActivity extends AppCompatActivity {

   private TextView name,email,rank,score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=(TextView)findViewById(R.id.tv_pa_name);
        email=(TextView)findViewById(R.id.tv_pa_email);

        rank=(TextView)findViewById(R.id.tv_pa_rank);
        score=(TextView)findViewById(R.id.tv_pa_score);

        name.setText(User.getName());
        email.setText(User.getEmail());
        rank.setText(String.valueOf(User.getRank()));
        score.setText(String.valueOf(User.getScore()));

    }






}
