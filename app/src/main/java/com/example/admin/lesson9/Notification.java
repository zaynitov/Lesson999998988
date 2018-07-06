package com.example.admin.lesson9;

public class Notification {
    private int id;
    private String name;
    private String content;
    private String date;

    public Notification() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Notification(int id, String name, String content, String date) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.date = date;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
