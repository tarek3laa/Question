package com.example.nextapp.question.Data;

public class User {
   private String name,userName,id,rank,score;
   private int _id;

    public User(String name, String userName, String id, String rank, String score, int _id) {
        this.name = name;
        this.userName = userName;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public String getRank() {
        return rank;
    }

    public String getScore() {
        return score;
    }

    public int get_id() {
        return _id;
    }
}
