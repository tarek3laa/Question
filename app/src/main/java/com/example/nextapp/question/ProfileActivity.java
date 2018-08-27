package com.example.nextapp.question;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.nextapp.question.Data.User;


public class ProfileActivity extends AppCompatActivity {

    TextView name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=(TextView)findViewById(R.id.tv_pa_name);
        email=(TextView)findViewById(R.id.tv_pa_email);



            name.setText(MainActivity.sharedPreferences.getString(User.NAME_KEY,""));
            email.setText(MainActivity.sharedPreferences.getString(User.EMAIL_KEY,""));









    }


}
