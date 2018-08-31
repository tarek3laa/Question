package com.example.nextapp.question;

import android.accounts.Account;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class SignUpActivity extends AppCompatActivity {

    EditText metName, metUser, metEmail, metPassword;

    Button mbtSign, mbtHaveaccount;
    LinearLayout mlyName, mlyUser;
    TextView mtvsign;
    boolean haveAccount = false;

    DataBase dataBase;

    private String name, Email, password, userName;

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
        dataBase = new DataBase();
        mtvsign = (TextView) findViewById(R.id.tv_sign_or_sign_up);
        mbtHaveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mtvsign.setText("Sign In");
                mlyName.setVisibility(View.INVISIBLE);
                mlyUser.setVisibility(View.INVISIBLE);
                mbtSign.setText("Sign In");
                mbtHaveaccount.setVisibility(View.INVISIBLE);
                haveAccount = true;
            }
        });

        mbtSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (haveAccount) {
                    userName = metUser.getText().toString();
                    password = metPassword.getText().toString();

                    /***********  ***************/
                    DocumentReference UserReference0 = FirebaseFirestore.getInstance().collection(User.collectionReference).document(userName);
                    UserReference0.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                User user05 = documentSnapshot.toObject(User.class);
                                if (user05.getPassword().equals(password)) {
                                    //user and password are correct

                                } else {

                                    // password incorrect
                                }


                            } else {
                                //user incorrect
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUpActivity.this, "try again", Toast.LENGTH_LONG).show();

                        }
                    });


                    /*************************/


                } else {
                    name = metName.getText().toString();
                    userName = metUser.getText().toString();
                    Email = metEmail.getText().toString();
                    password = metPassword.getText().toString();
                    dataBase.setData(name, userName, Email, password);


                    //  user0.sharedPreferences.edit().putString(User.EMAIL_KEY, metEmail.getText().toString());
                    //  user0.sharedPreferences.edit().putString(User.NAME_KEY, metName.getText().toString());
                    // user0.sharedPreferences.edit().putString(User.USER_KEY, metUser.getText().toString());
                    //user0.sharedPreferences.edit().putInt(User.SCORE_KEY, 0);
                    //user0.sharedPreferences.edit().putBoolean(User.IS_SIGN_KEY, true);
                    Intent intent = new Intent(SignUpActivity.this, ProfileActivity.class);
                    intent.putExtra(User.NAME_KEY, name);
                    intent.putExtra(User.EMAIL_KEY, Email);

                    User.setSignUp(true);
                    startActivity(intent);


                }

            }
        });

    }


}
