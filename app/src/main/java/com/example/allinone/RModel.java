package com.example.allinone;

public class RModel {
    String id, title, description, date, time;
    int uniid;

    public RModel(String id, String title, String description, String date, String time, int uniid) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.uniid = uniid;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUniid() {
        return uniid;
    }

    public void setUniid(int uniid) {
        this.uniid = uniid;
    }
}


