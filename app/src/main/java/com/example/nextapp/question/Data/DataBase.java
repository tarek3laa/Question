package com.example.nextapp.question.Data;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;
import android.widget.Toast;

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

    public void setData(String name,String userName,String email,String password){
        UserReference = FirebaseFirestore.getInstance().collection(User.collectionReference).document(userName);
        collectionReference =FirebaseFirestore.getInstance().collection("USER");

        User user1=new User(name,email,password,0,0);

        UserReference.set(user1).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                System.out.println("sssss");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("err");
            }
        });

    }
    public User getCurrUser(String userName){
        final User[] user = new User[1];
        final boolean[] success = {true};
        UserReference=FirebaseFirestore.getInstance().collection(User.collectionReference).document(userName);
        UserReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists())
                user[0] =documentSnapshot.toObject(User.class);

                else {
                    success[0] =false;
                }
            }
        });

        if (success[0])
        return user[0];
        else return null;
    }
}
