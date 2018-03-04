package com.example.yanghanwen.taskmanagementmonster;

/**
 * Created by superfan1995 on 2018-02-26.
 */

import java.util.Date;

/**
 * Created by Terrence on 2018/2/26.
 */

public class Message {
    private String username;
    private String message;
    private Date date;
    public Message(String username,String message,Date date){
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
