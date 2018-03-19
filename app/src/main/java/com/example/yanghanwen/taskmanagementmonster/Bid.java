package com.example.yanghanwen.taskmanagementmonster;



/**
 * Bid class object
 *
 * Contain and operate on the necessary data of each bid
 *
 * @version 1.0
 */
public class Bid {

    private final String userName;  // the bidder's username
    private double amount;          // the bidder's bid amount

    /**
     * Create a new bid with input username and amount
     *
     * @param userName
     * @param amount
     */
    public Bid(String userName, double amount) {
        this.userName = userName;
        this.amount = amount;
    }

    /**
     * get the username of bid
     *
     * @return username of bid
     */
    public String getUserName() {

        return userName;
    }

    /**
     * get the amount of bid
     *
     * @return amount of bid
     */
    public double getAmount() {

        return amount;
    }

    /**
     * set the amount of bid
     *
     * @param amount the new amount of bid
     */
    public void setAmount(double amount) {

        this.amount = amount;
    }

    /**
     * print the string of summary of a bid information that showed on the ListView of all bids of
     * a task
     *
     * @return string of summary of a bid information
     */
    public String toString() {

        return "Name: " + userName + " | " + "Amount: " + amount;
    }
}