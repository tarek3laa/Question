package com.example.nextapp.question;


import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.nextapp.question.Data.User;
import com.example.nextapp.question.Data.Users;
import com.example.nextapp.question.Recycler.RecyclerViewAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
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

public class RankingActivity extends AppCompatActivity {

   RecyclerView listView;
   RecyclerViewAdapter adapter;
   ArrayList<Users>users;
   CollectionReference collectionReference;
   ProgressBar progressBar;
   private AdView mAdView;
    private ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        actionBar = this.getSupportActionBar();
        if(actionBar!=null)actionBar.setDisplayHomeAsUpEnabled(true);

        // ad mob
        MobileAds.initialize(this, User.APP_ID);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);




       listView=(RecyclerView) findViewById(R.id.lv_ranking);
       progressBar=(ProgressBar)findViewById(R.id.loading);
       users=new ArrayList<>();
       /**************************************************************************************************/

        collectionReference = FirebaseFirestore.getInstance().collection(Users.collectionReference);
        progressBar.setVisibility(View.VISIBLE);

        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){

                    users.add(documentSnapshot.toObject(Users.class));


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


                adapter=new RecyclerViewAdapter(users);
                LinearLayoutManager layoutManager = new LinearLayoutManager(RankingActivity.this);
                listView.setHasFixedSize(true);
                listView.setLayoutManager(layoutManager);

                listView.setAdapter(adapter);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

        /************************************************************************/





    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }




}
