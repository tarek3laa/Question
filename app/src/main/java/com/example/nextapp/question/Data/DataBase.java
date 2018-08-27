package com.example.nextapp.question.Data;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.nextapp.question.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class DataBase {

    DocumentReference UserReference;
    CollectionReference collectionReference;
    User user;
    public void setData(String name,String userName,String email,String password){
        UserReference = FirebaseFirestore.getInstance().collection(User.collectionReference).document(userName);
        collectionReference =FirebaseFirestore.getInstance().collection("USER");

         user =new User(name,email,password,0,0);
        MainActivity.sharedPreferences.edit().putString(User.NAME_KEY,name);
        MainActivity.sharedPreferences.edit().putString(User.EMAIL_KEY,email);
        MainActivity.sharedPreferences.edit().apply();
        UserReference.set(user);

    }
    public boolean getCurrUser(final String userName, final String password){
        final boolean[] success = {false};
        UserReference=FirebaseFirestore.getInstance().collection(User.collectionReference).document(userName);
        UserReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if (documentSnapshot.getId().equals(userName))success[0]=true;

                else success[0] = false;
            }
        });

        return success[0];
    }
}
