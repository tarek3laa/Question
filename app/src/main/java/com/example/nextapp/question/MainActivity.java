package com.example.nextapp.question;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nextapp.question.Data.User;
import com.example.nextapp.question.Data.Users;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {


     private CardView mProfile, mcvRank;
     private LinearLayout sport,generalInfo,religion  ;
     private LinearLayout science;
     private AdView mAdView;
     CircleImageView cr_vv;
     TextView mtvRank,mtvName;

     CircleImageView profileImage;


    public static  SharedPreferences sharedPreferences;

    public static final String Intentkey="Question";


    @Override
    protected void onCreate(final Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        //ad mob
        MobileAds.initialize(this, User.APP_ID) ;
        mAdView = findViewById(R.id.adView) ;
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mtvName=(TextView)findViewById(R.id.tv_name) ;
        profileImage=(CircleImageView)findViewById(R.id.ci_profile);
        sharedPreferences=getSharedPreferences(Users.FILE_NAME, Context.MODE_PRIVATE);


        mProfile=(CardView)findViewById(R.id.cv_profile);
        mcvRank =(CardView)findViewById(R.id.cv_ranking) ;
        mtvRank=(TextView)findViewById(R.id.tv_ranking) ;
        cr_vv=(CircleImageView)findViewById(R.id.cr_vv);


        cr_vv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,EmergancyActivity.class);
                startActivity(intent);

            }
        });

        mProfile.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view)  {

                if  (User.isSignUp())

                     startActivity(new Intent(MainActivity.this , ProfileActivity.class));

                else  {

                    startActivity(new Intent(MainActivity.this , SignUpActivity.class));

                 }
            }
        });


        mcvRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , RankingActivity.class)) ;
            }
        });



        sport=(LinearLayout)findViewById(R.id.it_sport) ;
        generalInfo=(LinearLayout)findViewById(R.id.it_general_info) ;
        science=(LinearLayout)findViewById(R.id.it_science) ;
        religion=(LinearLayout)findViewById(R.id.it_religion) ;

        sport.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view)  {

                Intent intent=new Intent(MainActivity.this,QuestionActivity.class);
                intent.putExtra(Intentkey,User.SPORT_QUESTION) ;
                startActivity(intent) ;
            }
        });


        generalInfo.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {



                   Intent intent=new Intent(MainActivity.this,QuestionActivity.class);
                   intent.putExtra(Intentkey,User.GI_QUESTION) ;
                   startActivity(intent) ;


            }
        });


        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                Intent intent=new Intent(MainActivity.this,QuestionActivity.class);
                intent.putExtra(Intentkey,User.SCIENCE_QUESTION);
                startActivity(intent) ;
            }
        });
        religion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                Intent intent=new Intent(MainActivity.this,QuestionActivity.class) ;
                intent.putExtra(Intentkey,User.RELIGION_QUESTION) ;
                startActivity(intent) ;
            }
        });



    }

    @Override
    protected void onResume()  {
        super.onResume() ;

       User.setSignUp(sharedPreferences.getBoolean(User.IS_SIGN_KEY,false));

       final String name = sharedPreferences.getString(User.NAME_KEY,"NULL"),Email = sharedPreferences.getString(User.EMAIL_KEY,"NULL"),password ="" ,userName=sharedPreferences.getString(User.USER_KEY,"");
       float score = sharedPreferences.getFloat(User.SCORE_KEY, (float) 0.0);
        String path =  MainActivity.sharedPreferences.getString(User.IMAGE_PATH_KEY,null) ;




        User.setUserName(userName);
       User.setName(name) ;
       User.setEmail(Email);
       User.setScore(score) ;
       mtvName.setText(String.valueOf(User.getName())) ;

       loadImageFromStorage(path,profileImage) ;





       if(User.isSignUp()) {



           /**************************************************************************************************/

           final ArrayList<Users> users=new ArrayList<>();
           CollectionReference collectionReference = FirebaseFirestore.getInstance().collection(Users.collectionReference);

          DocumentReference documentReference=FirebaseFirestore.getInstance().collection(Users.collectionReference).document(User.getUserName());


           documentReference.update("score",User.getScore());
           collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
               @Override
               public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                   for (QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots){

                       Users user0=documentSnapshot.toObject(Users.class);

                       users.add(user0);

                   }
               }
           }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()  {

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
                   mtvRank.setText("# "+String.valueOf(User.getRank()))  ;


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

        sharedPreferences.edit().putBoolean(key,b).apply() ;
    }
    public static void putsharedPreferences(float d,String key){

        sharedPreferences.edit().putFloat(key,d).apply() ;
    }
    private  void loadImageFromStorage(String path,CircleImageView imageView)
    {

        try {
            File f=new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));

            imageView.setImageBitmap(b) ;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        if(id==R.id.emerg){
        Intent intent =new Intent(MainActivity.this,EmergancyActivity.class);
        startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info,menu);
        return super.onCreateOptionsMenu(menu);

    }
}
