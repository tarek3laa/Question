package com.example.nextapp.question;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nextapp.question.Data.User;
import com.example.nextapp.question.Data.Users;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class SignUpActivity extends AppCompatActivity  {

    EditText metName, metUser, metEmail, metPassword;

    Button mbtSign, mbtHaveaccount ;
    LinearLayout mlyName, mlyUser;
    TextView mtvsign ;
    boolean haveAccount = false;
    private AdView mAdView ;



    private String name, Email, password, userName ;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up) ;

        actionBar = this.getSupportActionBar() ;
        if(actionBar!=null)actionBar.setDisplayHomeAsUpEnabled(true);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        //ad mob
        MobileAds.initialize(this, User.APP_ID) ;
        mAdView = findViewById(R.id.adView) ;
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest) ;



        metName = (EditText) findViewById(R.id.et_name) ;
        metUser = (EditText) findViewById(R.id.et_user);
        metEmail = (EditText) findViewById(R.id.et_email) ;
        metPassword = (EditText) findViewById(R.id.et_password) ;
        mbtSign = (Button) findViewById(R.id.bt_sign_up) ;
        mbtHaveaccount = (Button) findViewById(R.id.bt_have_account) ;
        mlyName = (LinearLayout) findViewById(R.id.ly_name);
        mlyUser = (LinearLayout) findViewById(R.id.ly_user);
        mtvsign = (TextView) findViewById(R.id.tv_sign_or_sign_up) ;
        mbtHaveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mtvsign.setText("Sign In");
                mlyName.setVisibility(View.INVISIBLE) ;
                mlyUser.setVisibility(View.INVISIBLE) ;
                mbtSign.setText("Sign In");
                mbtHaveaccount.setVisibility(View.INVISIBLE) ;
                haveAccount = true;
            }
        });

        mbtSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (haveAccount) {

                    userName = metUser.getText().toString() ;
                    password = metPassword.getText().toString() ;
                    if (TextUtils.isEmpty(userName)){
                        Toast.makeText(SignUpActivity.this,"Username is required",Toast.LENGTH_LONG).show() ;

                    }if (TextUtils.isEmpty(password)){
                        Toast.makeText(SignUpActivity.this,"password is required",Toast.LENGTH_LONG).show() ;

                    }
                    if ((!TextUtils.isEmpty(userName))&&(!TextUtils.isEmpty(password))) {

                        /***********  ***************/
                        DocumentReference UserReference0 = FirebaseFirestore . getInstance().collection(Users.collectionReference).document(userName);

                        UserReference0.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    Users user05 = documentSnapshot.toObject(Users.class);
                                    if (user05.getPassword().equals(password)) {


                                        //user and password are correct
                                        User.setSignUp(true);
                                        User.setEmail(user05.getEmail());
                                        User.setName(user05.getName()) ;
                                        User.setScore(user05.getScore());
                                        User.setUserName(user05.getUserName()) ;

                                        MainActivity.putsharedPreferences(user05.getName(), User.NAME_KEY) ;
                                        MainActivity.putsharedPreferences(userName, User.USER_KEY) ;
                                        MainActivity.putsharedPreferences(user05.getEmail(), User.EMAIL_KEY) ;
                                        MainActivity.putsharedPreferences(user05.getScore(), User.SCORE_KEY) ;


                                        Intent intent = new Intent(SignUpActivity . this, ProfileActivity.class);
                                        startActivity(intent);


                                    } else  {

                                        Toast.makeText(SignUpActivity.this, "password incorrect ", Toast.LENGTH_LONG).show();
                                    }


                                } else  {


                                    //user incorrect
                                    Toast.makeText(SignUpActivity.this , "User Name incorrect ", Toast.LENGTH_LONG).show();


                                }

                            }
                        }).addOnFailureListener(new OnFailureListener()  {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignUpActivity.this, "try again", Toast.LENGTH_LONG).show();

                            }
                        });


                        /*************************/
                    }

                } else {

                    name = metName.getText().toString() ;
                    userName = metUser.getText().toString() ;
                    if (TextUtils.isEmpty(userName)) {
                        Toast.makeText(SignUpActivity . this, "Username is required", Toast.LENGTH_LONG).show();

                    }
                     else{
                        //if user already exists
                    DocumentReference UserReference0 = FirebaseFirestore.getInstance().collection(Users.collectionReference).document(userName);
                    UserReference0.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                Toast.makeText(SignUpActivity.this , "The username already exists ", Toast.LENGTH_LONG).show();

                            } else {
                                Email = metEmail.getText().toString() ;
                                password = metPassword.getText().toString() ;
                                if (TextUtils.isEmpty(name)&&TextUtils.isEmpty(Email)&&TextUtils.isEmpty(password))
                                    Toast.makeText(SignUpActivity.this, "There is an empty field", Toast.LENGTH_LONG).show();
                                else {
                                    setData(name, userName, Email, password) ;
                                    Intent intent = new Intent(SignUpActivity.this, ProfileActivity.class);
                                    User.setSignUp(true) ;
                                    startActivity(intent);
                                    finish();
                                }
                            }

                        }
                    })  ;
                }


                }

            }
        }) ;

    }


    public void setData(String name,String userName,String email,String password){
        DocumentReference UserReference;
        Users user;
        UserReference = FirebaseFirestore.getInstance().collection(Users.collectionReference).document(userName);
        user =new Users(name,email,userName,password,0);

        User.setSignUp(true);
        User.setEmail(user.getEmail());
        User.setName(user.getName());
        User.setScore(user.getScore()) ;
        User.setUserName(user.getUserName());
        UserReference.set(user) ;



        MainActivity.putsharedPreferences(name,User.NAME_KEY) ;
        MainActivity.putsharedPreferences(userName,User.USER_KEY) ;
        MainActivity.putsharedPreferences(email,User.EMAIL_KEY) ;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)  {
        switch (item.getItemId()) {
            case android.R.id.home :
                NavUtils.navigateUpFromSameTask(this) ;
        }
        return super.onOptionsItemSelected(item) ;
     }
}
