package com.example.nextapp.question.Data;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class User {
   private String name,Email,password;

    private int rank;
    private int score;
    private boolean isSignUp =false;
    public static final String IS_SIGN_KEY="sign_in_key";
    public static final String NAME_KEY="name";
    public static final String USER_KEY="name";
    public static final String RANK_KEY="name";
    public static final String SCORE_KEY="name";
    public static final String EMAIL_KEY="name";


    public static final String collectionReference="USER";
    public SharedPreferences sharedPreferences;
    public boolean isSignUp() {
        return isSignUp;
    }

    public void setSignUp(boolean signUp) {
        isSignUp = signUp;
    }

    public User(Context context){
        sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context);

    }
    public User(String name, String email,String password, int rank, int score ) {
        this.name = name;
        this.Email = email;

        this.rank = rank;
        this.score = score;
        this.password=password;

    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return Email;
    }


    public int getRank() {
        return rank;
    }


    public int getScore() {

        return score;
    }

}
