package com.example.yanghanwen.taskmanagementmonster;


import java.util.Date;

public class Message {
    private final String username;
    private String message;
    private Date date;


    public Message(String username, String message, Date date){
        this.username = username;
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
