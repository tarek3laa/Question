package com.example.nextapp.question;

import android.accounts.Account;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nextapp.question.Data.DataBase;
import com.example.nextapp.question.Data.User;


public class SignUpActivity extends AppCompatActivity {

    EditText metName, metUser, metEmail, metPassword;

    Button mbtSign, mbtHaveaccount;
    LinearLayout mlyName, mlyUser;
    TextView mtvsign;
    boolean haveAccount = false;
    User user0, user;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        metName = (EditText) findViewById(R.id.et_name);
        metUser = (EditText) findViewById(R.id.et_user);
        metEmail = (EditText) findViewById(R.id.et_email);
        metPassword = (EditText) findViewById(R.id.et_password);
        mbtSign = (Button) findViewById(R.id.bt_sign_up);
        mbtHaveaccount = (Button) findViewById(R.id.bt_have_account);
        mlyName = (LinearLayout) findViewById(R.id.ly_name);
        mlyUser = (LinearLayout) findViewById(R.id.ly_user);
        mtvsign = (TextView) findViewById(R.id.tv_sign_or_sign_up);
        mbtHaveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBase = new DataBase();
                mtvsign.setText("Sign In");
                mlyName.setVisibility(View.INVISIBLE);
                mlyUser.setVisibility(View.INVISIBLE);
                mbtSign.setText("Sign In");
                mbtHaveaccount.setVisibility(View.INVISIBLE);
                haveAccount = true;
            }
        });
        user0 = new User(this);
        mbtSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (haveAccount) {
                    //get email and password
                    if (dataBase.getCurrUser(metUser.getText().toString()) != null) {
                        user = dataBase.getCurrUser(metUser.getText().toString());
                        if (metPassword.getText().toString().equals(user.getPassword())) {
                            //user0.sharedPreferences.edit().putBoolean(User.IS_SIGN_KEY, true);

                        } else {
                            Toast.makeText(SignUpActivity.this, "password incorruct", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(SignUpActivity.this, "user name not found , please sign up ", Toast.LENGTH_LONG).show();

                    }


                } else {
                    dataBase.setData(metName.getText().toString(), metUser.getText().toString(), metEmail.getText().toString(), metPassword.getText().toString());
                  //  user0.sharedPreferences.edit().putString(User.EMAIL_KEY, metEmail.getText().toString());
                  //  user0.sharedPreferences.edit().putString(User.NAME_KEY, metName.getText().toString());
                   // user0.sharedPreferences.edit().putString(User.USER_KEY, metUser.getText().toString());
                    //user0.sharedPreferences.edit().putInt(User.SCORE_KEY, 0);
                    //user0.sharedPreferences.edit().putBoolean(User.IS_SIGN_KEY, true);




                }
            }
        });


    }


}
