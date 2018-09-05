package com.example.nextapp.question;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nextapp.question.Data.User;
import com.example.nextapp.question.Data.Users;
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
                    DocumentReference UserReference0 = FirebaseFirestore.getInstance().collection(Users.collectionReference).document(userName);
                    UserReference0.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                Users user05 = documentSnapshot.toObject(Users.class);
                                if (user05.getPassword().equals(password)) {


                                    //user and password are correct
                                    User.setSignUp(true);
                                    User.setEmail(user05.getEmail());
                                    User.setName(user05.getName());
                                    User.setScore(user05.getScore());
                                    User.setUserName(user05.getUserName());


                                    MainActivity.putsharedPreferences(user05.getName(),User.NAME_KEY);
                                    MainActivity.putsharedPreferences(userName,User.USER_KEY);
                                    MainActivity.putsharedPreferences(user05.getEmail(),User.EMAIL_KEY);
                                    MainActivity.putsharedPreferences(user05.getScore(),User.SCORE_KEY);



                                    Intent intent = new Intent(SignUpActivity.this, ProfileActivity.class);
                                    startActivity(intent);



                                } else {

                                    Toast.makeText(SignUpActivity.this,"password incorrect ",Toast.LENGTH_LONG).show();
                                }


                            } else {


                                //user incorrect
                                Toast.makeText(SignUpActivity.this,"User Name incorrect ",Toast.LENGTH_LONG).show();


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
                    DocumentReference UserReference0 = FirebaseFirestore.getInstance().collection(Users.collectionReference).document(userName);
                    UserReference0.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                Toast.makeText(SignUpActivity.this, "The username already exists ", Toast.LENGTH_LONG).show();

                            }else {
                                Email = metEmail.getText().toString();
                                password = metPassword.getText().toString();
                                setData(name, userName, Email, password);
                                Intent intent = new Intent(SignUpActivity.this, ProfileActivity.class);
                                User.setSignUp(true);
                                startActivity(intent);
                            }

                        }
                    });



                }

            }
        });

    }


    public void setData(String name,String userName,String email,String password){
        DocumentReference UserReference;
        Users user;
        UserReference = FirebaseFirestore.getInstance().collection(Users.collectionReference).document(userName);
        user =new Users(name,email,userName,password,0);

        User.setSignUp(true);
        User.setEmail(user.getEmail());
        User.setName(user.getName());
        User.setScore(user.getScore());
        User.setUserName(user.getUserName());
        UserReference.set(user);



        MainActivity.putsharedPreferences(name,User.NAME_KEY);
        MainActivity.putsharedPreferences(userName,User.USER_KEY);
        MainActivity.putsharedPreferences(email,User.EMAIL_KEY);


    }
}
