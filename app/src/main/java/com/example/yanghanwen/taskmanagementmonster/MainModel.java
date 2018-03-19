package com.example.yanghanwen.taskmanagementmonster;

import android.util.Log;

/**
 * Created by superfan1995 on 2018-03-17.
 */

public class MainModel {

    private User user;      // the user object contain information of user

    /**
     * Construct a model instance for the MainActivity by providing the current user's
     * username.
     *
     * @param username the current user's username
     */
    public MainModel(String username) {

        ElasticSearch.GetUser getUser = new ElasticSearch.GetUser();
        getUser.execute(username);

        try{
            user = getUser.get();
            Log.i("print something","should print information");
        }
        catch (Exception e) {
            Log.i("Error", "Fail to connect to server");
        }
    }

    /**
     *
     * @return
     */
    public String getUsername() {

        return user.getUserName();
    }

    /**
     *
     * @return
     */
    public String getEmail() {

        return user.getEmail();
    }

    /**
     *
     * @return
     */
    public String getPhoneNum() {

        return user.getPhoneNum();
    }

    /**
     *
     * @param email
     * @param phoneNum
     */
    public void updateUser(String email, String phoneNum) {

        user.setEmail(email);
        user.setPhoneNum(phoneNum);

        ElasticSearch.AddUser addUser = new ElasticSearch.AddUser();
        addUser.execute(user);
    }

}