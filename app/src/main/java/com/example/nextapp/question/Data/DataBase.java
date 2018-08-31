package com.example.nextapp.question.Data;


import android.support.annotation.NonNull;

import com.example.nextapp.question.MainActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class DataBase {



    DocumentReference UserReference;
    CollectionReference collectionReference;
    User user;
    public void setData(String name,String userName,String email,String password){
        UserReference = FirebaseFirestore.getInstance().collection(User.collectionReference).document(userName);


         user =new User(name,email,password,0,0);
        MainActivity.sharedPreferences.edit().putString(User.NAME_KEY,name);
        MainActivity.sharedPreferences.edit().putString(User.EMAIL_KEY,email);
        MainActivity.sharedPreferences.edit().apply();
        UserReference.set(user);

    }
   public static ArrayList<User>users;
    public static boolean Success =false;
   public void getUsers(){
       collectionReference =FirebaseFirestore.getInstance().collection(User.collectionReference);
       final ArrayList<User> users=new ArrayList<>();
        users.add(new User("name","email","yjfyj",0,0));
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
               Success=true;
           }
       });





    }



 }
