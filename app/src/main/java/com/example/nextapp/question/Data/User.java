package com.example.nextapp.question.Data;

public class User {
   private String name,userName;
   private int _id;
    public static int _cid=0;
    private int rank;
    private int score;

    public User(String name, String userName, int rank, int score, int _id) {
        this.name = name;
        this.userName = userName;

        this.rank = rank;
        this.score = score;
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }


    public int getRank() {
        return rank;
    }

    public static int get_cid() {
        return _cid;
    }

    public int getScore() {

        return score;
    }

    public int get_id() {
        return _id;
    }
}
