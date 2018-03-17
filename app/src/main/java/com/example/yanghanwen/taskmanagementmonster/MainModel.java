package com.example.yanghanwen.taskmanagementmonster;

import android.util.Log;

/**
 * Created by superfan1995 on 2018-03-17.
 */

public class MainModel {

    private ElasticSearch.GetUser getUser;
    private User user;

    public MainModel(String username) {

        getUser = new ElasticSearch.GetUser();
        getUser.execute(username);

        try{
            user = getUser.get();
            Log.i("print something","should print information");
        }
        catch (Exception e) {
            Log.i("Error", "Fail to connect to server");
        }
    }

    public String getUsername() {

        return user.getUserName();
    }

}
