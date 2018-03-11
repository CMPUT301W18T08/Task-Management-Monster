package com.example.yanghanwen.taskmanagementmonster;

import java.util.ArrayList;

/**
 * Created by superfan1995 on 2018-02-26.
 */

public class Task {

    private int tid;
    private String username;
    private String taskname;
    private String status;
    private String description;
    ArrayList<Bid> bids;

    public Task(){

    }

    public Task (int tid, String username, String taskname , String description) {
        this.tid = tid;
        this.username = username;
        this.taskname = taskname;
        this.description = description;
    }

    public int getTid () {

        return this.tid;
    }

    public String getUsername () {

        return this.username;
    }

    public String getTaskname () {

        return taskname;
    }

    public String getStatus () {

        return this.status;
    }

    public String getDescription () {

        return this.description;
    }

    public void setTaskname (String taskname) {

        this.taskname = taskname;
    }

    public void setStatus (String status) {

        this.status = status;
    }

    public void setDescription (String description) {

        this.description = description;
    }

    public void newBid(String bider, Double amount) {

        Bid bid = new Bid(bider, amount);
        bids.add(bid);

    }

    // need change
    public Double getLowestBid() {

        return 10.0;
    }

    // need cahnge
    public Double getUserBid(String bider1) {

        return 10.0;
    }

    public void modifyBid(String bider1, Double amount2) {


    }

    public Boolean hasBid(String bider1) {

        return Boolean.FALSE;
    }

    public void declineBid(String bider1) {


    }

}
