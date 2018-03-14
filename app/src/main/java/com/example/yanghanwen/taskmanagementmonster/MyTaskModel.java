package com.example.yanghanwen.taskmanagementmonster;

import java.util.ArrayList;

/**
 * Created by Terrence on 2018/3/11.
 */

public class MyTaskModel {
    // this model is used for pass data to my task's listview
    // the listview has to kinds:
    // for requester listview, there will be a filter to show bidding task or assigned task
    //                          if choose bidding task, list view shows status as "bidding"
    //                                                                  title
    //                                                                  lowest bid
    //                          if choose assigned task,list view shows status as "assigned"
    //                                                                  its provider username
    //                                                                  title
    //                                                                  accepted bid
    // for provider listview, there will be a filter to show bidding task or assigned task
    //                          if choose bidding task, list view shows status as "bidding"
    //                                                                  title
    //                                                                  task requester username
    //                                                                  lowest bid so far
    //                                                                  my bid
    //                          if choose assigned task, list view shows status as "assigned"
    //                                                                   title
    //                                                                   its task requester username
    //                                                                   my accepted bid
    protected String username;
    protected Task task;
    public void MyTaskModel(){

        //take username from main activity
        this.username = MainActivity.user.getUserName();

    }





}
