package com.example.nextapp.question.Data;

import com.example.nextapp.question.MainActivity;

public class User {

    private static int rank;
    private static float score ;
    private static String name ;
    private static String Email;
    private static String userName;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        User.userName = userName;
    }

    private static boolean isSignUp = false;
    public static final String IS_SIGN_KEY = "sign_in_key";
    public static final String NAME_KEY = "name";
    public static final String USER_KEY = "user";
    public static final String RANK_KEY = "rank";
    public static final String SCORE_KEY = "score";
    public static final String EMAIL_KEY = "email";
    public static final String IMAGE_PATH_KEY="image" ;
    public static final String GI_QUESTION ="GI Question";
    public static final String SPORT_QUESTION ="Sport Question";
    public static final String RELIGION_QUESTION ="Religion Question";
    public static final String SCIENCE_QUESTION ="Science Question";

    public static final String APP_ID="ca-app-pub-1041023466257355~7577782244";


    public static boolean isSignUp() {
        return isSignUp;
    }


    public static void setSignUp(boolean signUp) {

        isSignUp = signUp;
        MainActivity.putsharedPreferences(signUp,IS_SIGN_KEY);
    }

    public static int getRank()  {
        return rank ;
    }

    public static void setRank(int rank)  {
        User.rank = rank;
    }

    public static float getScore()  {
        return score;
    }

    public static void setScore(float score)  {
        User.score = score;
    }

    public static String getName()  {
        return name;
    }

    public static void setName(String name)  {
        User.name = name;
    }

    public static String getEmail()  {
        return Email;
    }

    public static void setEmail(String email)  {
        Email = email;
    }





}
