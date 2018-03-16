package com.example.yangwenhan.project;

import io.searchbox.annotations.JestId;

/**
 * Created by yangwenhan on 2018/2/21.
 */

public class User {
    @JestId
    private String userName;
    private int uid;
    private String email;
    private String phoneNum;

//    public static ArrayList<User> Users = new ArrayList<>();

    public User(){

    }

    public User(String name, String email, String phoneNum){
        this.userName = name;
        this.email = email;
        this.phoneNum = phoneNum;

    }

//    public int getUid() {
//        return uid;
//    }
//
//    public void setUid(int uid) {
//        this.uid = uid;
//    }

    public String getUserName(){
        return this.userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhoneNum(){
        return this.phoneNum;
    }

    public void setPhoneNum(String phoneNum){
        this.phoneNum = phoneNum;
    }

}
