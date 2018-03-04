package com.example.yanghanwen.taskmanagementmonster;

/**
 * Created by songxixuan on 2018-02-26.
 */

public class Bid {

    private String userName;
    private double amount;

    public Bid() {
    }

    public Bid(String userName, double amount) {
        this.userName = userName;
        this.amount = amount;
    }

    public String getUserName() {

        return userName;
    }

    public double getAmount() {

        return amount;
    }


    public void setAmount(double amount) {

        this.amount = amount;
    }

    public String toString() {

        return "Name: " + userName + " " + "Amount: " + amount;
    }
}