package com.avocado.qwirk;

import java.util.ArrayList;



public class Post {
    String text;
    String userName;
    int id;
    int picture;
    int timeStamp;

    public Post(String userName, String text, int picture, int timeStamp, int id) {
        this.text = text;
        this.userName = userName;
        this.id = id;
        this.picture = picture;
        this.timeStamp = timeStamp;
    }

    public String getText() {
        return text;
    }

    public String getUserName() {
        return userName;
    }

    public int getId() {
        return id;
    }
}
