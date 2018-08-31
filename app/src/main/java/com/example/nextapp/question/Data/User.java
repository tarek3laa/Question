package com.example.nextapp.question.Data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.nextapp.question.MainActivity;
import com.google.firebase.firestore.Exclude;

import java.io.Serializable;


public class User {
    private String name;
    private String Email;
    private String password;





    private int rank;
    private int score;
    private static boolean isSignUp = false;
    public static final String IS_SIGN_KEY = "sign_in_key";
    public static final String NAME_KEY = "name";
    public static final String USER_KEY = "user";
    public static final String RANK_KEY = "rank";
    public static final String SCORE_KEY = "score";
    public static final String EMAIL_KEY = "email";

    public static final String FILE_NAME = "com.example.nextapp.question.Data";

    public static final String collectionReference = "USERS";

    public static boolean isSignUp() {
        return isSignUp;
    }

    public static void setSignUp(boolean signUp) {

        isSignUp = signUp;
        MainActivity.sharedPreferences.edit().putBoolean(IS_SIGN_KEY, signUp).apply();
    }

    public User() {

    }

    public User(String name, String email, String password, int rank, int score) {
        this.name = name;
        this.Email = email;

        this.rank = rank;
        this.score = score;
        this.password = password;

    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return Email;
    }

    public int getRank() {
        return rank;
    }


    public int getScore() {

        return score;
    }



}
