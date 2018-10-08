package com.example.nextapp.question.Data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.nextapp.question.MainActivity;
import com.google.firebase.firestore.Exclude;

import java.io.Serializable;


public class Users {
    private String name;
    private String Email;
    private String password ;
    private String userName ;
    private int score;


    public static final String FILE_NAME = "com.example.nextapp.question.Data";

    public static final String collectionReference = "Users";


    public Users() {

    }

    public String getUserName() {
        return userName;
     }



    public Users(String name, String email, String userName, String password, int score) {
        this.name = name;
        this.Email = email ;
        this.score = score;
        this.password = password;
        this.userName=userName ;


    }

    public String getPassword() {
        return password;
    }

    public String getName()  {
        return name;
    }

    public String getEmail() {
        return Email;
    }

    public int getScore() {

        return score;
    }

    public void setEmail(String email)  {
        Email = email;
    }
}
