package com.example.nextapp.question;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.nextapp.question.Data.DataBase;
import com.example.nextapp.question.Data.User;
import com.example.nextapp.question.Recycler.RecyclerViewAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class RankingActivity extends AppCompatActivity {

    RecyclerView listView;
    RecyclerViewAdapter adapter;
   ArrayList<User>users;
   CollectionReference collectionReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

       listView=(RecyclerView) findViewById(R.id.lv_ranking);
       users=new ArrayList<>();
       /**************************************************************************************************/

        collectionReference = FirebaseFirestore.getInstance().collection(User.collectionReference);

        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots){

                    User user0=documentSnapshot.toObject(User.class);
                    users.add(user0);
                    System.out.println(user0.getName());
                }
            }
        }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                adapter=new RecyclerViewAdapter(users);
                LinearLayoutManager layoutManager = new LinearLayoutManager(RankingActivity.this);
                listView.setHasFixedSize(true);
                listView.setLayoutManager(layoutManager);

                listView.setAdapter(adapter);
            }
        });

        /************************************************************************/





    }
}
