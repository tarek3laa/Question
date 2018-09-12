package com.example.nextapp.question;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nextapp.question.Data.User;
import com.example.nextapp.question.Data.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MainActivity extends AppCompatActivity {


     private CardView mProfile, mcvRank;
     private LinearLayout sport,generalInfo;

     TextView mtvRank;

    public static  SharedPreferences sharedPreferences;

    public static final String Intentkey="Question";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPreferences=getSharedPreferences(Users.FILE_NAME, Context.MODE_PRIVATE);


        mProfile=(CardView)findViewById(R.id.cv_profile);
        mcvRank =(CardView)findViewById(R.id.cv_ranking);
        mtvRank=(TextView)findViewById(R.id.tv_ranking);

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


        mcvRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RankingActivity.class));
            }
        });



        sport=(LinearLayout)findViewById(R.id.it_sport);
        generalInfo=(LinearLayout)findViewById(R.id.it_general_info);


        sport=(LinearLayout)findViewById(R.id.it_science);
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

       final String name = sharedPreferences.getString(User.NAME_KEY,"NULL"),Email = sharedPreferences.getString(User.EMAIL_KEY,"NULL"),password ="" ,userName=sharedPreferences.getString(User.USER_KEY,"");
       int score = sharedPreferences.getInt(User.SCORE_KEY,0);

       User.setUserName(userName);
       User.setName(name);
       User.setEmail(Email);
       User.setScore(score);



       if(User.isSignUp()){



           /**************************************************************************************************/

           final ArrayList<Users> users=new ArrayList<>();
         CollectionReference collectionReference = FirebaseFirestore.getInstance().collection(Users.collectionReference);


           collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
               @Override
               public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                   for (QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots){

                       Users user0=documentSnapshot.toObject(Users.class);

                       users.add(user0);

                   }
               }
           }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

               @Override
               public void onComplete(@NonNull Task<QuerySnapshot> task) {


                   Collections.sort(users, new Comparator<Users>() {
                       @Override
                       public int compare(Users user, Users t1) {
                           return Integer.valueOf(t1.getScore()).compareTo(user.getScore());
                       }
                   });

                   for (int i=0;i<users.size();i++){
                       if(users.get(i).getUserName().equals(userName)){
                           User.setRank(i+1);

                           break;
                       }
                   }
                   mtvRank.setText("# "+String.valueOf(User.getRank()));


               }
           });

           /************************************************************************/





       }

    }


    public static void putsharedPreferences(String s,String key){
        sharedPreferences.edit().putString(key,s).apply();
    }
    public static void putsharedPreferences(int i,String key){

        sharedPreferences.edit().putInt(key,i).apply();
    }
    public static void putsharedPreferences(boolean b,String key){

        sharedPreferences.edit().putBoolean(key,b).apply();
    }



}
