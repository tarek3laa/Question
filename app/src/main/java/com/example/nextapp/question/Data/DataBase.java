package com.example.nextapp.question.Data;

import com.google.android.gms.auth.api.Auth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;

import static java.lang.Integer.parseInt;

public class DataBase {

    DocumentReference reference;
    CollectionReference reference1;



    public void setData(String name,String userName){
        reference= FirebaseFirestore.getInstance().document("sample");




        User user1 = null;
        user1=new User(name,userName,1,1,1);

        reference.set(user1);




    }
}
