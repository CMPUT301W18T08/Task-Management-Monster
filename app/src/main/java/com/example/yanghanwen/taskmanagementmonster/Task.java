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
    private ArrayList<Bid> bids;

    public Task (int tid, String username, String taskname , String description) {

        this.tid = tid;
        this.username = username;
        this.taskname = taskname;
        this.description = description;

        status = "requested";
        bids = new ArrayList<Bid>();
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

    public void newBid(String bidder, Double amount) {

        Bid bid = new Bid(bidder, amount);
        bids.add(bid);

    }

    public Boolean hasBid() {

        int size = bids.size();

        if (size == 0) {
            return Boolean.FALSE;
        }
        else {
            return Boolean.TRUE;
        }
    }

    public Double getUserAmount(String bidder) {

        Double userAmount = null;

        if ( this.hasBid() ) {

            int maxSize = bids.size();

            for (int i = 0; i < maxSize; i = i + 1) {

                Bid bid = bids.get(i);

                if (bid.getUserName() == bidder) {

                    userAmount = bid.getAmount();
                    break;
                }
            }
        }

        return userAmount;
    }

    public Double getLowestBid() {

        Double result = null;

        if ( this.hasBid() ) {

            int maxSize = bids.size();

            for (int i = 0; i < maxSize; i = i + 1) {

                Bid bid = bids.get(i);

                if (result == null) {
                    result = bid.getAmount();
                }

                if (bid.getAmount() < result) {
                    result = bid.getAmount();
                }
            }

        }

        return result;

    }

    public void modifyBid(String bidder, Double amount) {

        if ( this.hasBid() ) {

            int maxSize = bids.size();

            for (int i = 0; i < maxSize; i = i + 1) {

                Bid bid = bids.get(i);

                if (bid.getUserName() == bidder) {

                    bid.setAmount(amount);
                    break;
                }
            }
        }
    }

    public void declineBid(String bidder) {

        if ( this.hasBid() ) {

            int maxSize = bids.size();

            for (int i = 0; i < maxSize; i = i + 1) {

                Bid bid = bids.get(i);

                if (bid.getUserName() == bidder) {

                    bids.remove(bid);
                    break;
                }
            }
        }
    }

}
