package com.example.nextapp.question.Data;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import static java.lang.Integer.parseInt;

public class DataBase {

    DocumentReference reference;

    public void setData(String name,String userName){
        reference= FirebaseFirestore.getInstance().document("sample/"+userName);
        User user1 = null;
        user1=new User(name,userName,1,1,1);

        reference.set(user1);



    }
}
